package br.com.associacaoshare.view.alunos

import br.com.associacaoshare.model.page.ListaViewModel
import br.com.associacaoshare.view.base.SisInsAlunoView
import io.javalin.http.Context
import kotlinx.html.*

class ListaView(private val errormsg: String?, private val model: ListaViewModel) : SisInsAlunoView() {
    override val pageTitle = "Lista de Inscrições"

    override fun BODY.renderBody(ctx: Context) {
        link(type = "text/css", rel = "stylesheet", href = "/css/sisins_lista.css")

        link(type = "text/css", rel = "stylesheet", href = "/css/alerts.css")
        if (!errormsg.isNullOrEmpty()) {
            div("materialert error") {
                div("material-icons") { +"error_outline" }
                +"$errormsg"
            }
        }

        div("row") {
            div("col s12 m12 l3 offset-l3") {
                div("card") {
                    div("card-image") {
                        img("Logo da Share", "../img/share-logo.png", "logo")
                    }
                }
            }
            div("center col s12 m12 l3") {

                h5 { +"Seus cursos:" }

                div(classes = "board") {
                    ul("collection with-header") {
                        for (curso in model.cursos)
                            li("collection-item") {
                                span("title") {
                                    b { +curso.nome }
                                }

                                a("inscricoes2.html", classes = "secondary-content") {
                                    i("material-icons") { +"add" }
                                }

                                p {
                                    +curso.categoria
                                    +" - "
                                    +curso.horario
                                }
                            }
                    }

                }
            }
        }

    }
}
