package br.com.associacaoshare.view.alunos

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAlunoView
import io.javalin.http.Context
import kotlinx.html.*

class ProvaView(private val errormsg: String?, private val participante: Participante, private val curso: Curso) : SisInsAlunoView() {
    override val pageTitle: String
        get() = "Prova"

    override fun BODY.renderBody(ctx: Context) {
        link(type = "text/css", rel = "stylesheet", href = "/css/sisins_prova.css")

        link(type = "text/css", rel = "stylesheet", href = "/css/alerts.css")
        if (!errormsg.isNullOrEmpty()) {
            div("materialert error") {
                div("material-icons") { +"error_outline" }
                +"$errormsg"
            }
        }

        img("Logo da Share", "../../img/share-logo.png", "logo")
        h3 { +"Prova de nivelamento" }


        h6("justify flow-text") {
            span(classes = "textinho") {
                +"A partir de agora você responderá um breve questionário que servirá para identificarmos "
                +"se você realmente possui o  conhecimento necessário para ingressar no nível intermediário ou avançado. "
            }
        }
        form("ProvaProc", method = FormMethod.post) {
            h6 {
                +"${curso.pergunta1}"
            }
            fieldSet {
                id = "questao1"
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao1"
                            name = "resposta1_c1"
                            value = "1"
                            required = true
                        }
                        span {
                            +"${curso.alternativa11}"
                        }
                    }
                }

                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao1"
                            name = "resposta1_c1"
                            value = "2"
                            required = true
                        }
                        span { +"${curso.alternativa12}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao1"
                            name = "resposta1_c1"
                            value = "3"
                            required = true
                        }
                        span { +"${curso.alternativa13}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao1"
                            name = "resposta1_c1"
                            value = "4"
                            required = true
                        }
                        span { +"${curso.alternativa14}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao1"
                            name = "resposta1_c1"
                            value = "5"
                            required = true
                        }
                        span { +"${curso.alternativa15}" }
                    }
                }
            }

            h6 {
                +"${curso.pergunta2}"
            }
            fieldSet {
                id = "questao2"
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao2"
                            name = "resposta2_c1"
                            value = "1"
                            required = true
                        }
                        span { +"${curso.alternativa21}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao2"
                            name = "resposta2_c1"
                            value = "2"
                            required = true
                        }
                        span { +"${curso.alternativa22}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao2"
                            name = "resposta2_c1"
                            value = "3"
                            required = true
                        }
                        span { +"${curso.alternativa23}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao2"
                            name = "resposta2_c1"
                            value = "4"
                            required = true
                        }
                        span { +"${curso.alternativa24}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao2"
                            name = "resposta2_c1"
                            value = "5"
                            required = true
                        }
                        span { +"${curso.alternativa25}" }
                    }
                }
            }

            h6 {
                span { +"${curso.pergunta3}" }
            }

            fieldSet {
                id = "questao3"
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao3"
                            name = "resposta3_c1"
                            value = "1"
                            required = true
                        }
                        span { +"${curso.alternativa31}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao3"
                            name = "resposta3_c1"
                            value = "2"
                            required = true
                        }
                        span { +"${curso.alternativa32}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao3"
                            name = "resposta3_c1"
                            value = "3"
                            required = true
                        }
                        span { +"${curso.alternativa33}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao3"
                            name = "resposta3_c1"
                            value = "4"
                            required = true
                        }
                        span { +"${curso.alternativa34}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao3"
                            name = "resposta3_c1"
                            value = "5"
                            required = true
                        }
                        span { +"${curso.alternativa35}" }
                    }
                }
            }

            h6 {
                +"${curso.pergunta4}"
            }

            fieldSet {
                id = "questao4"
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao4"
                            name = "resposta4_c1"
                            value = "1"
                            required = true
                        }
                        span { +"${curso.alternativa41}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao4"
                            name = "resposta4_c1"
                            value = "2"
                            required = true
                        }
                        span { +"${curso.alternativa42}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao4"
                            name = "resposta4_c1"
                            value = "3"
                            required = true
                        }
                        span { +"${curso.alternativa43}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao4"
                            name = "resposta4_c1"
                            value = "4"
                            required = true
                        }
                        span { +"${curso.alternativa44}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao4"
                            name = "resposta4_c1"
                            value = "5"
                            required = true
                        }
                        span { +"${curso.alternativa45}" }
                    }
                }
            }


            h6 {
                +"${curso.pergunta5}"
            }

            fieldSet {
                id = "questao5"
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao5"
                            name = "resposta5_c1"
                            value = "1"
                            required = true
                        }
                        span { +"${curso.alternativa51}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao5"
                            name = "resposta5_c1"
                            value = "2"
                            required = true
                        }
                        span { +"${curso.alternativa52}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao5"
                            name = "resposta5_c1"
                            value = "3"
                            required = true
                        }
                        span { +"${curso.alternativa53}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao5"
                            name = "resposta5_c1"
                            value = "4"
                            required = true
                        }
                        span { +"${curso.alternativa54}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao5"
                            name = "resposta5_c1"
                            value = "5"
                            required = true
                        }
                        span { +"${curso.alternativa55}" }
                    }
                }
            }

            h6 {
                +"${curso.pergunta6}"
            }

            fieldSet {
                id = "questao6"
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao6"
                            name = "resposta6_c1"
                            value = "1"
                            required = true
                        }
                        span { +"${curso.alternativa61}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao6"
                            name = "resposta6_c1"
                            value = "2"
                            required = true
                        }
                        span { +"${curso.alternativa62}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao6"
                            name = "resposta6_c1"
                            value = "3"
                            required = true
                        }
                        span { +"${curso.alternativa63}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao6"
                            name = "resposta6_c1"
                            value = "4"
                            required = true
                        }
                        span { +"${curso.alternativa64}" }
                    }
                }
                p {
                    label {
                        input(InputType.radio, classes = "with-gap") {
                            id = "questao6"
                            name = "resposta6_c1"
                            value = "5"
                            required = true
                        }
                        span { +"${curso.alternativa65}" }
                    }
                }
            }


            div(classes = "row") {
                button(type = ButtonType.submit, classes = "entrar waves-effect waves-light btn") {
                    +"Enviar"
                }
            }
        }
    }
}
