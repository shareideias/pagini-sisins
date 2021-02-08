package br.com.associacaoshare.view.alunos

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAlunoView
import io.javalin.http.Context
import kotlinx.html.*

class Lista1View(private val errormsg: String?, private val participante: Participante, private val cursos: List<Curso>, private var interruptor: Int) : SisInsAlunoView() {
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

        header {
            nav("nav-wrapper transparent") {
                div("container") {
                    /*a("brand-logo") {
                        img("Logo da Share", "/img/navbar-brand.png", "share-brand")
                    }*/
                    a("#", classes = "sidenav-trigger") {
                        attributes["data-target"] = "mobile-menu"
                        i("material-icons") { +"menu" }
                    }

                    ul("right hide-on-med-and-down") {
                        li {
                            a("/inscricoes/logout", classes = "link_menu desknav") { +"Logout" }
                        }
                    }
                    ul("sidenav lighten-2") {
                        id = "mobile-menu"
                        li {
                            a("/inscricoes/logout", classes = "link_menu") { +"Logout" }
                        }
                    }
                }
            }
        }



        div("row") {
            div("col s12 m12 l3 offset-l3") {
                div("card") {
                    div("card-image") {
                        img("Logo da Share", "../../img/share-logo.png", "logo")
                    }
                }
            }
            div("center col s12 m12 l3") {

                h5 { +"Cursos disponíveis." }
                h5 { +"Inscreva-se em sua primeira opção:" }

                div(classes = "board") {
                    ul("collection with-header") {
                        cursos.forEach {
                            if(participante.curso2_id != it.id){
                                li("collection-item") {
                                    span("title") {
                                        b { +it.nome }
                                    }

//                                    form("/inscricoes/alunos/redacao_entrada1?id=${it.id}", classes = "col s12 addform", method = FormMethod.get) {

                                        if (interruptor == 1){
                                            a("/inscricoes/alunos/redacao_entrada1?id=${it.id}", classes = "secondary-content") {
                                                button(type = ButtonType.button, classes = "secondary-content") {
                                                    i("material-icons") { +"add" }
                                                }
                                            }
                                        }
//                                    }


                                    br {}
                                    p("horario") { +it.horario }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
