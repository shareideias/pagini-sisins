package br.com.associacaoshare.view.adm

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.Influencer
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.view.base.SisInsAdmView
import io.javalin.http.Context
import kotlinx.html.*

class InfluencerView(private val errormsg: String?, private val inscritos: List<Influencer>?, private var qtdParticipantes: Int) : SisInsAdmView() {
    override val pageTitle: String = "Cursos"

    override fun MAIN.renderMain(ctx: Context) {
        link(type = "text/css", rel = "stylesheet", href = "/css/alerts.css")
        if (!errormsg.isNullOrEmpty()) {
            div("materialert error") {
                div("material-icons") { +"error_outline" }
                +"$errormsg"
            }
        }

        h3{+"Influencers"}

        div("row") {
            div("col 14 m3 s12")
            div("col 14 m6 s12") {
                inscritos?.forEach {
                    if(it.influencer != "nenhum")
                    ul("collection") {
                        li("collection-item") {
                            span("title") {
                                +it.influencer
                            }
                            br{}
                            span("title") {
                                +"Quantidade: ${it.count}"
                            }
                        }
                    }

                }
            }
        }
    }
}
