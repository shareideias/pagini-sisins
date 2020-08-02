package br.com.associacaoshare.view.adm.inscricoes

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAdmView
import io.javalin.http.Context
import kotlinx.html.*

class ReprovadosView(private val errormsg: String?, private val curso: Curso, private val inscritos: List<Participante>?, private var qtdParticipantes: Int) : SisInsAdmView() {
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

        nav("links") {
            div("nav-wrapper") {
                div("col s12") {
                    a("/inscricoes/adm/inscricoes?id=${curso.id}", "breadcrumb") { +"Todos" }
                    a("/inscricoes/adm/naoAvaliados?id=${curso.id}", "breadcrumb") { +"Não avaliados" }
                    a("/inscricoes/adm/aprovados?id=${curso.id}", "breadcrumb") { +"Aprovados" }
                    a("/inscricoes/adm/espera?id=${curso.id}", "breadcrumb") { +"Lista de Espera" }
                    a("/inscricoes/adm/reprovados?id=${curso.id}", "breadcrumb") { +"Reprovados" }
                    a("/inscricoes/adm/desistencias?id=${curso.id}", "breadcrumb") { +"Desistências" }
                }
            }
        }

        div("row") {
            div("col 14 m3 s12")
            div("col 14 m6 s12") {
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

