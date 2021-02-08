package br.com.associacaoshare.view.adm

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAdmView
import io.javalin.http.Context
import kotlinx.html.*

class PerfilCandidatoView(private val errormsg: String?, private val participante: Participante, private val curso1: Curso?, private val curso2: Curso?) : SisInsAdmView() {
    override val pageTitle: String = "Candidato"

    override fun MAIN.renderMain(ctx: Context) {
        h4 { +participante.nome }
        article {
            p {
                b { +"Categoria:" }
                if (participante.categoria == 1) {
                    br { +"Pessoas com vínculo com a UFSCar." }
                } else if (participante.categoria == 2) {
                    br { +"Alunos do terceiro ano do ensino médio (tendo no mínimo 16 anos)." }
                } else if (participante.categoria == 3) {
                    br { +"Pessoas que não possuem vínculo com a UFSCar." }
                }
            }
            p {
                b { +"Vínculo:" }
                if (participante.vinculo_ufscar == 1) {
                    br { +"Professor" }
                } else if (participante.vinculo_ufscar == 2) {
                    br { +"Técnico-Administrativo (TAs)" }
                } else if (participante.vinculo_ufscar == 3) {
                    br { +"Aluno da Graduação" }
                } else if (participante.vinculo_ufscar == 4) {
                    br { +"Aluno da Pós Graduação" }
                } else if (participante.vinculo_ufscar == 5) {
                    br { +"Outro" }
                } else if (participante.vinculo_ufscar == 6) {
                    br { +"Nenhum" }
                }
            }
            p {
                b { +"Estudante e/ou trabalha:" }
                if (participante.tipo_sem_vinculo == 1) {
                    br { +"Trabalho e não estudo" }
                } else if (participante.tipo_sem_vinculo == 2) {
                    br { +"Não trabalho e estudo" }
                } else if (participante.tipo_sem_vinculo == 3) {
                    br { +"Não estudo e não trabalho" }
                } else if (participante.tipo_sem_vinculo == 4) {
                    br { +"Estudo e trabalho" }
                }
            }
            p {
                b { +"Escola a qual estuda se for do ensino médio:" }
                br { +participante.escola }
            }
            p {
                b { +"Data de nascimento:" }

                br { +"${participante.data_nascimento.dayOfMonth}/${participante.data_nascimento.monthValue}/${participante.data_nascimento.year}" }
            }
            p {
                b { +"Telefone:" }
                br { +participante.telefone }
            }
            p {
                b { +"Email:" }
                br { +participante.email }
            }
        }

        h4 { +"Inscrição" }
        article {
            p {
                b { +"Esteve antes na UFSCar:" }
                if (participante.esteve_ufscar == 1) {
                    br { +"Sim" }
                } else if (participante.esteve_ufscar == 2) {
                    br { +"Não" }
                }
            }
            p {
                b { +"Ciente de que as aulas acontecem na UFSCar Sorocaba:" }
                if (participante.local_aulas == 1) {
                    br { +"Sim" }
                } else if (participante.local_aulas == 2) {
                    br { +"Não" }
                }
            }
            p {
                b { +"Disponibilidade de tempo:" }
                br { +participante.disponibilidade }
            }
            p {
                b { +"Objetivo com o curso:" }
                if (participante.objetivo == 1) {
                    br { +"Aprender algo novo" }
                } else if (participante.objetivo == 2) {
                    br { +"Ter um hobby/me distrair" }
                } else if (participante.objetivo == 3) {
                    br { +"Me atualizar para o mercado de trabalho" }
                } else if (participante.objetivo == 4) {
                    br { +"Conhecer pessoas novas" }
                } else if (participante.objetivo == 5) {
                    br { +"Conhecer mais sobre a área/ sanar minha curiosidade" }
                } else if (participante.objetivo == 6) {
                    br { +"Me preparar para provas (vestibulares,  provas da faculdade)" }
                } else if (participante.objetivo == 7) {
                    br { +"Me preparar para um intercâmbio" }
                } else if (participante.objetivo == 8) {
                    br { +"Outro" }
                }
            }
            p {
                b { +"Inscrição em outros cursos da Share:" }
                if (participante.cursou_share == 1) {
                    br { +"Sim, e fui chamado" }
                } else if (participante.cursou_share == 2) {
                    br { +"Não" }
                } else if (participante.cursou_share == 3) {
                    br { +"Uma vez, e não fui chamado" }
                } else if (participante.cursou_share == 4) {
                    br { +"Mais de uma vez, e nunca fui chamado." }
                }
            }
            p {
                b { +"Ciente das consequências da desistências do curso:" }
                if (participante.desistencia == 1) {
                    br { +"Sim" }
                } else if (participante.desistencia == 2) {
                    br { +"Não" }
                }
            }
//            p {
//                b { +"Texto de por quê gostaria de fazer um curso na Share:" }
//                br { +participante.redacao_entrada }
//            }
            p {
                b { +"Leitura do edital:" }
                if (participante.esteve_ufscar == 1) {
                    br { +"Sim" }
                } else if (participante.esteve_ufscar == 2) {
                    br { +"Não" }
                }
            }
            p {
                b { +"Como você ficou sabendo da Share?" }
                if (participante.onde_conheceu == 1) {
                    br { +"Instagram" }
                } else if (participante.onde_conheceu == 2) {
                    br { +"Facebook" }
                } else if (participante.onde_conheceu == 3) {
                    br { +"WhatsApp" }
                } else if (participante.onde_conheceu == 4) {
                    br { +"amigos/familiares" }
                } else if (participante.onde_conheceu == 5) {
                    br { +"influencer: ${participante.influencer}" }
                }
            }
        }
        h4 { +"Cursos escolhidos" }
        article {
            if (participante.curso1_id == curso1?.id && participante.curso1_id != null) {
                p {
                    a("/inscricoes/adm/candidato?id=${participante.id}&&idC=${curso1?.id}", classes = "secondary-content") {
                        i("material-icons") {
                            +"edit"
                        }
                    }
                    b {
                        if (curso1 != null) {
                            +curso1.nome
                        }
                    }

                    br

                    when (participante.resultado_c1) {
                        -1 -> b("grey-text text-darken-2") {
                            +"Não avaliado"
                        }

                        1 -> b("light-green-text text-accent-3") {
                            +"Aprovado"
                        }
                        2 -> b("yellow-text text-accent-3") {
                            +"Lista de Espera"
                        }

                        3 -> b("blue-text text-accent-3") {
                            +"Desistência"
                        }

                        4 -> b("red-text text-accent-4") {
                            +"Reprovado"
                        }
                    }
                }
                p {
                    b { +"redação: ${curso1?.nome}" }
                    br { +participante.redacao1 }
                }
            } else {
                p {
                    b { +"1ºopção não escolhida" }
                }
            }

            if (participante.curso2_id == curso2?.id && participante.curso2_id != null) {
                p {
                    a("/inscricoes/adm/candidato?id=${participante.id}&&idC=${curso2?.id}", classes = "secondary-content") {
                        i("material-icons") {
                            +"edit"
                        }
                    }
                    b {
                        if (curso2 != null) {
                            +"${curso2.nome}"
                        }
                    }

                    br

                    when (participante.resultado_c2) {
                        -1 -> b("grey-text text-darken-2") {
                            +"Não avaliado"
                        }

                        1 -> b("light-green-text text-accent-3") {
                            +"Aprovado"
                        }
                        2 -> b("yellow-text text-accent-3") {
                            +"Lista de Espera"
                        }

                        3 -> b("blue-text text-accent-3") {
                            +"Desistência"
                        }

                        4 -> b("red-text text-accent-4") {
                            +"Reprovado"
                        }
                    }
                }
                p {
                    b { +"redação: ${curso2?.nome}" }
                    br { +participante.redacao2 }
                }
            } else {
                p {
                    b { +"2ºopção não escolhida" }
                }
            }
        }

        link(type = "text/css", rel = "stylesheet", href = "/css/sisins_edicao.css")
        div("row") {
            form("EdicaoSenha", classes = "col s12", method = FormMethod.post) {
                div("input-field col s12 mb-0") {
                    p { +"Atenção! Só altere a senha com a autorização do(a) ${participante.nome}." }

                    div("input-field col s12 mb-0 invisible") {
                        label("perguntas") {
                            htmlFor = "inputId"
                            +"ID"
                        }
                        input(InputType.text, classes = "validate") {
                            id = "inputId"
                            name = "id"

                            value = participante.id.toString()
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
                        }
                    }
                }
                button(type = ButtonType.submit, classes = "entrar waves-effect waves-light btn") {
                    id = "btnParte4Cadastrar"
                    +"Alterar senha"
                }
            }
        }
    }
}