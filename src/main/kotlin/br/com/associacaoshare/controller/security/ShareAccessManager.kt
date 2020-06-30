package br.com.associacaoshare.controller.security

import br.com.associacaoshare.controller.security.MainRole.*
import br.com.associacaoshare.model.Usuario
import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import kotlin.text.Charsets.UTF_8
import java.net.URLEncoder.encode as urlEncode

class ShareAccessManager : AccessManager {
    override fun manage(handler: Handler, ctx: Context, permittedRoles: MutableSet<Role>) {
        if (permittedRoles.isEmpty()) {
            handler.handle(ctx)
        } else {
            when (getRole(ctx.sessionAttribute<Usuario>("USER"))) {
                in permittedRoles -> handler.handle(ctx)
                ANYONE -> {
                    val thenUrl = listOfNotNull(ctx.path(), ctx.queryString()).joinToString("?")
                    ctx.redirect("/login?err=unauthorized&then=${urlEncode(thenUrl, UTF_8.toString())}")
                }
                else -> throw ForbiddenAccessException()
            }
        }
    }

    companion object {
        fun getRole(usuario: Usuario?): MainRole {
            return when (usuario?.admin) {
                true -> SUPERADMIN
                false -> ADMIN
                else -> ANYONE
            }
        }
    }
}