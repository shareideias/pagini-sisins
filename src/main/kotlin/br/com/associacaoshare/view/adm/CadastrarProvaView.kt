package br.com.associacaoshare.view.adm

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.view.base.SisInsAdmView
import io.javalin.http.Context
import kotlinx.html.*

class CadastrarProvaView(private val errormsg: String?, private val curso: Curso?) : SisInsAdmView() {
    override val pageTitle: String = "Cadastro de Prova de nivelamento"

    override fun MAIN.renderMain(ctx: Context) {
        h3{
         +"Criação da Prova de nivelamento"}


        div(classes = "row rowzeras") {
            form("adicionaProva?id=${curso?.id}", classes = "col s12", method = FormMethod.post) {
                //Pergunta 1
                h4 {
                    +"Pergunta 1"
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputPergunta1"
                            +"Pergunta"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputPergunta1"
                            name = "pergunta1"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa11"
                            +"Alternativa 1"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa11"
                            name = "alternativa11"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa12"
                            +"Alternativa 2"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa12"
                            name = "alternativa12"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa13"
                            +"Alternativa 3"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa13"
                            name = "alternativa13"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa14"
                            +"Alternativa 4"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa14"
                            name = "alternativa14"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa15"
                            +"Alternativa 5"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa15"
                            name = "alternativa15"
                        }
                    }
                }

                //Pergunta 2
                h4 {
                    +"Pergunta 2"
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputPergunta2"
                            +"Pergunta"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputPergunta2"
                            name = "pergunta2"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa21"
                            +"Alternativa 1"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa21"
                            name = "alternativa21"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa22"
                            +"Alternativa 2"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa22"
                            name = "alternativa22"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa23"
                            +"Alternativa 3"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa23"
                            name = "alternativa23"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa24"
                            +"Alternativa 4"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa24"
                            name = "alternativa24"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa25"
                            +"Alternativa 5"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa25"
                            name = "alternativa25"
                        }
                    }
                }

                //fim Pergunta 2

                //Pergunta 3
                h4 {
                    +"Pergunta 3"
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputPergunta3"
                            +"Pergunta"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputPergunta3"
                            name = "pergunta3"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa31"
                            +"Alternativa 1"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa31"
                            name = "alternativa31"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa32"
                            +"Alternativa 2"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa32"
                            name = "alternativa32"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa33"
                            +"Alternativa 3"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa33"
                            name = "alternativa33"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa34"
                            +"Alternativa 4"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa34"
                            name = "alternativa34"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa35"
                            +"Alternativa 5"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa35"
                            name = "alternativa35"
                        }
                    }
                }

                //fim Pergunta 3

                //Pergunta 4
                h4 {
                    +"Pergunta 4"
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputPergunta4"
                            +"Pergunta"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputPergunta4"
                            name = "pergunta4"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa41"
                            +"Alternativa 1"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa41"
                            name = "alternativa41"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa42"
                            +"Alternativa 2"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa42"
                            name = "alternativa42"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa43"
                            +"Alternativa 3"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa43"
                            name = "alternativa43"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa44"
                            +"Alternativa 4"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa44"
                            name = "alternativa44"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa45"
                            +"Alternativa 5"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa45"
                            name = "alternativa45"
                        }
                    }
                }

                //fim Pergunta 4

                //Pergunta 5
                h4 {
                    +"Pergunta 5"
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputPergunta5"
                            +"Pergunta"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputPergunta5"
                            name = "pergunta5"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa51"
                            +"Alternativa 1"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa51"
                            name = "alternativa51"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa52"
                            +"Alternativa 2"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa52"
                            name = "alternativa52"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa53"
                            +"Alternativa 3"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa53"
                            name = "alternativa53"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa54"
                            +"Alternativa 4"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa54"
                            name = "alternativa54"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa55"
                            +"Alternativa 5"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa55"
                            name = "alternativa55"
                        }
                    }
                }

                //fim Pergunta 5

                //Pergunta 6
                h4 {
                    +"Pergunta 6"
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputPergunta6"
                            +"Pergunta"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputPergunta6"
                            name = "pergunta6"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa61"
                            +"Alternativa 1"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa61"
                            name = "alternativa61"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa62"
                            +"Alternativa 2"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa62"
                            name = "alternativa62"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa63"
                            +"Alternativa 3"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa63"
                            name = "alternativa63"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa64"
                            +"Alternativa 4"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa64"
                            name = "alternativa64"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputAlternativa65"
                            +"Alternativa 5"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputAlternativa65"
                            name = "alternativa65"
                        }
                    }
                }

                //fim Pergunta 6


                //botao
                div(classes = "row") {
                    button(type = ButtonType.submit, classes = "entrar waves-effect waves-light btn") {
                        id = "btnParte4Cadastrar"
                        +"Cadastrar"
                    }
                }
                //fim botao
            }
        }

        script("text/javascript", "/js/materialize.min.js") {}
        script("text/javascript", "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js") {}
        script("text/javascript", "https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js") {}
        script("text/javascript", "/js/selector.js") {}
    }
}