package br.com.associacaoshare.view

import br.com.associacaoshare.model.Noticia
import br.com.associacaoshare.model.page.IndexViewModel
import br.com.associacaoshare.utils.compressSpaces
import br.com.associacaoshare.utils.limit
import br.com.associacaoshare.view.base.PagIniView
import br.com.associacaoshare.view.base.PagIniView.Type.INDEX
import io.javalin.http.Context
import kotlinx.html.*
import org.jsoup.Jsoup
import java.time.format.DateTimeFormatter

class CursoView(override val model: IndexViewModel) : PagIniView(INDEX) {
    override val pageTitle = "Cursos"

    override fun MAIN.renderMain(ctx: Context) {
        link("/css/pagini_cursos.css", "stylesheet")

        h1 { +"""Cursos""" }
        div(classes = "row") {
            div(classes = "col s12 m10 offset-m1 l10 offset-l1") {
                ul(classes = "collapsible") {
                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Cursos de línguas"""
                        }
                        div(classes = "collapsible-body") {
                            ul {
                                li { +"""- Inglês Básico""" }
                                li { +"""- Inglês Conversação Pré-Intermediário""" }
                                li { +"""- Inglês Conversação Pós-Intermediário""" }
                                li { +"""- Inglês Pós-Intermediário""" }
                                li { +"""- Business English""" }
                                li { +"""- Espanhol Pré-Intermediário""" }
                                li { +"""- Espanhol Conversação Intermediário""" }
                                li { +"""- Francês Básico""" }
                                li { +"""- Alemão Básico""" }
                                li { +"""- Japonês Básico""" }
                                li { +"""- Português Instrumental""" }
                            }
                        }
                    }

                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Cursos de artes"""
                        }
                        div(classes = "collapsible-body") {
                            ul{
                                li { +"""- Improvisação Teatral""" }
                                li { +"""- Teatro""" }
                                li { +"""- Oficina de Mandalas""" }
                                li { +"""- Minicurso: Meio ambiente e qualidade de vida (inclusivo para PCD)""" }
                            }
                        }
                    }

                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Cursos de programação"""
                        }
                        div(classes = "collapsible-body ultimo") {
                            span { +"""- Introdução a programação em Python""" }
                        }
                    }
                }


            }

        }


        script("text/javascript", "/js/m_collapsible.js") {}
    }

}