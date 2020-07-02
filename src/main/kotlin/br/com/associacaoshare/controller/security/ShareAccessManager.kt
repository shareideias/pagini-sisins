package br.com.associacaoshare.controller.security

import br.com.associacaoshare.model.Usuario
import br.com.associacaoshare.model.exception.FalhaSessaoException
import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import java.net.URLEncoder

class ShareAccessManager : AccessManager {
    override fun manage(handler: Handler, ctx: Context, permittedRoles: MutableSet<Role>) {
        if (permittedRoles.isEmpty()) {
            handler.handle(ctx)
        } else {
            //PHP venha logo cansei de gambiarra
            if(permittedRoles.contains(Roles.AVALIADOR) || permittedRoles.contains(Roles.PARTICIPANTE)) {
                //Access manager do sisins
                when (getRole(ctx)) {
                    in permittedRoles -> handler.handle(ctx)
                    else -> throw FalhaSessaoException()
                }
            } else {
                //Access manager do pagini
                when (getRole(ctx.sessionAttribute<Usuario>("USER"))) {
                    in permittedRoles -> handler.handle(ctx)
                    MainRole.ANYONE -> {
                        val thenUrl = listOfNotNull(ctx.path(), ctx.queryString()).joinToString("?")
                        ctx.redirect("/login?err=unauthorized&then=${URLEncoder.encode(thenUrl, Charsets.UTF_8.toString())}")
                    }
                    else -> throw ForbiddenAccessException()
                }
            }
        }
    }

    //getrole do sisins
    private fun getRole(ctx: Context): Roles {
        val role = ctx.sessionAttribute<String>("ROLE")
        ctx.sessionAttribute<Int>("ID") ?: return Roles.ANYONE
        return when (role) {
            "AVALIADOR" -> Roles.AVALIADOR
            "PARTICIPANTE" -> Roles.PARTICIPANTE
            else -> Roles.ANYONE
        }
    }

    //Getrole do pagini
    companion object {
        fun getRole(usuario: Usuario?): MainRole {
            return when (usuario?.admin) {
                true -> MainRole.SUPERADMIN
                false -> MainRole.ADMIN
                else -> MainRole.ANYONE
            }
        }
    }

    //Roles do Sisins
    internal enum class Roles : Role {
        ANYONE, PARTICIPANTE, AVALIADOR
    }

    //Roles do Pagini
    enum class MainRole : Role {
        ANYONE, ADMIN, SUPERADMIN
    }
}
