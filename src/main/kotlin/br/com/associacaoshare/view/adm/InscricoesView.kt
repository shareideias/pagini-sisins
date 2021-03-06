package br.com.associacaoshare.view.adm

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAdmView
import io.javalin.http.Context
import kotlinx.html.*

class InscricoesView(private val errormsg: String?, private val curso: Curso, private val inscritos: List<Participante>?, private var qtdParticipantes: Int) : SisInsAdmView() {
    override val pageTitle: String = "Cursos"

    override fun MAIN.renderMain(ctx: Context) {
        link(type = "text/css", rel = "stylesheet", href = "/css/alerts.css")
        if (!errormsg.isNullOrEmpty()) {
            div("materialert error") {
                div("material-icons") { +"error_outline" }
                +"$errormsg"
            }
        }

        h3 { +"Inscrições (${qtdParticipantes})" }
        h4 { +curso.nome }
        h5 { +"${curso.horario}" }

        nav("abas-container") {
            div("abas-content") {
                div("col s12") {
                    a("/inscricoes/adm/inscricoes?id=${curso.id}", classes = "breadcrumb") { span("orange btn-small") { +"Todos" } }
                    a("/inscricoes/adm/naoAvaliados?id=${curso.id}", classes = "breadcrumb") { span("gray btn-small") { +"Não avaliados" } }
                    a("/inscricoes/adm/aprovados?id=${curso.id}", classes = "breadcrumb") { span("green btn-small") { +"1 chamada" } }
                    a("/inscricoes/adm/aprovados2?id=${curso.id}", classes = "breadcrumb") { span("light-green btn-small") { +"2 chamada" } }
                    a("/inscricoes/adm/espera?id=${curso.id}", classes = "breadcrumb") { span("yellow darken-2 btn-small") { +"Lista de Espera" } }
                    a("/inscricoes/adm/desistencias?id=${curso.id}", classes = "breadcrumb") { span("blue btn-small") { +"Desistências" } }
                    a("/inscricoes/adm/reprovados?id=${curso.id}", classes = "breadcrumb") { span("red btn-small") { +"Reprovados" } }
                }
            }
        }

        div("row") {
            div("col 14 m3 s12")
            div("col 14 m6 s12") {
                inscritos?.forEach {
                    when (if (it.curso1_id == curso.id) it.resultado_c1 else it.resultado_c2) {
                        -1 -> {
                            ul("collection") {
                                li("collection-item avatar") {
                                    i("material-icons circle gray") {
                                        +"account_circle"
                                    }
                                    span("title") {
                                        +it.nome
                                    }
                                    p("statusavaliacao") {
                                        +"Não avaliado"
                                    }
                                    a("/inscricoes/adm/candidato?id=${it.id}&&idC=${curso.id}", classes = "secondary-content") {
                                        i("material-icons") {
                                            +"edit"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                inscritos?.forEach {
                    when (if (it.curso1_id == curso.id) it.resultado_c1 else it.resultado_c2) {
                        1 -> {
                            ul("collection") {
                                li("collection-item avatar") {
                                    i("material-icons circle green") {
                                        +"account_circle"
                                    }
                                    span("title") {
                                        +it.nome
                                    }
                                    p("statusavaliacao") {
                                        +"1 chamada"
                                    }
                                    a("/inscricoes/adm/candidato?id=${it.id}&&idC=${curso.id}", classes = "secondary-content") {
                                        i("material-icons") {
                                            +"edit"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                inscritos?.forEach {
                    when (if (it.curso1_id == curso.id) it.resultado_c1 else it.resultado_c2) {
                        5 -> {
                            ul("collection") {
                                li("collection-item avatar") {
                                    i("material-icons circle light-green") {
                                        +"account_circle"
                                    }
                                    span("title") {
                                        +it.nome
                                    }
                                    p("statusavaliacao") {
                                        +"2 chamada"
                                    }
                                    a("/inscricoes/adm/candidato?id=${it.id}&&idC=${curso.id}", classes = "secondary-content") {
                                        i("material-icons") {
                                            +"edit"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                inscritos?.forEach {
                    when (if (it.curso1_id == curso.id) it.resultado_c1 else it.resultado_c2) {
                        2 -> {
                            ul("collection") {
                                li("collection-item avatar") {
                                    i("material-icons circle yellow") {
                                        +"account_circle"
                                    }
                                    span("title") {
                                        +it.nome
                                    }
                                    p("statusavaliacao") {
                                        +"Lista de espera"
                                    }
                                    a("/inscricoes/adm/candidato?id=${it.id}&&idC=${curso.id}", classes = "secondary-content") {
                                        i("material-icons") {
                                            +"edit"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                inscritos?.forEach {
                    when (if (it.curso1_id == curso.id) it.resultado_c1 else it.resultado_c2) {
                        3 -> {
                            ul("collection") {
                                li("collection-item avatar") {
                                    i("material-icons circle blue") {
                                        +"account_circle"
                                    }
                                    span("title") {
                                        +it.nome
                                    }
                                    p("statusavaliacao") {
                                        +"Desistiu"
                                    }
                                    a("/inscricoes/adm/candidato?id=${it.id}&&idC=${curso.id}", classes = "secondary-content") {
                                        i("material-icons") {
                                            +"edit"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                inscritos?.forEach {
                    when (if (it.curso1_id == curso.id) it.resultado_c1 else it.resultado_c2) {

                        4 -> {
                            ul("collection") {
                                li("collection-item avatar") {
                                    i("material-icons circle red") {
                                        +"account_circle"
                                    }
                                    span("title") {
                                        +it.nome
                                    }
                                    p("statusavaliacao") {
                                        +"Reprovado"
                                    }
                                    a("/inscricoes/adm/candidato?id=${it.id}&&idC=${curso.id}", classes = "secondary-content") {
                                        i("material-icons") {
                                            +"edit"
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
}
