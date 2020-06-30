package br.com.associacaoshare.view

import br.com.associacaoshare.model.Noticia
import br.com.associacaoshare.model.page.IndexViewModel
import br.com.associacaoshare.utils.compressSpaces
import br.com.associacaoshare.utils.limit
import br.com.associacaoshare.view.base.PagIniView
import br.com.associacaoshare.view.base.PagIniView.Type.INDEX
import io.javalin.http.Context
import kotlinx.html.*
import org.jsoup.Jsoup
import java.time.format.DateTimeFormatter

class BlogView(override val model: IndexViewModel) : PagIniView(INDEX) {
    override val pageTitle = "Blog"

    override fun MAIN.renderMain(ctx: Context) {
        link("/css/pagini_blog.css", "stylesheet")

        h1("section-title") { +"""Blog""" }

        div("container") {
            div("row") {
                div("col s12 xl8") {
                    h5("underlined") { +"Publicações:" }

                    if (model.noticias.isNotEmpty()) {
                        renderNoticias(model.noticias)
                    } else {
                        p("blue-grey-text lighten-3 center-align") {
                            i {
                                if (model.pageNumber == 0) {
                                    +"O site não tem nenhuma notícia. Elas serão mostradas aqui."
                                } else {
                                    +"Essa página não contém notícias. Por favor, "
                                    a("/") { +"volte a página inicial" }
                                    +"."
                                }
                            }
                        }
                    }

                    a {
                        href = "posts"
                        button(classes = "col s12 btn btn-large waves-effect") { +"""Ver todas as publicações""" }
                    }

                }
                div("col s12 xl4") {
                    h5("underlined") { +"Sobre nós:" }
                    p("par_news") {
                        +"A Share é uma Entidade Estudantil fundada em 2016 por alunos de Ciências Econômicas na"
                        +" UFSCar - Campus Sorocaba, com o intuito de conectar o desejo de ensinar com a vontade de aprender."
                        +" Para isso oferecemos semestralmente cursos de idioma, culturais e administrativos, além de eventos,"
                        +" tudo isso de forma acessível e com certificado."
                        +" Contamos com professores voluntários e 5 áreas administrativas voluntárias"
                        +" dos quais ajudam a fazer o projeto acontecer e crescer."
                    }
                }
            }
        }
    }




    private fun DIV.renderNoticias(noticias: List<Noticia>) {
        noticias.forEach {
            div("card") {
                div("card-content") {
                    h3 ("post-title"){ +it.titulo }
                    p("article-info") {
                        +DateTimeFormatter.RFC_1123_DATE_TIME.format(it.dataCriacao)
                        +" por "
                        +(model.pessoas[it.criadoPorPessoa]?.nome ?: "Usuário removido")
                    }
                    p("justify") {
                        +Jsoup.parseBodyFragment(it.html, "/").text().compressSpaces().limit(140)
                    }
                    p("compartilhamento-titulo") { +"Compartilhe:" }
                    ul("social-sharing") {
                        li { a("https://www.facebook.com/sharer/sharer.php?u=https://associacaoshare.com.br/n/${it.id}", classes = "img-fb") { +"Facebook" } }
                        li { a("https://api.whatsapp.com/send?text=https://associacaoshare.com.br/n/${it.id}", classes = "img-wpp") { +"Whatsapp" } }
                        li { a("https://www.linkedin.com/shareArticle?mini=true&url=https://associacaoshare.com.br/n/${it.id}", classes = "img-ln") { +"LinkedIn" } }
                    }
                }
                div("card-action") { a("/n/${it.id}") { +"Leia mais" } }
            }
        }
    }
}