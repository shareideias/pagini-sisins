package br.com.associacaoshare.view.alunos

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAlunoView
import io.javalin.http.Context
import kotlinx.html.*

class Prova2View(private val errormsg: String?, private val participante: Participante, private val curso: Curso) : SisInsAlunoView() {
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

        img("Logo da Share", "../img/share-logo.png", "logo")
        h3 { +"Prova de nivelamento" }


        h6("justify flow-text") {
            span(classes = "textinho") {
                +"A partir de agora você responderá um breve questionário que servirá para identificarmos "
                +"se você realmente possui o  conhecimento necessário para ingressar no nível intermediário ou avançado. "
                +"Fique tranquilo, pois mesmo que você não saiba responder a maioria das perguntas você ainda terá a vaga "
                +"garantida em um nível mais baixo, caso seja selecionado. Por ser uma prova apenas para avaliarmos seus conhecimentos "
                +"pedimos para que realmente deixe em branco as questões que você não souber."
            }
        }
        form("ProvaProc2", method = FormMethod.post) {
            h6 {
                +"${curso.pergunta1}"
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt1"
                        name = "resposta1_c2"
                        value = "1"
                    }
                    span {
                        +"${curso.alternativa11}"
                    }
                }
            }

            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt2"
                        name = "resposta1_c2"
                        value = "2"
                    }
                    span { +"${curso.alternativa12}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt3"
                        name = "resposta1_c2"
                        value = "3"
                    }
                    span { +"${curso.alternativa13}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt4"
                        name = "resposta1_c2"
                        value = "4"
                    }
                    span { +"${curso.alternativa14}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt5"
                        name = "resposta1_c2"
                        value = "5"
                    }
                    span { +"${curso.alternativa15}" }
                }
            }

            h6 {
                +"${curso.pergunta2}"
            }

            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt1"
                        name = "resposta2_c2"
                        value = "1"
                    }
                    span { +"${curso.alternativa21}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt2"
                        name = "resposta2_c2"
                        value = "2"
                    }
                    span { +"${curso.alternativa22}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt3"
                        name = "resposta2_c2"
                        value = "3"
                    }
                    span { +"${curso.alternativa23}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt4"
                        name = "resposta2_c2"
                        value = "4"
                    }
                    span { +"${curso.alternativa24}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt5"
                        name = "resposta2_c2"
                        value = "5"
                    }
                    span { +"${curso.alternativa25}" }
                }
            }


            h6 {
                span { +"${curso.pergunta3}" }
            }

            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt1"
                        name = "resposta3_c2"
                        value = "1"
                    }
                    span { +"${curso.alternativa31}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt2"
                        name = "resposta3_c2"
                        value = "2"
                    }
                    span { +"${curso.alternativa32}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt3"
                        name = "resposta3_c2"
                        value = "3"
                    }
                    span { +"${curso.alternativa33}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt4"
                        name = "resposta3_c2"
                        value = "4"
                    }
                    span { +"${curso.alternativa34}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt5"
                        name = "resposta3_c2"
                        value = "5"
                    }
                    span { +"${curso.alternativa35}" }
                }
            }


            h6 {
                +"${curso.pergunta4}"
            }

            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt1"
                        name = "resposta4_c2"
                        value = "1"
                    }
                    span { +"${curso.alternativa41}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt2"
                        name = "resposta4_c2"
                        value = "2"
                    }
                    span { +"${curso.alternativa42}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt3"
                        name = "resposta4_c2"
                        value = "3"
                    }
                    span { +"${curso.alternativa43}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt4"
                        name = "resposta4_c2"
                        value = "4"
                    }
                    span { +"${curso.alternativa44}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt5"
                        name = "resposta4_c2"
                        value = "5"
                    }
                    span { +"${curso.alternativa45}" }
                }
            }


            h6 {
                +"${curso.pergunta5}"
            }

            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt1"
                        name = "resposta5_c2"
                        value = "1"
                    }
                    span { +"${curso.alternativa51}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt2"
                        name = "resposta5_c2"
                        value = "2"
                    }
                    span { +"${curso.alternativa52}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt3"
                        name = "resposta5_c2"
                        value = "3"
                    }
                    span { +"${curso.alternativa53}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt4"
                        name = "resposta5_c2"
                        value = "4"
                    }
                    span { +"${curso.alternativa54}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt5"
                        name = "resposta5_c2"
                        value = "5"
                    }
                    span { +"${curso.alternativa55}" }
                }
            }


            h6 {
                +"${curso.pergunta6}"
            }

            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt1"
                        name = "resposta6_c2"
                        value = "1"
                    }
                    span { +"${curso.alternativa61}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt2"
                        name = "resposta6_c2"
                        value = "2"
                    }
                    span { +"${curso.alternativa62}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt3"
                        name = "resposta6_c2"
                        value = "3"
                    }
                    span { +"${curso.alternativa63}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt4"
                        name = "resposta6_c2"
                        value = "4"
                    }
                    span { +"${curso.alternativa64}" }
                }
            }
            p {
                label {
                    input(InputType.radio, classes = "with-gap") {
                        id = "alt5"
                        name = "resposta6_c2"
                        value = "5"
                    }
                    span { +"${curso.alternativa65}" }
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
