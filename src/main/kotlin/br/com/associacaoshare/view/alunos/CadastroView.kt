package br.com.associacaoshare.view.alunos

import br.com.associacaoshare.view.base.SisInsAlunoView
import io.javalin.http.Context
import kotlinx.html.*

class CadastroView (private val errormsg: String?, private var interruptor: Int) : SisInsAlunoView() {
    override val pageTitle = "Cadastro"

    override fun BODY.renderBody(ctx: Context) {
        link(type = "text/css", rel = "stylesheet", href = "/css/sisins_cadastro.css")

        link(type = "text/css", rel = "stylesheet", href = "/css/alerts.css")
        if (!errormsg.isNullOrEmpty()) {
            div("materialert error") {
                div("material-icons") { +"error_outline" }
                +"$errormsg"
            }
        }

        img("Logo da Share", "../img/share-logo.png", "logo")
        h3 { +"Cadastro" }

        if (interruptor == 0) {
            h4 { +"As inscrições estão fechadas no momento. Confira as datas do próximo processo seletivo para alunos." }
        } else if (interruptor == 1){
            p("obscadastro"){+"*preencha obrigatoriamente todos os campos."}
            div("row") {
                form("CadastroProc", classes = "col s12", method = FormMethod.post) {
                    div("input-field col s12 mb-0") {
                        +"Responda em qual categoria se encaixa"
                        label {
                            htmlFor = "selectCategoria"
                        }
                        select {
                            id = "selectCategoria"
                            name = "categoria"
                            required = true
                            option {
                                disabled = true
                                selected = true
                                value = ""

                            }
                            option {
                                value = "1"
                                +"Pessoas com vínculo com a UFSCar."
                            }
                            option {
                                value = "2"
                                +"Alunos do terceiro ano do ensino médio (tendo no mínimo 16 anos)."
                            }
                            option {
                                value = "3"
                                +"Pessoas que não possuem vínculo com a UFSCar."
                            }
                        }
                    }


                    div("input-field col s12 mb-0") {
                        label("perguntas") {
                            htmlFor = "inputNome"
                            +"Nome completo"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputNome"
                            name = "nome"
                            required = true
                        }
                    }

                    div("input-field col s12 mb-0 perguntas") {
                        +"Data de nascimento (idade mínima: 16 anos)"
                        label {
                            htmlFor = "inputData_nascimento"

                        }
                        input(InputType.date, classes = "validate") {
                            id = "inputData_nascimento"
                            name = "data_nascimento"
                            required = true
                        }
                    }

                    div("input-field col s12 mb-0") {
                        label("perguntas") {
                            htmlFor = "inputTelefone"
                            +"DDD + telefone"
                        }
                        input(InputType.tel, classes = "validate") {
                            id = "inputTelefone"
                            name = "telefone"
                            required = true
                        }
                    }

                    div("input-field col s12 mb-0") {
                        label("perguntas") {
                            htmlFor = "inputEmail"
                            +"Email"
                        }
                        input(InputType.email, classes = "validate") {
                            id = "inputEmail"
                            name = "email"
                            required = true
                        }
                    }

                    div("input-field col s12 mb-0") {
                        label("perguntas") {
                            htmlFor = "inputPassword"
                            +"Senha"

                        }
                        input(InputType.password, classes = "validate") {
                            id = "inputPassword"
                            name = "password"
                            required = true
                        }
                    }

                    div("input-field col s12 mb-0") {
                        +"Você é estudante? E/ou trabalha?"
                        label {
                            htmlFor = "selectTipoSemVinculo"
                        }
                        select {
                            id = "selectTipoSemVinculo"
                            name = "tipo_sem_vinculo"
                            required = true
                            option {
                                disabled = true
                                selected = true
                                value = ""
                            }
                            option {
                                value = "1"
                                +"Trabalho e não estudo"
                            }
                            option {
                                value = "2"
                                +"Não trabalho e estudo"
                            }
                            option {
                                value = "3"
                                +"Não estudo e não trabalho"
                            }
                            option {
                                value = "4"
                                +"Estudo e trabalho"
                            }
                        }
                    }

                    div("input-field col s12 mb-0") {
                        +"Qual o seu vínculo com a UFSCAR?"
                        label {
                            htmlFor = "selectVinculoUfscar"
                        }
                        select {
                            id = "selectVinculoUfscar"
                            name = "vinculo_ufscar"
                            required = true
                            option {
                                disabled = true
                                selected = true
                                value = ""
                            }
                            option {
                                value = "1"
                                +"Professor"
                            }
                            option {
                                value = "2"
                                +"Técnico-Administrativo (TAs)"
                            }
                            option {
                                value = "3"
                                +"Aluno da Graduação"
                            }
                            option {
                                value = "4"
                                +"Aluno da Pós Graduação"
                            }
                            option {
                                value = "5"
                                +"Outro"
                            }
                            option {
                                value = "6"
                                +"Nenhum"
                            }
                        }
                    }

                    div("input-field col s12 mb-0 perguntas") {
                        +"Escola em que estuda e cidade (Se for aluno do ensino médio) "
                        label {
                            htmlFor = "inputEscola"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputEscola"
                            name = "escola"
                            required = true
                        }
                    }

                    div("input-field col s12 mb-0") {
                        +"Você leu atentamento o edital?"
                        label("perguntas") {
                            htmlFor = "inputEdital"

                        }

                        select {
                            id = "selectEdital"
                            name = "edital"
                            required = true
                            option {
                                disabled = true
                                selected = true
                                value = ""
                            }
                            option {
                                value = "1"
                                +"Sim, li e estou ciente dos termos do processo seletivo."
                            }
                            option {
                                value = "2"
                                +"Não"
                            }
                        }
                    }

                    div("input-field col s12 mb-0") {
                        +"Por onde conheceu a share?"
                        label("perguntas") {
                            htmlFor = "inputOnde_conheceu"
                        }
                        select {
                            id = "selectOndeConheceu"
                            name = "onde_conheceu"
                            required = true
                            option {
                                disabled = true
                                selected = true
                                value = ""
                            }
                            option {
                                value = "1"
                                +"Mídias Sociais (Facebook, Whatsapp, etc)"
                            }
                            option {
                                value = "2"
                                +"Recomendação"
                            }
                            option {
                                value = "3"
                                +"Outras Mídias (Jornais, Revistas, Televisão)"
                            }
                            option {
                                value = "4"
                                +"Outro"
                            }
                        }
                    }

                    div("input-field col s12 mb-0") {
                        +"Você já esteve na UFSCar antes?"
                        label("perguntas") {
                            htmlFor = "inputEsteve_ufscar"
                        }
                        select {
                            id = "selectEsteveUfscar"
                            name = "esteve_ufscar"
                            required = true
                            option {
                                disabled = true
                                selected = true
                                value = ""
                            }
                            option {
                                value = "1"
                                +"Sim"
                            }
                            option {
                                value = "2"
                                +"Não"
                            }
                        }
                    }

                    div("input-field col s12 mb-0") {
                        +"Você está ciente de que as aulas da Share acontecem na UFSCar Sorocaba, próximo à Salto de Pirapora?"
                        label("perguntas") {
                            htmlFor = "inputLocal_aulas"
                        }

                        select {
                            id = "selectLocal_aulas"
                            name = "local_aulas"
                            required = true
                            option {
                                disabled = true
                                selected = true
                                value = ""
                            }
                            option {
                                value = "1"
                                +"Sim"
                            }
                            option {
                                value = "2"
                                +"Não"
                            }
                        }
                    }

                    div("input-field col s12 mb-0 perguntas") {
                        +"Como voce descreveria a sua disponibilidade de tempo atualmente?"
                        label {
                            htmlFor = "inputDisponibilidade"

                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputDisponibilidade"
                            name = "disponibilidade"
                            required = true
                        }
                    }

                    div("input-field col s12 mb-0") {
                        +"Seu principal objetivo com o curso é:"
                        label("perguntas") {
                            htmlFor = "inputObjetivo"
                        }
                        select {
                            id = "selectObjetivo"
                            name = "objetivo"
                            required = true
                            option {
                                disabled = true
                                selected = true
                                value = ""
                            }
                            option {
                                value = "1"
                                +"Aprender algo novo"
                            }
                            option {
                                value = "2"
                                +"Ter um hobby/me distrair"
                            }
                            option {
                                value = "3"
                                +"Me atualizar para o mercado de trabalho"
                            }
                            option {
                                value = "4"
                                +"Conhecer pessoas novas"
                            }
                            option {
                                value = "5"
                                +"Conhecer mais sobre a área/ sanar minha curiosidade"
                            }
                            option {
                                value = "6"
                                +"Me preparar para provas (vestibulares,  provas da faculdade)"
                            }
                            option {
                                value = "7"
                                +"Me preparar para um intercâmbio"
                            }
                            option {
                                value = "8"
                                +"Outro"
                            }
                        }
                    }

                    div("input-field col s12 mb-0") {
                        +"Voce já se inscreveu em algum curso da share?"
                        label("perguntas") {
                            htmlFor = "inputCursou_share"
                        }
                        select {
                            id = "selectCursouShare"
                            name = "cursou_share"
                            required = true
                            option {
                                disabled = true
                                selected = true
                                value = ""
                            }
                            option {
                                value = "1"
                                +"Sim, e fui chamado"
                            }
                            option {
                                value = "2"
                                +"Não"
                            }
                            option {
                                value = "3"
                                +"Uma vez, e não fui chamado"
                            }
                            option {
                                value = "4"
                                +"Mais de uma vez, e nunca fui chamado."
                            }
                        }
                    }

                    div("input-field col s12 mb-0") {
                        +"Você está ciente de que desistir do curso, sem explicação plausível, "
                        +"impossibilitará o candidato de se inscrever para qualquer outro curso da Share, "
                        +"além de prejudicar sua turma?"
                        label("perguntas") {
                            htmlFor = "selectDesistencia"

                        }

                        select {
                            id = "selectDesistencia"
                            name = "desistencia"
                            required = true
                            option {
                                disabled = true
                                selected = true
                                value = ""
                            }
                            option {
                                value = "1"
                                +"Sim"
                            }
                            option {
                                value = "2"
                                +"Não"
                            }
                        }
                    }

                    div("input-field col s12 mb-0 perguntas") {
                        +"Para finalizar, pedimos para que você escreva em algumas linhas por que gostaria de fazer um curso na Share. Lembre-se, esse é um dos critérios mais importantes para ser selecionado (exceto: curso português para estrangeiros)."
                        label {
                            htmlFor = "inputRedacao_entrada"

                        }
                        textArea(classes = "validate materialize-textarea") {
                            id = "inputRedacao_entrada"
                            name = "redacao_entrada"
                            required = true
                        }
                    }
                    button(type = ButtonType.submit, classes = "entrar waves-effect waves-light btn") {
                        id = "btnParte4Cadastrar"
                        +"Cadastrar"
                    }
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
