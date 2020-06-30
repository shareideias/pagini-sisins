package br.com.associacaoshare.controller

import br.com.associacaoshare.model.exception.FalhaSessaoException
import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler

class SisinsAccessManager : AccessManager {
    override fun manage(handler: Handler, ctx: Context, permittedRoles: MutableSet<Role>) {
        if (permittedRoles.isEmpty()) {
            handler.handle(ctx)
        } else {
            when (getRole(ctx)) {
                in permittedRoles -> handler.handle(ctx)
                else -> throw FalhaSessaoException()
            }
        }
    }

    private fun getRole(ctx: Context): Roles {
        val role = ctx.sessionAttribute<String>("ROLE")
        ctx.sessionAttribute<Int>("ID") ?: return Roles.ANYONE
        return when (role) {
            "AVALIADOR" -> Roles.AVALIADOR
            "PARTICIPANTE" -> Roles.PARTICIPANTE
            else -> Roles.ANYONE
        }
    }

    internal enum class Roles : Role {
        ANYONE, PARTICIPANTE, AVALIADOR
    }
}
