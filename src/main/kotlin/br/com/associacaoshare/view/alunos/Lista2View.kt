package br.com.associacaoshare.view.alunos

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAlunoView
import io.javalin.http.Context
import kotlinx.html.*

class Lista2View(private val errormsg: String?, private val participante: Participante, private val cursos: List<Curso>, private var interruptor: Int) : SisInsAlunoView() {
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

        header{
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

                h5 { +"Seus cursos:" }

                div(classes="board"){
                    ul("collection with-header") {
                        cursos.forEach {
                            if(participante.curso1_id != it.id){
                                li("collection-item") {
                                    span("title") {
                                        b { +"${it.nome}" }
                                    }
                                    form("CadastraCurso2", classes = "col s12 addform", method = FormMethod.post) {
                                        input(InputType.number, classes = "validate invisible") {
                                            id = "inputId"
                                            name = "id"
                                            value = it.id.toString()
                                        }
                                        input(InputType.number, classes = "validate invisible") {
                                            id = "inputCategoria"
                                            name = "categoria"
                                            value = it.categoria
                                        }
                                        if(interruptor == 1) {
                                            button(type = ButtonType.submit, classes = "secondary-content") {
                                                i("material-icons") { +"add" }
                                            }
                                        }
                                    }
                                    br{}
                                    p ("horario"){ +"${it.horario}" }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
