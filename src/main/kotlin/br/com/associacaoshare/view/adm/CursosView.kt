package br.com.associacaoshare.view.adm

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.view.base.SisInsAdmView
import io.javalin.http.Context
import kotlinx.html.*

class CursosView(private val errormsg: String?, private val cursos: List<Curso>, private var interruptor: Int) : SisInsAdmView() {
    override val pageTitle: String = "Cursos"

    override fun MAIN.renderMain(ctx: Context) {
        link(type = "text/css", rel = "stylesheet", href = "/css/alerts.css")
        if (!errormsg.isNullOrEmpty()) {
            div("materialert error") {
                div("material-icons") { +"error_outline" }
                +"$errormsg"
            }
        }

        h3{+"Cursos"}

        if(interruptor == 0) {
            div("botaoabrir") {
                a("/inscricoes/adm/abreinscricoes", classes = "entrar waves-effect waves-light btn") {
                    +"Abrir inscrições"
                }
            }
        }
        else if(interruptor == 1) {
            div("botaofechar") {
                a("/inscricoes/adm/fechainscricoes", classes = "entrar waves-effect waves-light btn") {
                    +"Fechar inscrições"
                }
            }
        }

        div("container cards_recente") {
            div("row") {
                cursos.forEach {
                    article("borda-curso") {
                        div("col m4 s12 coursecard") {
                            div("card") {
                                div("card-content") {
                                    span("card-title") {
                                        +it.nome
                                        p("par_news") {
                                            +"${it.horario}"
                                        }
                                    }
                                }
                                div("card-action") {
                                    a("/inscricoes/adm/inscricoes?id=${it.id}") {
                                        +"Inscrições"
                                    }
                                    br{}
                                    a("/inscricoes/adm/editarcurso?id=${it.id}") {
                                        +"Editar curso"
                                    }
                                    br{}
                                    a("/inscricoes/adm/excluircurso?id=${it.id}") {
                                        +"Excluir curso"
                                    }
                                    br{}
                                    a("#?id=${it.id}") {
                                        +"Gerar certificados"
                                    }
                                }

                            }
                        }
                    }
                }
                //adicionar curso
                article("borda-curso") {
                    div("col m4 s12") {
                        div("card") {
                            div("card-action mais") {
                                a("/inscricoes/adm/addCurso") {
                                    +"+"
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}