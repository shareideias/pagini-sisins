package br.com.associacaoshare.view

import br.com.associacaoshare.model.page.EditarNoticiaViewModel
import br.com.associacaoshare.view.base.PagIniAdminView
import io.javalin.http.Context
import kotlinx.html.*
import kotlinx.html.ButtonType.submit
import kotlinx.html.FormMethod.post
import kotlinx.html.InputType.text

class EditarNoticiaView(override val model: EditarNoticiaViewModel) : PagIniAdminView() {
    override val pageTitle = if (model.editing) "Edição de Notícia" else "Nova Notícia"

    override fun MAIN.renderMain(ctx: Context) {
        div("container") {
            h5("underlined") { +pageTitle }
            form(method = post) {
                div("row") {
                    div("input-field col s12") {
                        label {
                            htmlFor = "inputTitle"
                            +"Título da notícia"
                        }
                        input(text, classes = "validate", name = "title") {
                            id = "inputTitle"
                            placeholder = "Título"
                            model.noticia?.let { value = it.titulo }
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
                            +if (model.noticia == null) "Criar Notícia" else "Editar Notícia"
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

                model.noticia?.takeIf { it.html.isNotEmpty() }?.let {
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
//