package br.com.associacaoshare.controller

import br.com.associacaoshare.controller.security.ContentType
import br.com.associacaoshare.controller.security.ShareAccessManager.MainRole.ADMIN
import br.com.associacaoshare.controller.security.ShareAccessManager.MainRole.SUPERADMIN
import br.com.associacaoshare.controller.security.UnableToEditException
import br.com.associacaoshare.model.Pagina
import br.com.associacaoshare.model.Usuario
import br.com.associacaoshare.model.dao.DataAccessObject
import br.com.associacaoshare.model.page.*
import br.com.associacaoshare.utils.brazilZone
import br.com.associacaoshare.view.AdminView
import br.com.associacaoshare.view.EditarNoticiaView
import br.com.associacaoshare.view.EditarPaginaView
import br.com.associacaoshare.view.EditarUsuarioView
import com.fasterxml.jackson.databind.ObjectMapper
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.EndpointGroup
import io.javalin.core.security.SecurityUtil.roles
import io.javalin.http.Context
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.net.URLEncoder
import java.time.OffsetDateTime
import kotlin.text.Charsets.UTF_8

class AdminController(override val kodein: Kodein) : EndpointGroup, KodeinAware {
    val dao: DataAccessObject by instance()
    val mapper: ObjectMapper by instance()
    val requiredRole = roles(ADMIN, SUPERADMIN)

    override fun addEndpoints() {
        get(::admin, requiredRole)

        get("novaPagina", ::novaPagina, requiredRole)
        post("novaPagina", ::criarNovaPagina, requiredRole)

        get("novaNoticia", ::novaNoticia, requiredRole)
        post("novaNoticia", ::criarNovaNoticia, requiredRole)

        get("editarPagina/:pagina", ::editarPagina, requiredRole)
        post("editarPagina/:pagina", ::salvarEdicaoPagina, requiredRole)

        get("editarNoticia/:noticia", ::editarNoticia, requiredRole)
        post("editarNoticia/:noticia", ::salvarEdicaoNoticia, requiredRole)

        get("removerPagina/:pagina", ::removerPagina, requiredRole)
        get("removerNoticia/:noticia", ::removerNoticia, requiredRole)

        post("novoLink", ::novoLink, requiredRole)
        get("swapLinks", ::swapLinks, requiredRole)
        get("deleteLink", ::deleteLink, requiredRole)

        get("perfil", ::perfil, requiredRole)
        post("perfil", ::salvarPerfil, requiredRole)
    }

    fun admin(ctx: Context) {
        val noticias = dao.allNoticias()
        val paginas = dao.allPaginas()
        val pessoaMap = listOf(
            noticias.flatMap { listOfNotNull(it.criadoPorPessoa, it.ultimaModificacaoPorPessoa) },
            paginas.flatMap { listOfNotNull(it.criadoPorPessoa, it.ultimaModificacaoPorPessoa) }
        ).flatten().toSet().mapNotNull { dao.getPessoa(it)?.let { p -> it to p } }.toMap()

        val self = ctx.sessionAttribute<Usuario>("USER")!!
        if (self.admin) {
            val pessoas = dao.allPessoas().map { it to dao.getUsuarioByPessoa(it.id) }
            AdminView(SuperAdminViewModel(self, pessoas, noticias, paginas, pessoaMap, dao.allLinks())).render(ctx)
        } else {
            AdminView(AdminViewModel(noticias, paginas, pessoaMap, dao.allLinks())).render(ctx)
        }
    }

    fun novaPagina(ctx: Context) {
        EditarPaginaView(EditarPaginaViewModel(mapper, null, false)).render(ctx)
    }

