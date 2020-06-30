package br.com.associacaoshare.view

import br.com.associacaoshare.model.page.EditarPaginaViewModel
import br.com.associacaoshare.view.base.PagIniAdminView
import io.javalin.http.Context
import kotlinx.html.*
import kotlinx.html.ButtonType.submit
import kotlinx.html.FormMethod.post
import kotlinx.html.InputType.text

class EditarPaginaView(override val model: EditarPaginaViewModel) : PagIniAdminView() {
    override val pageTitle = if (model.editing) "Edição de Página" else "Nova Página"

    override fun MAIN.renderMain(ctx: Context) {
        div("container") {
            h5("underlined") { +pageTitle }
            form(method = post) {
                div("row") {
                    div("input-field col s8") {
                        label {
                            htmlFor = "inputTitle"
                            +"Título da página"
                        }
                        input(text, classes = "validate", name = "title") {
                            id = "inputTitle"
                            placeholder = "Título"
                            model.pagina?.let { value = it.titulo }
                        }
                    }

                    div("input-field col s4") {
                        label {
                            htmlFor = "inputLink"
                            +"Link da página"
                        }
                        input(text, classes = "validate", name = "linkPagina") {
                            id = "inputLink"
                            placeholder = "link_da_pagina"
                            model.pagina?.let { value = it.linkPagina }
                        }
                    }

                    div("col s12") {
                        label { +"Corpo da página" }
                        textArea(classes = "materialize-textarea") {
                            id = "summernote"
                            name = "html"
                        }
                    }

                    div("col s12 input-field") {
                        button(type = submit, classes = "btn waves-effect light-blue lighten-2") {
                            +if (model.pagina == null) "Criar Página" else "Editar Página"
                            i("material-icons right") { +"send" }
                        }
                    }
                }
            }
        }
    }

    override fun HEAD.afterLinks(ctx: Context) {
        link("https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css", "stylesheet")
    }

    override fun BODY.beforeScripts(ctx: Context) {
        script(src = "http://code.jquery.com/jquery-3.4.1.min.js") {}
    }

    override fun BODY.afterScripts(ctx: Context) {
        script(src = "https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js") {}
        script(src = "https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/lang/summernote-pt-BR.min.js") {}
        script {
            unsafe {
                +"""
                $(document).ready(function() {
                    $('#summernote').summernote({
                        height: 400, lang: 'pt-BR',
                        toolbar: [
                            ['style', ['style']],
                            ['font', ['fontsize', 'bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'clear']],
                            ['color', ['forecolor', 'backcolor']],
                            ['para', ['ul', 'ol', 'paragraph', 'height']],
                            ['insert', ['table', 'picture', 'video', 'link', 'hr']],
                            ['view', ['fullscreen', 'codeview']]
                        ]
                     });
                """.trimIndent()

                model.pagina?.takeIf { it.html.isNotEmpty() }?.let {
                    +"""
                    $('#summernote').summernote('code', ${model.mapper.writeValueAsString(it.html)});
                    """.trimIndent()
                }
                +"""
                });
                """.trimIndent()
            }
        }
    }
}