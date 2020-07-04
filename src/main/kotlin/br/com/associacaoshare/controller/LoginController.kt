package br.com.associacaoshare.controller

import br.com.associacaoshare.controller.security.ShareAccessManager
import br.com.associacaoshare.controller.security.ShareAccessManager.MainRole
import br.com.associacaoshare.model.Usuario
import br.com.associacaoshare.model.dao.DataAccessObject
import br.com.associacaoshare.model.page.PagIniModel
import br.com.associacaoshare.view.LoginView
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.EndpointGroup
import io.javalin.http.Context
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance
import java.net.URLDecoder
import java.net.URLEncoder
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit

class LoginController(override val kodein: Kodein) : EndpointGroup, KodeinAware {
    val dao: DataAccessObject by instance()

    override fun addEndpoints() {
        get("login", ::login)
        post("doLogin", ::doLogin)
        get("logout", ::doLogout)
    }

    private fun login(ctx: Context) {
        val then = ctx.queryParam("then")?.let { URLDecoder.decode(it, Charsets.UTF_8.toString()) }

        when (ShareAccessManager.getRole(ctx.sessionAttribute<Usuario>("USER"))) {
            MainRole.ANYONE -> {
                val jwtToken = ctx.cookie("ulovecookies")

                if (jwtToken != null) {
                    val verifier: JWTVerifier by instance()

                    verifier.runCatching { verify(jwtToken) }
                        .onFailure { ctx.removeCookie("ulovecookies") }
                        .onSuccess {
                            dao.getUsuario(it.subject)?.let { u ->
                                loginRoutine(ctx, u, true)
                                return
                            }
                            ctx.removeCookie("ulovecookies")
                        }
                }

                LoginView(PagIniModel(dao.allLinks())).render(ctx)
            }
            else -> ctx.redirect(then ?: "/admin")
        }
    }

    private fun doLogin(ctx: Context) {
        val user = ctx.formParam("username")
        val pass = ctx.formParam("password")
        val rememberMe = ctx.formParamMap().containsKey("ilovecookies")

        if (user == null) {
            ctx.redirect("/login/?err=invalidcreds")
            return
        }
        val invalidCredsWithUsername = "/login/?err=invalidcreds&username=${URLEncoder.encode(user, Charsets.UTF_8.toString())}"
        if (pass == null) {
            ctx.redirect(invalidCredsWithUsername)
            return
        }

        dao.getUsuario(user)?.let { u ->
            if (u.hash == DataAccessObject.hashPassword(pass)) {
                loginRoutine(ctx, u, rememberMe)
                return
            }
        }

        ctx.redirect(invalidCredsWithUsername)
    }

    private fun doLogout(ctx: Context) {
        ctx.sessionAttribute("USER", null)
        ctx.removeCookie("ulovecookies")
        ctx.redirect("/")
    }

    private fun loginRoutine(ctx: Context, u: Usuario, rememberMe: Boolean) {
        ctx.sessionAttribute("USER", u)
        val then = (ctx.formParam("then") ?: ctx.queryParam("then"))?.let { URLDecoder.decode(it, Charsets.UTF_8.toString()) }

        if (rememberMe) {
            val expires = Date.from(Instant.now().plus(60, ChronoUnit.DAYS))
            val jwt = JWT.create()
                .withIssuer("associacaoshare")
                .withSubject(u.username)
                .withIssuedAt(Date())
                .withExpiresAt(expires)
                .sign(direct.instance())
            ctx.cookie("ulovecookies", jwt, TimeUnit.DAYS.toMillis(60).toInt())
        }
        ctx.redirect(then ?: "/admin")
    }
}