    fun criarNovaPagina(ctx: Context) {
        val title = ctx.formParam("title")
        val linkPagina = ctx.formParam("linkPagina")
        val html = ctx.formParam("html")

        if (!title.isNullOrBlank() && !linkPagina.isNullOrBlank() && !html.isNullOrBlank()) {
            dao.insertPagina(linkPagina, title, html, ctx.sessionAttribute<Usuario>("USER")!!.pessoaId)

            ctx.redirect("/admin?novaPagina=success&linkPagina=${URLEncoder.encode(linkPagina, UTF_8.toString())}")
        }
        ctx.redirect("/admin?novaPagina=invalid")
    }

    fun novaNoticia(ctx: Context) {
        EditarNoticiaView(EditarNoticiaViewModel(mapper, null, false)).render(ctx)
    }

    fun criarNovaNoticia(ctx: Context) {
        val title = ctx.formParam("title")
        val html = ctx.formParam("html")

        if (!title.isNullOrBlank() && !html.isNullOrBlank()) {
            val id = dao.insertNoticia(title, html, ctx.sessionAttribute<Usuario>("USER")!!.pessoaId).id

            ctx.redirect("/admin?novaNoticia=success&id=$id")
            return
        }
        ctx.redirect("/admin?novaNoticia=invalid")
    }

    fun editarPagina(ctx: Context) {
        val linkPagina = ctx.pathParam("pagina")
        val pagina = dao.getPagina(linkPagina) ?: throw UnableToEditException(true, ContentType.PAGINA)

        EditarPaginaView(EditarPaginaViewModel(mapper, pagina, true)).render(ctx)
    }

    fun salvarEdicaoPagina(ctx: Context) {
        val linkPagina = ctx.pathParam("pagina")
        val pagina = dao.getPagina(linkPagina) ?: throw UnableToEditException(true, ContentType.PAGINA)

        val title = ctx.formParam("title")
        val novoLinkPagina = ctx.formParam("linkPagina")
        val html = ctx.formParam("html")

        if (!title.isNullOrBlank() && !novoLinkPagina.isNullOrBlank() && !html.isNullOrBlank()) {
            val p: Pagina
            if (novoLinkPagina != novoLinkPagina) {
                dao.removePagina(novoLinkPagina)
                p = dao.insertPagina(novoLinkPagina, title, html, pagina.criadoPorPessoa)
                p.dataCriacao = pagina.dataCriacao
            } else p = pagina

            p.titulo = title
            p.html = html
            p.dataModificacao = OffsetDateTime.now(brazilZone)
            p.ultimaModificacaoPorPessoa = ctx.sessionAttribute<Usuario>("USER")!!.pessoaId
            dao.updatePagina(p)

            ctx.redirect("/admin?editarPagina=success&linkPagina=${URLEncoder.encode(novoLinkPagina, UTF_8.toString())}")
            return
        }
        ctx.redirect("/admin?editarPagina=invalid")
    }

    fun editarNoticia(ctx: Context) {
        val noticiaId = ctx.pathParam("noticia")
        val id = noticiaId.toIntOrNull() ?: throw UnableToEditException(true, ContentType.NOTICIA)
        val noticia = dao.getNoticia(id) ?: throw UnableToEditException(true, ContentType.NOTICIA)

        EditarNoticiaView(EditarNoticiaViewModel(mapper, noticia, true)).render(ctx)
    }

    fun salvarEdicaoNoticia(ctx: Context) {
        val noticiaId = ctx.pathParam("noticia")
        val id = noticiaId.toIntOrNull() ?: throw UnableToEditException(true, ContentType.NOTICIA)
        val noticia = dao.getNoticia(id) ?: throw UnableToEditException(true, ContentType.NOTICIA)

        val title = ctx.formParam("title")
        val html = ctx.formParam("html")

        if (!title.isNullOrBlank() && !html.isNullOrBlank()) {
            noticia.titulo = title
            noticia.html = html
            noticia.dataModificacao = OffsetDateTime.now(brazilZone)
            noticia.ultimaModificacaoPorPessoa = ctx.sessionAttribute<Usuario>("USER")!!.pessoaId
            dao.updateNoticia(noticia)

            ctx.redirect("/admin?editarNoticia=success&id=$id")
            return
        }
        ctx.redirect("/admin?editarNoticia=invalid")
    }

