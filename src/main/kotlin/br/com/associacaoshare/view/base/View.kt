package br.com.associacaoshare.view.base

import io.javalin.http.Context

interface View {
    fun render(ctx: Context)
}