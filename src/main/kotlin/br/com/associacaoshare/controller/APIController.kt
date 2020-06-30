package br.com.associacaoshare.controller

import br.com.associacaoshare.model.Link
import br.com.associacaoshare.model.Noticia
import br.com.associacaoshare.model.Pagina
import br.com.associacaoshare.model.Pessoa
import br.com.associacaoshare.model.api.APILink
import br.com.associacaoshare.model.api.APINoticia
import br.com.associacaoshare.model.api.APIPagina
import br.com.associacaoshare.model.api.APIPessoa
import br.com.associacaoshare.model.dao.DataAccessObject
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.EndpointGroup
import io.javalin.http.Context
import org.jsoup.Jsoup
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class APIController(override val kodein: Kodein) : EndpointGroup, KodeinAware {
    private val dao: DataAccessObject by instance()
    private val endpoints = ArrayList<String>()

    override fun addEndpoints() {
        get { ctx ->
            ctx.json(mapOf("endpoints" to endpoints))
        }
        getEndpoint("noticias") { ctx ->
            ctx.json(
                dao.allNoticias().map { it.toAPI(dao) }
            )
        }
        getEndpoint("paginas") { ctx ->
            ctx.json(
                dao.allPaginas().map { it.toAPI(dao) }
            )
        }
        getEndpoint("links") { ctx ->
            ctx.json(
                dao.allLinks().map { it.toAPI() }
            )
        }
    }

    private fun getEndpoint(path: String, handler: (Context) -> Unit) {
        get(path, handler)
        endpoints += path
    }

    private fun Noticia.toAPI(dao: DataAccessObject): APINoticia {
        return APINoticia(
            id,
            titulo,
            html,
            Jsoup.parseBodyFragment(html, "/").text(),
            dao.getPessoa(criadoPorPessoa)!!.toAPI(),
            dataCriacao,
            dataModificacao,
            ultimaModificacaoPorPessoa?.let(dao::getPessoa)?.toAPI()
        )
    }

    private fun Pagina.toAPI(dao: DataAccessObject): APIPagina {
        return APIPagina(
            linkPagina,
            titulo,
            html,
            Jsoup.parseBodyFragment(html, "/").text(),
            dao.getPessoa(criadoPorPessoa)!!.toAPI(),
            dataCriacao,
            dataModificacao,
            ultimaModificacaoPorPessoa?.let(dao::getPessoa)?.toAPI()
        )
    }

    private fun Pessoa.toAPI(): APIPessoa {
        return APIPessoa(id, nome, dataCriacao)
    }

    private fun Link.toAPI(): APILink {
        return APILink(nome, href)
    }
}

