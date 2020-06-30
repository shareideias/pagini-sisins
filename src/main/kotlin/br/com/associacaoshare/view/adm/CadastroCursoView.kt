package br.com.associacaoshare.view.adm

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.view.base.SisInsAdmView
import io.javalin.http.Context
import kotlinx.html.*

class CadastroCursoView(private val errormsg: String?) : SisInsAdmView() {
    override val pageTitle: String = "Cadastro de curso"

    override fun MAIN.renderMain(ctx: Context) {
        h3 { +"Cadastrar curso" }

        div("row rowzeras") {
            form("adicionaCurso", classes = "col s12", method = FormMethod.post) {

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputNome"
                            +"Nome do curso"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputNome"
                            name = "nome"
                        }
                    }
                }

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputHorario"
                            +"Horário"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputHorario"
                            name = "horario"
                        }
                    }
                }


                div("input-field col s12 mb-0") {
                    +"Precisa de prova de nivelamento?"
                    label {
                        htmlFor = "selectCategoria"
                    }
                    select {
                        id = "selectCategoria"
                        name = "categoria"
                        option {
                            value = "1"
                            +"Sim"
                        }
                        option {
                            selected = true
                            value = "2"
                            +"Não"
                        }
                    }
                }

                button(type = ButtonType.submit, classes = "entrar waves-effect waves-light btn") {
                    id = "btnParte4Cadastrar"
                    +"Cadastrar"
                }

            }
        }

        script {
            unsafe {
                +"""
                document.addEventListener('DOMContentLoaded', function() {
                    M.Datepicker.init(document.querySelectorAll('.datepicker'), { format: 'dd/mm/yyyy' });
                });
                """.trimIndent()
            }
        }
        script("text/javascript", "/js/materialize.min.js") {}
        script("text/javascript", "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js") {}
        script("text/javascript", "https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js") {}
        script("text/javascript", "/js/selector.js") {}

    }
}