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

class IndexView(override val model: IndexViewModel) : PagIniView(INDEX) {
    override val pageTitle = "Página Inicial"

    override fun MAIN.renderMain(ctx: Context) {
        /*Parte do código temporária para a divulgação dos processos seletivos*/
        /*Só deverá ser ativada ao começo de cada semestre*/
        /*div(classes = "janela") {
            id = "home"
            div(classes = "conteudo-janela") {
                a(classes = "fechar") {
                    href = "#home"
                    +"""X"""
                }
                h2(classes = "titulo-janela") { +"""Processo seletivo""" }
                p { +"""Está interessado em fazer parte de nossa entidade?""" }
                p { +"""Se inscreva em um ou mais de nossos processos:""" }

                p(classes = "datas-janela") { +"""de 28/02 até 14/03:""" }
                div(classes = "botaoadm") {
                    a {
                        href = "https://bit.ly/edital-aluno"
                        button(classes = "col s12 btn btn-large waves-effect") { +"""Administrativo""" }
                    }
                    br {
                    }
                }

                p(classes = "datas-janela") { +"""de 28/02 até 14/03:""" }
                div(classes = "botaoprof") {
                    a {
                        href = "http://bit.ly/InscriçõesProf"
                        button(classes = "col s12 btn btn-large waves-effect") { +"""Professor""" }
                    }
                    br {
                    }
                }

                p(classes = "datas-janela") { +"""de 25/04 até 28/04:""" }
                div(classes = "botaoaluno") {
                    a {
                        href = "https://forms.gle/pK4RXSeFdfpzqcty8"
                        button(classes = "col s12 btn btn-large waves-effect") { +"""Aluno""" }
                    }
                    br {
                    }
                }
            }
        }*/
        /*fim da parte temporária*/

        /*header com foto*/
        div(classes = "parallax-container") {
            h1(classes = "textoheadertitulo") { +"""Share""" }
            h2(classes = "textoheader") {
                +"""Compartilhando"""
                br {
                }
                +"""ideias"""
            }
            div(classes = "parallax") {
                img {
                    src = "/img/alunoheader.png"
                }
            }
        }
        /*fim header*/

        /*Blog*/
        div(classes = "slider") {
            model.cards.takeIf { it.isNotEmpty() }?.let { renderCards(it) }
        }
        /*fim Blog*/

        /*redes sociais*/
        div(classes = "redes") {
            ul(classes = "redes_sociais") {
                li {
                    a(classes = "img-fb") {
                        href = "https://www.facebook.com/shareideias/"
                        +"""Facebook"""
                    }
                }
                li { a("https://www.instagram.com/shareideias/", classes = "img-ig") { +"Instagram" } }
                li {
                    a(classes = "img-ln") {
                        href = "https://www.linkedin.com/in/shareideias/"
                        +"""LinkedIn"""
                    }
                }
            }
        }
        /*fim redes sociais*/

        /*MISSAO*/
        div(classes = "row rowzeras1") {
            h2(classes = "wow animate__animated animate__fadeIn") {
                id = "instit1"
                +"""Nossa Missão"""
            }

            div(classes = "col s12 m10 offset-m1 l10 offset-l1") {
                div(classes = "card cardzeras1 wow animate__animated animate__fadeIn") {
                    attributes["data-wow-delay"] = "0.5s"
                    +"""Visamos a """
                    b(classes = "negritocartao1") { +"""construção do conhecimento """ }
                    +"""nas mais diversas áreas: idiomas, cultura e até softwares. Tudo isso de forma """
                    b(classes = "negritocartao1") { +"""íntegra e gratuita""" }
                    +""", preservando, assim, tanto o ensinar quanto o aprender."""
                }
            }
        }
        /*FIM MISSAO*/

        img(classes = "responsive-img") {
            src = "/img/aula_fade1.png"
        }

        /*CONQUISTAS*/
        div(classes = "row rowzeras2") {
            div(classes = "col s12 m8 offset-m2 l8 offset-l2") {
                div(classes = "card cardzeras2") {
                    +"""No primeiro semestre de 2020 """
                    b(classes = "negritocartao2") { +"""mais de 3.900 pessoas """ }
                    +"""tanto no Brasil quanto no exterior se inscreveram em cursos da Share"""
                }
            }

            div(classes = "col s12 m10 offset-m1 l10 offset-l1") {
                div(classes = "card cardzeras21") {
                    +"""Oferecemos """
                    b(classes = "negritocartao2") { +"""18 cursos""" }
                    +""" e ofertamos """
                    b(classes = "negritocartao2") { +"""370 vagas""" }
                }
            }

            div(classes = "col s12 m10 offset-m1 l10 offset-l1") {
                div(classes = "card cardzeras22") {
                    +"""Fomos destaque no """
                    a(classes = "linknot") {
                        href = "https://g1.globo.com/sp/sorocaba-jundiai/noticia/2020/04/23/centro-de-linguas-da-ufscar-sorocaba-recebe-inscricoes-para-cursos-gratuitos.ghtml"
                        target = "“_blank”"
                        +"""G1 Notícias"""
                    }
                    +""","""
                    a(classes = "linknot") {
                        href = "https://www.sorocaba.ufscar.br/noticia?codigo=12631"
                        target = "“_blank”"
                        +""" Site da UFSCar Sorocaba,"""
                    }
                    br{

                    }
                    +"""Rádio Jornal Cruzeiro do Sul e TV Sorocaba(SBT)"""
                }
            }
        }
        /*FIM CONQUISTAS*/

        h3(classes = "wow animate__animated animate__slideInUp citacao") { +""""Conectar o desejo de ensinar com a vontade de aprender"""" }

        /*DEPOIMENTOS*/
        div(classes = "row rowzeras3") {
            div(classes = "col s12 m10 offset-m1 l10 offset-l1") {
                div(classes = "card cardzeras3") {
                    i { +""""Fazer o curso de oratória na share, para mim, foi incrível! Além de ter a oportunidade de conhecer pessoas de outros cursos e pessoas de fora da UFSCar, as aulas me proporcionaram um aprendizado muito rico sobre técnicas de apresentação, como se portar durante um discurso e como ser claro e coerente ao passar uma mensagem verbal. Indico a Share para todos aqueles que tem vontade de aprender de uma forma inovadora!"""" }
                    br{

                    }
                    b { +"""- Vitória Benevenuto, foi aluna do curso de Oratória""" }
                }
            }


            div(classes = "col s12 m10 offset-m1 l10 offset-l1") {
                div(classes = "card cardzeras3") {
                    i { +""""Eu fiz o curso de espanhol pós intermediário e foi muito bom, gostei muito da relação aluno-professor que eu tive com a minha professora (tanto que agora ela é minha aluna de alemão). Já sabia muito do idioma, mas eu não tinha nenhuma prova ou certificado e isso me ajudou muito; quase fui contratada como professora de espanhol esse ano, mas por não atender meu horario de disponibilidade mesmo que eu não consegui"""" }
                    br{

                    }
                    b { +"""- Helena do Carmo, foi aluna do curso de Espanhol Pós-intermediário""" }
                }
            }


            div(classes = "col s12 m10 offset-m1 l10 offset-l1") {
                div(classes = "card cardzeras3") {
                    i { +""""Estudei inglês com a Share durante o ano de 2018 e foi uma experiência bastante proveitosa, pude rever conceitos do qual tinha dúvidas, aumentar meu vocabulário e aprofundar meu conhecimento. Foi a minha primeira oportunidade dentro de uma sala de aula para o aprendizado de inglês e hoje posso afirmar que consigo compreender a língua de uma forma bem melhor. É um ótimo projeto que recomendo para toda a comunidade de dentro e de fora da UFSCar."""" }
                    br{

                    }
                    b { +"""- Joice Natália Araújo, foi aluna do curso de Inglês""" }
                }
            }
            div(classes = "col s12 m10 offset-m1 l10 offset-l1") {
                div(classes = "card cardzeras3") {
                    i { +""""Fiz Inglês básico em 2019 e a experiência foi além das minhas expectativas. A professora explicava muito bem e trouxe um material muito legal; dava atividades para praticarmos em casa e sempre nos incentivava e dava dicas. Também foi muito legal ver pessoas fora da comunidade da UFSCar fazendo o curso com vontade de aprender e se esforçando bastante, a entidade realmente proporciona muitas oportunidades para todos."""" }
                    br{

                    }
                    b { +"""- Francielli Viana, foi aluna do curso de Inglês""" }
                }
            }
        }
        /*FIM DEPOIMENTOS*/

        div(classes = "parallax-container") {
            div(classes = "parallax") {
                img {
                    src = "/img/time_parallax.png"
                }
            }
        }





/*
        div("container") {
            model.cards.takeIf { it.isNotEmpty() }?.let { renderCards(it) }
            div("row") {
                div("col s12 xl8") {
                    h5("underlined") { +"Notícias:" }

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

    private fun DIV.renderCards(noticias: List<Noticia>) {
        h5("underlined") { +"Recente:" }
        div("row") {
            when (noticias.size) {
                1 -> {
                    div("col s12") { renderCard(noticias[0]) }
                }
                2 -> {
                    div("col s12 m6") { renderCard(noticias[0]) }
                    div("col s12 m6") { renderCard(noticias[1]) }
                }
                3 -> {
                    div("col s12 m6 xl4") { renderCard(noticias[0]) }
                    div("col s12 m6 xl4") { renderCard(noticias[1]) }
                    div("col s12 xl4") { renderCard(noticias[2]) }
                }
            }
        }
    }

    private fun DIV.renderCard(noticia: Noticia) {
        div("card") {
            div("card-content") {
                span("card-title") { +noticia.titulo }
                p("justify") {
                    +Jsoup.parseBodyFragment(noticia.html, "/").text().compressSpaces().limit(280)
                }
            }
            div("card-action") { a("/n/${noticia.id}") { +"Leia mais" } }
        }
    }

    private fun DIV.renderNoticias(noticias: List<Noticia>) {
        noticias.forEach {
            article {
                h3 { +it.titulo }
                p("article-info") {
                    a("/n/${it.id}") {
                        +DateTimeFormatter.RFC_1123_DATE_TIME.format(it.dataCriacao)
                        +" por "
                        +(model.pessoas[it.criadoPorPessoa]?.nome ?: "Usuário removido")
                    }
                }
                div("justify") { unsafe { +it.html } }
                p("compartilhamento-titulo"){+"Compartilhe:"}
                ul("social-sharing") {
                    li { a("https://www.facebook.com/sharer/sharer.php?u=https://associacaoshare.com.br/n/${it.id}", classes = "img-fb") { +"Facebook" } }
                    li { a("https://api.whatsapp.com/send?text=https://associacaoshare.com.br/n/${it.id}", classes = "img-wpp") { +"Whatsapp" } }
                    li { a("https://www.linkedin.com/shareArticle?mini=true&url=https://associacaoshare.com.br/n/${it.id}", classes = "img-ln") { +"LinkedIn" } }
                }
            }
        }*/
        
        script("text/javascript", "/js/m_parallax.js") {}
        script("text/javascript", "/js/m_slider.js") {}

    }

    private fun DIV.renderCards(noticias: List<Noticia>) {
        when (noticias.size) {
            1 -> {
                    ul(classes = "slides") {
                        li {
                            img {
                                src = "../img/noticia1.png"
                            }
                            div(classes = "caption left-align") {
                                h3("slides-posttitle") { +noticias[0].titulo }
                                h5(classes = "light grey-text text-lighten-3 slides-postcontent") { +Jsoup.parseBodyFragment(noticias[0].html, "/").text().compressSpaces().limit(80)}
                                a("blog", classes = "btn waves-effect white grey-text darken-text-2") { +"""Ver Blog da Share""" }
                            }
                        }
                    }
                }
            2 -> {
                ul(classes = "slides") {
                    li {
                        img {
                            src = "../img/noticia1.png"
                        }
                        div(classes = "caption left-align") {
                            h3("slides-posttitle") { +noticias[0].titulo }
                            h5(classes = "light grey-text text-lighten-3 slides-postcontent") { +Jsoup.parseBodyFragment(noticias[0].html, "/").text().compressSpaces().limit(80)}
                            a("blog", classes = "btn waves-effect white grey-text darken-text-2") { +"""Ver Blog da Share""" }
                        }
                    }

                    li {
                        img {
                            src = "../img/noticia_2.png"
                        }
                        div(classes = "caption left-align") {
                            h3("slides-posttitle") { +noticias[1].titulo }
                            h5(classes = "text-lighten-3 slides-postcontent") { +Jsoup.parseBodyFragment(noticias[1].html, "/").text().compressSpaces().limit(80)}
                            a("blog", classes = "btn waves-effect white grey-text darken-text-2") { +"""Ver Blog da Share""" }
                        }
                    }
                }
            }
            3 -> {
                ul(classes = "slides") {
                    li {
                        img {
                            src = "../img/noticia1.png"
                        }
                        div(classes = "caption left-align") {
                            h3("slides-posttitle") { +noticias[0].titulo }
                            h5(classes = "light grey-text text-lighten-3 slides-postcontent") { +Jsoup.parseBodyFragment(noticias[0].html, "/").text().compressSpaces().limit(80)}
                            a("blog", classes = "btn waves-effect white grey-text darken-text-2") { +"""Ver Blog da Share""" }
                        }
                    }
                    li {
                        img {
                            src = "../img/noticia_2.png"
                        }
                        div(classes = "caption left-align") {
                            h3("slides-posttitle") { +noticias[1].titulo }
                            h5(classes = "text-lighten-3 slides-postcontent") { +Jsoup.parseBodyFragment(noticias[1].html, "/").text().compressSpaces().limit(80)}
                            a("blog", classes = "btn waves-effect white grey-text darken-text-2") { +"""Ver Blog da Share""" }
                        }
                    }
                    li {
                        img {
                            src = "../img/noticia_3.png"
                        }
                        div(classes = "caption left-align") {
                            h3("slides-posttitle") { +noticias[2].titulo }
                            h5(classes = "text-lighten-3 slides-postcontent") { +Jsoup.parseBodyFragment(noticias[2].html, "/").text().compressSpaces().limit(80)}
                            a("blog", classes = "btn waves-effect white grey-text darken-text-2") { +"""Ver Blog da Share""" }
                        }
                    }
                }
            }
        }
    }
}