    fun removerPagina(ctx: Context) {
        val linkPagina = ctx.pathParam("pagina")
        dao.getPagina(linkPagina) ?: throw UnableToEditException(true, ContentType.PAGINA)

        dao.removePagina(linkPagina)
        ctx.redirect("/admin?removerPagina=success")
    }

    fun removerNoticia(ctx: Context) {
        val noticiaId = ctx.pathParam("noticia")
        val id = noticiaId.toIntOrNull() ?: throw UnableToEditException(true, ContentType.NOTICIA)
        dao.getNoticia(id) ?: throw UnableToEditException(true, ContentType.NOTICIA)

        dao.removeNoticia(id)
        ctx.redirect("/admin?removerNoticia=success")
    }

    fun novoLink(ctx: Context) {
        val nome = ctx.formParam("nome")
        val href = ctx.formParam("href")
        if (!nome.isNullOrBlank() && !href.isNullOrBlank()) {
            dao.insertLink(nome, href)
            ctx.redirect("/admin?novoLink=success")
            return
        }
        ctx.redirect("/admin?novoLink=invalid")
    }

    fun swapLinks(ctx: Context) {
        val firstId = ctx.queryParam("i")?.toIntOrNull()
        val secondId = ctx.queryParam("j")?.toIntOrNull()
        if (firstId != null && secondId != null) {
            val first = dao.getLink(firstId)
            val second = dao.getLink(secondId)

            if (first != null && second != null) {
                dao.swapLinks(first to second)
                ctx.redirect("/admin?swapLinks=success")
                return
            }
        }
        ctx.redirect("/admin?swapLinks=invalid")
    }

    fun deleteLink(ctx: Context) {
        val id = ctx.queryParam("i")?.toIntOrNull()
        if (id != null) {
            val first = dao.getLink(id)

            if (first != null) {
                dao.removeLink(id)
                ctx.redirect("/admin?deleteLink=success")
                return
            }
        }
        ctx.redirect("/admin?deleteLink=invalid")
    }

    fun perfil(ctx: Context) {
        val self = ctx.sessionAttribute<Usuario>("USER")!!
        val pessoa = dao.getPessoa(self.pessoaId) ?: throw UnableToEditException(true, ContentType.USUARIO)

        EditarUsuarioView(EditarUsuarioViewModel(pessoa, self, true)).render(ctx)
    }

    fun salvarPerfil(ctx: Context) {
        val self = ctx.sessionAttribute<Usuario>("USER")!!
        val pessoa = dao.getPessoa(self.pessoaId) ?: throw UnableToEditException(true, ContentType.USUARIO)

        val nome = ctx.formParam("nome")
        val username = ctx.formParam("username")
        val password = ctx.formParam("password")

        if (!nome.isNullOrBlank() && !username.isNullOrBlank()) {
            if (pessoa.nome != nome) {
                pessoa.nome = nome
                dao.updatePessoa(pessoa)
            }

            if (self.username != username) {
                if (dao.getUsuario(username) != null) {
                    ctx.redirect("/admin?editarUsuario=invalid")
                    return
                }
                val novoUsuario = dao.insertUsuario(username, password ?: "", self.pessoaId, self.admin)
                if (password.isNullOrBlank()) {
                    novoUsuario.hash = self.hash
                    dao.updateUsuario(novoUsuario)
                }
                dao.removeUsuario(self.username)
                ctx.sessionAttribute("USER", novoUsuario)
            } else if (!password.isNullOrBlank()) {
                val oldHash = self.hash
                self.hashPassword(password)
                if (self.hash != oldHash) {
                    dao.updateUsuario(self)
                }
            }
            ctx.redirect("/admin?perfil=success")
            return
        }
        ctx.redirect("/admin?perfil=invalid")
        return
    }

}