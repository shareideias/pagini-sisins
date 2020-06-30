package br.com.associacaoshare.view.alunos

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAlunoView
import io.javalin.http.Context
import kotlinx.html.*

class InscricoesAlunoView(private val errormsg: String?, private val participante: Participante, private val curso1: Curso?, private val curso2: Curso?, private var interruptor: Int)  : SisInsAlunoView() {
    override val pageTitle: String
        get() = "Inscrição Share"
    override fun BODY.renderBody(ctx: Context) {
        link(type = "text/css", rel = "stylesheet", href = "/css/sisins_inscricoes.css")

        link(type = "text/css", rel = "stylesheet", href = "/css/alerts.css")

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


        if (!errormsg.isNullOrEmpty()) {
            div("materialert error") {
                div("material-icons") { +"error_outline" }
                +"$errormsg"
            }
        }

        div("row"){
            div("col s12 m0 l3")
            div("col s12 m12 l3"){
                div("card"){
                    div("card-image"){
                        img("Logo da Share", "../img/share-logo.png", "logo")
                    }
                }
            }
            div("center col s12 m12 l3"){
                a("/inscricoes/alunos/editar", classes = "center atualizar waves-effect waves-light btn"){
                    +"Atualizar perfil"
                }
                h5 { +"Seus cursos:" }

                if(interruptor == 0)
                    h6{+"As inscrições estão fechadas no momento."}

                ul("collection with-header"){

                    if(participante.curso1_id == curso1?.id && participante.curso1_id != null){
                        li("collection-item bigitem") {
                            span("title") {
                                b {
                                    if (curso1 != null) {
                                        +"${curso1.nome}"
                                    }
                                }
                            }

                            form("/inscricoes/alunos/DeleteCurso1", classes = "col s12 addform secondary-content", method = FormMethod.post) {
                                input(InputType.number, classes = "validate invisible") {
                                    id = "inputId"
                                    name = "id"
                                    if (curso1 != null) {
                                        value = curso1.id.toString()
                                    }
                                }
                                if(interruptor == 1) {
                                    button(type = ButtonType.submit, classes = "secondary-content") {
                                        i("material-icons") { +"delete" }
                                    }
                                }
                            }

                            br {}
                            p("horario") {
                                if (curso1 != null) {
                                    +"${curso1.horario}"
                                }
                            }
                        }

                    } else{
                        li("collection-item") {
                            div {
                                i { +"Curso não selecionado" }
                                if(interruptor == 1) {
                                    a("/inscricoes/alunos/curso1", classes = "secondary-content") {
                                        i("material-icons") { +"add" }
                                    }
                                }
                            }
                        }
                    }

                    if(participante.curso2_id == curso2?.id && participante.curso2_id != null){
                        li("collection-item bigitem") {
                            span("title") {
                                b {
                                    if (curso2 != null) {
                                        +"${curso2.nome}"
                                    }
                                }
                            }

                            form("/inscricoes/alunos/DeleteCurso2", classes = "col s12 addform secondary-content", method = FormMethod.post) {
                                input(InputType.number, classes = "validate invisible") {
                                    id = "inputId"
                                    name = "id"
                                    if (curso2 != null) {
                                        value = curso2.id.toString()
                                    }
                                }
                                if(interruptor == 1) {
                                    button(type = ButtonType.submit, classes = "secondary-content") {
                                        i("material-icons") { +"delete" }
                                    }
                                }
                            }

                            br {}
                            p("horario") {
                                if (curso2 != null) {
                                    +"${curso2.horario}"
                                }
                            }
                        }
                    } else{
                        li("collection-item") {
                            div {
                                i { +"Curso não selecionado" }
                                if(interruptor == 1) {
                                    a("/inscricoes/alunos/curso2", classes = "secondary-content") {
                                        i("material-icons") { +"add" }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
