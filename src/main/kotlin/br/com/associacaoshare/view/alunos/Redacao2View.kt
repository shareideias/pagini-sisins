package br.com.associacaoshare.view.alunos

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAlunoView
import io.javalin.http.Context
import kotlinx.html.*

class Redacao2View(private val errormsg: String?, private val participante: Participante, private val cursos: Curso, private var interruptor: Int) : SisInsAlunoView() {
    override val pageTitle = "Redação"

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

        div("container center col s12") {

            h4 { +"Texto do curso: ${cursos.nome}" }
            br {  }

            if(cursos.categoria.toInt() == 1){
                h6 { +"Antes de fazer a prova de nivelamento, pedimos para que você escreva em algumas linhas por que gostaria de fazer um curso na Share. Lembre-se, esse é um dos critérios mais importantes para ser selecionado." }
            } else {
                h6 { +"Para finalizar, pedimos para que você escreva em algumas linhas por que gostaria de fazer este curso na Share. Lembre-se, esse é um dos critérios mais importantes para ser selecionado." }
            }


            br {  }
            br {  }
            br {  }


            form("CadastraCurso2", classes = "col s12 center", method = FormMethod.post) {
                input(InputType.number, classes = "validate invisible") {
                    id = "inputId"
                    name = "id"
                    value = cursos.id.toString()
                }

                input(InputType.number, classes = "validate invisible") {
                    id = "inputCategoria"
                    name = "categoria"
                    value = cursos.categoria
                }

//                div("input-field col s12 mb-0 perguntas") {
//                    label {
//                        htmlFor = "inputRedacao_entrada"
//
//                    }
                div(classes = "input-field") {
                    textArea(classes = "validate materialize-textarea") {
                        id = "inputRedacao_entrada"
                        name = "redacao2"
                        required = true
                    }
                    label{
                        htmlFor="inputRedacao_entrada"
                        +"Digite aqui"
                    }

                }
//                }
                br {  }
                br {  }
                if (interruptor == 1) {
                    button(type = ButtonType.submit, classes="entrar waves-effect waves-light btn" ) {
                        +"Enviar"
                    }
                }
            }

        }

    }
}

