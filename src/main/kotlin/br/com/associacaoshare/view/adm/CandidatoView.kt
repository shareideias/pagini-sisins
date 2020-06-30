package br.com.associacaoshare.view.adm

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAdmView
import io.javalin.http.Context
import kotlinx.html.*

class CandidatoView(private val errormsg: String?, private val participante: Participante?, private val curso: Curso?) : SisInsAdmView() {
    override val pageTitle: String = "Candidato"

    override fun MAIN.renderMain(ctx: Context) {
        h4 {
            +participante!!.nome
        }
        article {
            p {
                b { +"Categoria:" }
                if (participante!!.categoria == 1) {
                    br { +"Pessoas com vínculo com a UFSCar." }
                } else if (participante!!.categoria == 2) {
                    br { +"Alunos do terceiro ano do ensino médio (tendo no mínimo 16 anos)." }
                } else if (participante!!.categoria == 3) {
                    br { +"Pessoas que não possuem vínculo com a UFSCar." }
                }
            }
            p {
                b { +"Vínculo:" }
                if (participante!!.vinculo_ufscar == 1) {
                    br { +"Professor" }
                } else if (participante!!.vinculo_ufscar == 2) {
                    br { +"Técnico-Administrativo (TAs)" }
                } else if (participante!!.vinculo_ufscar == 3) {
                    br { +"Aluno da Graduação" }
                } else if (participante!!.vinculo_ufscar == 4) {
                    br { +"Aluno da Pós Graduação" }
                } else if (participante!!.vinculo_ufscar == 5) {
                    br { +"Outro" }
                } else if (participante!!.vinculo_ufscar == 6) {
                    br { +"Nenhum" }
                }
            }
            p {
                b { +"Estudante e/ou trabalha:" }
                if (participante!!.tipo_sem_vinculo == 1) {
                    br { +"Trabalho e não estudo" }
                } else if (participante!!.tipo_sem_vinculo == 2) {
                    br { +"Não trabalho e estudo" }
                } else if (participante!!.tipo_sem_vinculo == 3) {
                    br { +"Não estudo e não trabalho" }
                } else if (participante!!.tipo_sem_vinculo == 4) {
                    br { +"Estudo e trabalho" }
                }
            }
            p {
                b { +"Escola a qual estuda se for do ensino médio:" }
                br { +participante!!.escola }
            }
            p {
                b { +"Data de nascimento:" }

                br { +"${participante!!.data_nascimento.dayOfMonth}/${participante!!.data_nascimento.monthValue}/${participante!!.data_nascimento.year}" }
            }
            p {
                b { +"Telefone:" }
                br { +participante!!.telefone }
            }
            p {
                b { +"Email:" }
                br { +participante!!.email }
            }
        }

        h4 { +"Inscrição" }
        article {
            p {
                b { +"Esteve antes na UFSCar:" }
                if (participante!!.esteve_ufscar == 1) {
                    br { +"Sim" }
                } else if (participante!!.esteve_ufscar == 2) {
                    br { +"Não" }
                }
            }
            p {
                b { +"Ciente de que as aulas acontecem na UFSCar Sorocaba:" }
                if (participante!!.local_aulas == 1) {
                    br { +"Sim" }
                } else if (participante!!.local_aulas == 2) {
                    br { +"Não" }
                }
            }
            p {
                b { +"Disponibilidade de tempo:" }
                br { +participante!!.disponibilidade }
            }
            p {
                b { +"Objetivo com o curso:" }
                if (participante!!.objetivo == 1) {
                    br { +"Aprender algo novo" }
                } else if (participante!!.objetivo == 2) {
                    br { +"Ter um hobby/me distrair" }
                } else if (participante!!.objetivo == 3) {
                    br { +"Me atualizar para o mercado de trabalho" }
                } else if (participante!!.objetivo == 4) {
                    br { +"Conhecer pessoas novas" }
                } else if (participante!!.objetivo == 5) {
                    br { +"Conhecer mais sobre a área/ sanar minha curiosidade" }
                } else if (participante!!.objetivo == 6) {
                    br { +"Me preparar para provas (vestibulares,  provas da faculdade)" }
                } else if (participante!!.objetivo == 7) {
                    br { +"Me preparar para um intercâmbio" }
                } else if (participante!!.objetivo == 8) {
                    br { +"Outro" }
                }
            }
            p {
                b { +"Inscrição em outros cursos da Share:" }
                if (participante!!.cursou_share == 1) {
                    br { +"Sim, e fui chamado" }
                } else if (participante!!.cursou_share == 2) {
                    br { +"Não" }
                } else if (participante!!.cursou_share == 3) {
                    br { +"Uma vez, e não fui chamado" }
                } else if (participante!!.cursou_share == 4) {
                    br { +"Mais de uma vez, e nunca fui chamado." }
                }
            }
            p {
                b { +"Ciente das consequências da desistências do curso:" }
                if (participante!!.desistencia == 1) {
                    br { +"Sim" }
                } else if (participante!!.desistencia == 2) {
                    br { +"Não" }
                }
            }
            p {
                b { +"Texto de por quê gostaria de fazer um curso na Share:" }
                br { +participante!!.redacao_entrada }
            }
            p {
                b { +"Leitura do edital:" }
                if (participante!!.esteve_ufscar == 1) {
                    br { +"Sim" }
                } else if (participante!!.esteve_ufscar == 2) {
                    br { +"Não" }
                }
            }
            p {
                b { +"Por onde conheceu a Share:" }
                if (participante!!.onde_conheceu == 1) {
                    br { +"Mídias Sociais (Facebook, Whatsapp, etc)" }
                } else if (participante!!.onde_conheceu == 2) {
                    br { +"Recomendação" }
                } else if (participante!!.onde_conheceu == 3) {
                    br { +"Outras Mídias (Jornais, Revistas, Televisão)" }
                } else if (participante!!.onde_conheceu == 4) {
                    br { +"Outro" }
                }
            }
        }
        if (curso!!.categoria == "1") {
            h4 {
                +"Inscrito em "
                +curso!!.nome
            }
            article {
                p {
                    b { +curso!!.pergunta1!! }
                    when(if(participante!!.curso1_id == curso.id) participante!!.resposta1_c1 else participante!!.resposta1_c1){
                        1 -> {
                            br { +curso!!.alternativa11!! }
                        }
                        2 -> {
                            br { +curso!!.alternativa12!! }
                        }
                        3 -> {
                            br { +curso!!.alternativa13!! }
                        }
                        4 -> {
                            br { +curso!!.alternativa14!! }
                        }
                        5 -> {
                            br { +curso!!.alternativa15!! }
                        }

                    }
                }
                p {
                    b { +curso!!.pergunta2!! }
                    when(if(participante!!.curso1_id == curso.id) participante!!.resposta2_c1 else participante!!.resposta2_c2){
                        1 -> {
                            br { +curso!!.alternativa21!! }
                        }
                        2 -> {
                            br { +curso!!.alternativa22!! }
                        }
                        3 -> {
                            br { +curso!!.alternativa23!! }
                        }
                        4 -> {
                            br { +curso!!.alternativa24!! }
                        }
                        5 -> {
                            br { +curso!!.alternativa25!! }
                        }

                    }
                }
                p {
                    b { +curso!!.pergunta3!! }
                    when(if(participante!!.curso1_id == curso.id) participante!!.resposta3_c1 else participante!!.resposta3_c2){
                        1 -> {
                            br { +curso!!.alternativa31!! }
                        }
                        2 -> {
                            br { +curso!!.alternativa32!! }
                        }
                        3 -> {
                            br { +curso!!.alternativa33!! }
                        }
                        4 -> {
                            br { +curso!!.alternativa34!! }
                        }
                        5 -> {
                            br { +curso!!.alternativa35!! }
                        }
                    }
                }
                p {
                    b { +curso!!.pergunta4!! }
                    when(if(participante!!.curso1_id == curso.id) participante!!.resposta4_c1 else participante!!.resposta4_c2){
                        1 -> {
                            br { +curso!!.alternativa41!! }
                        }
                        2 -> {
                            br { +curso!!.alternativa42!! }
                        }
                        3 -> {
                            br { +curso!!.alternativa43!! }
                        }
                        4 -> {
                            br { +curso!!.alternativa44!! }
                        }
                        5 -> {
                            br { +curso!!.alternativa45!! }
                        }
                    }
                }
                p {
                    b { +curso!!.pergunta5!! }
                    when(if(participante!!.curso1_id == curso.id) participante!!.resposta5_c1 else participante!!.resposta5_c2){
                        1 -> {
                            br { +curso!!.alternativa51!! }
                        }
                        2 -> {
                            br { +curso!!.alternativa52!! }
                        }
                        3 -> {
                            br { +curso!!.alternativa53!! }
                        }
                        4 -> {
                            br { +curso!!.alternativa54!! }
                        }
                        5 -> {
                            br { +curso!!.alternativa55!! }
                        }
                    }
                }
                p {
                    b { +curso!!.pergunta6!! }
                    when(if(participante!!.curso1_id == curso.id) participante!!.resposta6_c1 else participante!!.resposta6_c2){
                        1 -> {
                            br { +curso!!.alternativa61!! }
                        }
                        2 -> {
                            br { +curso!!.alternativa62!! }
                        }
                        3 -> {
                            br { +curso!!.alternativa63!! }
                        }
                        4 -> {
                            br { +curso!!.alternativa64!! }
                        }
                        5 -> {
                            br { +curso!!.alternativa65!! }
                        }
                    }
                }
            }
        }

        a("/inscricoes/adm/aprova?id=${participante?.id}&&idC=${curso.id}", classes = "botao waves-effect waves-light green btn-large") {
            +"Aprovar"
        }
        a("/inscricoes/adm/listadeespera?id=${participante?.id}&&idC=${curso.id}", classes = "botao waves-effect waves-light yellow btn-large") {
            +"Lista de espera"
        }
        a("/inscricoes/adm/desistencia?id=${participante?.id}&&idC=${curso.id}", classes = "botao waves-effect waves-light blue btn-large") {
            +"Desistência"
        }
        a("/inscricoes/adm/reprova?id=${participante?.id}&&idC=${curso.id}", classes = "botao waves-effect waves-light red btn-large") {
            +"Reprovar"
        }
    }
}
