package br.com.associacaoshare.view.adm.inscricoes

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAdmView
import io.javalin.http.Context
import kotlinx.html.*

class EsperaView(private val errormsg: String?, private val curso: Curso, private val inscritos: List<Participante>?, private var qtdParticipantes: Int) : SisInsAdmView() {
    override val pageTitle: String = "Cursos"

    override fun MAIN.renderMain(ctx: Context) {
        link(type = "text/css", rel = "stylesheet", href = "/css/alerts.css")
        if (!errormsg.isNullOrEmpty()) {
            div("materialert error") {
                div("material-icons") { +"error_outline" }
                +"$errormsg"
            }
        }
        
        var qtdEspera = 0
        inscritos?.forEach {
            when (if (it.curso1_id == curso.id) it.resultado_c1 else it.resultado_c2) {
                2 -> {qtdEspera++}
            }
        }

        h3 { +"Lista de Espera (${qtdEspera})" }
        h4 { +curso.nome }
        h5 { +"${curso.horario}" }

        nav {
            div("pageList") {
                div {
                    a("/inscricoes/adm/inscricoes?id=${curso.id}", classes = "breadcrumb") { span("orange btn-small") { +"Todos" } }
                    a("/inscricoes/adm/naoAvaliados?id=${curso.id}", classes = "breadcrumb") { span("gray btn-small") { +"Não avaliados" } }
                    a("/inscricoes/adm/aprovados?id=${curso.id}", classes = "breadcrumb") { span("green btn-small") { +"Aprovados" } }
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
            }
        }
    }
}
