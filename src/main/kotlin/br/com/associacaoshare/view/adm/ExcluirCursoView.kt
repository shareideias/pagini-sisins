package br.com.associacaoshare.view.adm

import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.view.base.SisInsAdmView
import io.javalin.http.Context
import kotlinx.html.*

class ExcluirCursoView(private val errormsg: String?, private val curso: Curso) : SisInsAdmView() {
    override val pageTitle: String = "Cursos"

    override fun MAIN.renderMain(ctx: Context) {
        link(type = "text/css", rel = "stylesheet", href = "/css/alerts.css")
        if (!errormsg.isNullOrEmpty()) {
            div("materialert error") {
                div("material-icons") { +"error_outline" }
                +"$errormsg"
            }
        }

        h5{+"Você realmente deseja excluir o curso de ${curso.nome}?"}

        form(classes="formexclusao") {
            div("botoesexclusao"){
                a("/inscricoes/adm", classes = "botao waves-effect waves-light btn-large") {
                    +"Não"
                }
                a("/inscricoes/adm/cursoexcluido?id=${curso.id}", classes = "botao waves-effect waves-light red btn-large") {
                    +"Sim"
                }
            }
        }

    }
}