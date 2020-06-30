package br.com.associacaoshare.controller

import br.com.associacaoshare.controller.security.ContentType
import br.com.associacaoshare.controller.security.ForbiddenAccessException
import br.com.associacaoshare.controller.security.MainRole.SUPERADMIN
import br.com.associacaoshare.controller.security.UnableToEditException
import br.com.associacaoshare.model.Usuario
import br.com.associacaoshare.model.dao.DataAccessObject
import br.com.associacaoshare.model.page.EditarUsuarioViewModel
import br.com.associacaoshare.view.EditarUsuarioView
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.EndpointGroup
import io.javalin.core.security.SecurityUtil.roles
import io.javalin.http.Context
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class SuperAdminController(override val kodein: Kodein) : EndpointGroup, KodeinAware {
    val dao: DataAccessObject by instance()
    val requiredRole = roles(SUPERADMIN)

    override fun addEndpoints() {
        post("novoUsuario", ::novoUsuario, requiredRole)
        get("removerPessoa/:pessoa", ::removerPessoa, requiredRole)

        get("editarUsuario/:pessoa", ::editarUsuario, requiredRole)
        post("editarUsuario/:pessoa", ::salvarEdicaoUsuario, requiredRole)

        get("linkUsuario/:pessoa", ::linkUsuario, requiredRole)
        post("linkUsuario/:pessoa", ::criarLinkUsuario, requiredRole)
        get("unlinkUsuario/:pessoa", ::unlinkUsuario, requiredRole)
    }

    fun novoUsuario(ctx: Context) {
        val nome = ctx.formParam("nome")
        val username = ctx.formParam("username")
        val password = ctx.formParam("password")
        val admin = ctx.formParamMap().containsKey("superadmin")

        if (!nome.isNullOrBlank() && !username.isNullOrBlank() && dao.getUsuario(username) == null && !password.isNullOrBlank()) {
            dao.insertUsuario(username, password, dao.insertPessoa(nome).id, admin)
            ctx.redirect("/admin?novoUsuario=success")
            return
        }
        ctx.redirect("/admin?novoUsuario=invalid")
    }

    fun removerPessoa(ctx: Context) {
        val pessoaId = ctx.pathParam("pessoa")
        val id = pessoaId.toIntOrNull() ?: throw UnableToEditException(true, ContentType.PESSOA)
        dao.getPessoa(id) ?: throw UnableToEditException(true, ContentType.PESSOA)

        dao.removePessoa(id)
        ctx.redirect("/admin?removerPessoa=success")
    }

    fun editarUsuario(ctx: Context) {
        val pessoaId = ctx.pathParam("pessoa")
        val id = pessoaId.toIntOrNull() ?: throw UnableToEditException(true, ContentType.PESSOA)
        val pessoa = dao.getPessoa(id) ?: throw UnableToEditException(true, ContentType.PESSOA)
        val self = ctx.sessionAttribute<Usuario>("USER")!!

        EditarUsuarioView(EditarUsuarioViewModel(
            pessoa, dao.getUsuarioByPessoa(id), self.pessoaId == id
        )).render(ctx)
    }

    fun salvarEdicaoUsuario(ctx: Context) {
        val pessoaId = ctx.pathParam("pessoa")
        val id = pessoaId.toIntOrNull() ?: throw UnableToEditException(true, ContentType.PESSOA)
        val pessoa = dao.getPessoa(id) ?: throw UnableToEditException(true, ContentType.PESSOA)
        val usuario = dao.getUsuarioByPessoa(id) ?: throw UnableToEditException(true, ContentType.USUARIO)
        val self = ctx.sessionAttribute<Usuario>("USER")!!

        val nome = ctx.formParam("nome")
        val username = ctx.formParam("username")
        val password = ctx.formParam("password")
        val admin = if (self.pessoaId == id) self.admin else ctx.formParamMap().containsKey("superadmin")

        if (!nome.isNullOrBlank() && !username.isNullOrBlank()) {
            if (pessoa.nome != nome) {
                pessoa.nome = nome
                dao.updatePessoa(pessoa)
            }

            if (usuario.username != username) {
                if (dao.getUsuario(username) != null) {
                    ctx.redirect("/admin?editarUsuario=invalid")
                    return
                }
                val novoUsuario = dao.insertUsuario(username, password ?: "", id, admin)
                if (password.isNullOrBlank()) {
                    novoUsuario.hash = usuario.hash
                    dao.updateUsuario(novoUsuario)
                }
                dao.removeUsuario(usuario.username)
                if (self.username == usuario.username) {
                    ctx.sessionAttribute("USER", novoUsuario)
                }
            } else if (!password.isNullOrBlank()) {
                val oldHash = usuario.hash
                usuario.admin = admin
                usuario.hashPassword(password)
                if (usuario.hash != oldHash) {
                    dao.updateUsuario(usuario)
                }
            } else if (usuario.admin != admin) {
                usuario.admin = admin
                dao.updateUsuario(usuario)
            }
            ctx.redirect("/admin?editarUsuario=success")
            return
        }
        ctx.redirect("/admin?editarUsuario=invalid")
        return
    }

    fun linkUsuario(ctx: Context) {
        val pessoaId = ctx.pathParam("pessoa")
        val id = pessoaId.toIntOrNull() ?: throw UnableToEditException(true, ContentType.PESSOA)
        val pessoa = dao.getPessoa(id) ?: throw UnableToEditException(true, ContentType.PESSOA)
        val self = ctx.sessionAttribute<Usuario>("USER")!!
        val usuario = dao.getUsuarioByPessoa(id)

        if (self.pessoaId == id || usuario != null) {
            throw ForbiddenAccessException()
        }

        EditarUsuarioView(EditarUsuarioViewModel(
            pessoa, usuario, self.pessoaId == id
        )).render(ctx)
    }

    fun criarLinkUsuario(ctx: Context) {
        val pessoaId = ctx.pathParam("pessoa")
        val id = pessoaId.toIntOrNull() ?: throw UnableToEditException(true, ContentType.PESSOA)
        val pessoa = dao.getPessoa(id) ?: throw UnableToEditException(true, ContentType.PESSOA)
        val self = ctx.sessionAttribute<Usuario>("USER")!!

        val nome = ctx.formParam("nome")
        val username = ctx.formParam("username")
        val password = ctx.formParam("password")
        val admin = ctx.formParamMap().containsKey("superadmin")

        if (self.pessoaId != id && !nome.isNullOrBlank() && !username.isNullOrBlank() && dao.getUsuario(username) == null && !password.isNullOrBlank()) {
            if (pessoa.nome != nome) {
                pessoa.nome = nome
                dao.updatePessoa(pessoa)
            }
            dao.insertUsuario(username, password, id, admin)
            ctx.redirect("/admin?linkUsuario=success")
            return
        }
        ctx.redirect("/admin?linkUsuario=invalid")
        return
    }

    fun unlinkUsuario(ctx: Context) {
        val pessoaId = ctx.pathParam("pessoa")
        val id = pessoaId.toIntOrNull() ?: throw UnableToEditException(true, ContentType.PESSOA)
        val usuario = dao.getUsuarioByPessoa(id) ?: throw UnableToEditException(true, ContentType.USUARIO)
        val self = ctx.sessionAttribute<Usuario>("USER")!!

        if (self.pessoaId != id) {
            dao.removeUsuario(usuario.username)
            ctx.redirect("/admin?unlinkUsuario=success")
            return
        }
        ctx.redirect("/admin?unlinkUsuario=invalid")
        return
    }
}