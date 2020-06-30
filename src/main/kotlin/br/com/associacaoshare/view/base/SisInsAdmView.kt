package br.com.associacaoshare.view.base

import io.javalin.http.Context
import kotlinx.html.*

abstract class SisInsAdmView : HtmlBuilderView() {
    abstract val pageTitle: String

    override fun HTML.render(ctx: Context) {
        head {
            meta(charset = "utf-8")
            meta("viewport", "width=device-width, initial-scale=1.0")

            title("Sistema de Inscrições · $pageTitle")

            link(rel = "icon", href = "/img/globo.png")
            link(href = "https://fonts.googleapis.com/icon?family=Material+Icons", rel = "stylesheet")
            link(type = "text/css", rel = "stylesheet", href = "/css/materialize.min.css")
            link(type = "text/css", rel = "stylesheet", href = "/css/sisins_adm.css")

            script("text/javascript", "/js/materialize.min.js") {}
        }

        body {
            header { renderHeader() }
            main { renderMain(ctx) }
            renderScripts()
        }
    }

    private fun HEADER.renderHeader() {
        nav(classes = "nav-wrapper transparent") {
            div(classes = "container") {
                a(href = "cursos.html", classes = "brand-logo") {
                    img(classes = "imagem_logo", alt = "Logo da Share", src = "/img/globo.png")
                }
                a(href = "", classes = "sidenav-trigger") {
                    attributes["data-target"] = "mobile-menu"
                    i(classes = "material-icons") { +"menu" }
                }

                class L(val nome: String, val href: String)
                val links = emptyArray<L>() //TODO Change

                ul(classes = "right hide-on-med-and-down") {
                    li {
                        a(classes = "link_menu desknav", href = "/inscricoes/adm") { +"Cursos" }
                    }
                    li {
                        a(classes = "link_menu desknav", href = "/inscricoes/adm/inscricoesgerais") { +"Inscrições" }
                    }
                    li {
                        a("/inscricoes/logout", classes = "link_menu desknav") { +"Logout" }
                    }
                }
                ul(classes = "sidenav lighten-2") {
                    id = "mobile-menu"

                    li {
                        a(classes = "link_menu", href = "/inscricoes/adm") { +"Cursos" }
                    }
                    li {
                        a(classes = "link_menu", href = "/inscricoes/adm/inscricoesgerais") { +"Inscrições" }
                    }
                    li {
                        a("/inscricoes/logout", classes = "link_menu ") { +"Logout" }
                    }
                }
            }
        }
    }

    abstract fun MAIN.renderMain(ctx: Context)

    private fun BODY.renderScripts() {
        script(src = "/js/materialize.min.js") {}
        script {
            unsafe {
                +"""
                document.addEventListener('DOMContentLoaded', function() {
                    var instances = M.Sidenav.init(document.querySelectorAll('.sidenav'), {});
                });
            """.trimIndent()
            }
        }
    }
}