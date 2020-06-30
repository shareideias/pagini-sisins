package br.com.associacaoshare.view.base

import io.javalin.http.Context
import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML

abstract class HtmlBuilderView : View {
    abstract fun HTML.render(ctx: Context)

    override fun render(ctx: Context) {
        ctx.contentType("text/html")
        ctx.res.writer.appendHTML().html { render(ctx) }.close()
    }
}