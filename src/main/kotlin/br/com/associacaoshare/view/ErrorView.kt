package br.com.associacaoshare.view

import br.com.associacaoshare.model.page.ErrorViewModel
import br.com.associacaoshare.view.base.PagIniView
import br.com.associacaoshare.view.base.PagIniView.Type.ERROR_PAGE
import io.javalin.http.Context
import kotlinx.html.*

class ErrorView(override val model: ErrorViewModel) : PagIniView(ERROR_PAGE) {
    override val mainPage = model.navLinks.first().href
    override val pageTitle = "${model.errorCode} - ${model.errorTitle}"

    override fun MAIN.renderMain(ctx: Context) {
        div("container center-align") {
            h1 { +model.errorCode.toString() }
            h2 { +model.errorTitle }
            p("flow-text center-align") { +model.errorDescription }
        }
    }
}