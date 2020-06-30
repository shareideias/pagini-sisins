package br.com.associacaoshare.view.adm

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.view.base.SisInsAdmView
import io.javalin.http.Context
import kotlinx.html.*

class EdicaoCursoView(private val errormsg: String?, private val curso: Curso) : SisInsAdmView() {
    override val pageTitle: String = "Cadastro de curso"

    override fun MAIN.renderMain(ctx: Context) {
        if (!errormsg.isNullOrEmpty()) {
            div("materialert error") {
                div("material-icons") { +"error_outline" }
                +"$errormsg"
            }
        }

        h3 { +"Editar curso" }

        div("row rowzeras") {
            form("cursoeditado?id=${curso?.id}", classes = "col s12", method = FormMethod.post) {

                div(classes = "row") {
                    div("input-field col s12 mb-0") {
                        label("perguntas"){
                            htmlFor = "inputNome"
                            +"Nome do curso"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputNome"
                            name = "nome"
                            value = curso.nome
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
                            value = curso.horario
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

                        if(curso.categoria == "1"){
                            option {
                                selected = true
                                value = "1"
                                +"Sim"
                            }
                            option {
                                value = "2"
                                +"Não"
                            }
                        }
                        else if(curso.categoria == "2"){
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
                }

                button(type = ButtonType.submit, classes = "entrar waves-effect waves-light btn") {
                    id = "btnParte4Cadastrar"
                    +"Editar"
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