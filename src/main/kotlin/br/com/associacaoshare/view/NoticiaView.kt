package br.com.associacaoshare.view

import br.com.associacaoshare.model.page.NoticiaViewModel
import br.com.associacaoshare.view.base.PagIniView
import br.com.associacaoshare.view.base.PagIniView.Type.PUBLIC_PAGE
import io.javalin.http.Context
import kotlinx.html.*
import java.time.format.DateTimeFormatter

class NoticiaView(override val model: NoticiaViewModel) : PagIniView(PUBLIC_PAGE) {
    override val pageTitle = model.noticia.titulo

    override fun MAIN.renderMain(ctx: Context) {
        div("container") {
            div("row") {
                div("col s12 xl8") {
                    article {
                        h3 { +model.noticia.titulo }
                        p("article-info") {
                            +DateTimeFormatter.RFC_1123_DATE_TIME.format(model.noticia.dataCriacao)
                            +" por "
                            +(model.criadoPorPessoa?.nome ?: "Usuário removido")
                        }
                        div("par_news") {
                            unsafe { +model.noticia.html }
                        }
                    }
                    p("compartilhamento-titulo") { +"Compartilhe:" }
                    ul("social-sharing") {
                        li { a("https://www.facebook.com/sharer/sharer.php?u=https://associacaoshare.com.br/n/${model.noticia.id}", classes = "img-fb") { +"Facebook" } }
                        li { a("https://api.whatsapp.com/send?text=https://associacaoshare.com.br/n/${model.noticia.id}", classes = "img-wpp") { +"Whatsapp" } }
                        li { a("https://www.linkedin.com/shareArticle?mini=true&url=https://associacaoshare.com.br/n/${model.noticia.id}", classes = "img-ln") { +"LinkedIn" } }
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
}