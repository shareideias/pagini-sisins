package br.com.associacaoshare.view.base

import br.com.associacaoshare.model.page.PagIniModel
import io.javalin.http.Context
import kotlinx.html.*

abstract class PagIniView(val type: Type) : HtmlBuilderView() {
    abstract val model: PagIniModel

    enum class Type(val cssModules: List<String>) {
        INDEX(listOf("public", "index")),
        LOGIN(listOf("login")),
        PUBLIC_PAGE(listOf("public")),
        ERROR_PAGE(listOf("public", "error")),
        ADMIN_PAGE(emptyList())
    }

    protected open val pageTitle: String? = null
    protected open val mainPage: String = "/"

    override fun HTML.render(ctx: Context) {
        head {
            meta(charset = "utf-8")
            meta("viewport", "width=device-width, initial-scale=1.0")

            link("https://fonts.googleapis.com/icon?family=Material+Icons", "stylesheet")
            link("/css/materialize.min.css", "stylesheet")
            link("/css/pagini.css", "stylesheet")

            /*jquery*/
            script {
                src = "https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"
            }
            /*animacao*/
            link {
                rel = "stylesheet"
                href = "https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css"
            }
            script {
                src = "/js/wow.min.js"
            }
            script {
                src = "/js/wowanimate.js"
            }
            /*fim animacao*/
            /*fonte*/
            link {
                href = "https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
                rel = "stylesheet"
            }




            for (module in type.cssModules) {
                link("/css/pagini_$module.css", "stylesheet")
            }
            afterLinks(ctx)

            link("https://associacaoshare.com.br${ctx.path()}", "canonical")

            meta("author", "Associação Share")
            meta("description", "A Share é uma Entidade Estudantil fundada em 2016 por alunos de Ciências Econômicas na UFSCar - Campus Sorocaba, com o intuito de conectar a vontade de ensinar com a vontade de aprender oferecendo cursos semestrais de idioma, culturais e administrativos.")
            meta("keywords", "Associação Share, UFSCar Sorocaba, Centro Línguas, Instituto Share; Share Ideias")

            meta("twitter:card", "summary")

            when {
                pageTitle.isNullOrBlank() -> {
                    title("Associação Share")
                    opgProperty("og:title", "Página sem título")
                    opgProperty("og:site_name", "Associação Share")
                }
                type == Type.INDEX -> {
                    title("Associação Share · $pageTitle")
                    opgProperty("og:title", "Associação Share")
                    opgProperty("og:site_name", "Associação Share")
                }
                else -> {
                    title("Associação Share · $pageTitle")
                    opgProperty("og:title", "$pageTitle")
                    opgProperty("og:site_name", "Associação Share")
                }
            }

            opgProperty("og:type", "website")
            opgProperty("og:url", "https://associacaoshare.com.br${ctx.path()}")
            opgProperty("og:image", "https://associacaoshare.com.br/img/card.png")
            opgProperty("og:locale", "pt_BR")

            link(rel = "apple-touch-icon-precomposed", href = "/ico/apple-touch-icon-57x57.png") { attributes["sizes"] = "57x57" }
            link(rel = "apple-touch-icon-precomposed", href = "/ico/apple-touch-icon-114x114.png") { attributes["sizes"] = "114x114" }
            link(rel = "apple-touch-icon-precomposed", href = "/ico/apple-touch-icon-72x72.png") { attributes["sizes"] = "72x72" }
            link(rel = "apple-touch-icon-precomposed", href = "/ico/apple-touch-icon-144x144.png") { attributes["sizes"] = "144x144" }
            link(rel = "apple-touch-icon-precomposed", href = "/ico/apple-touch-icon-60x60.png") { attributes["sizes"] = "60x60" }
            link(rel = "apple-touch-icon-precomposed", href = "/ico/apple-touch-icon-120x120.png") { attributes["sizes"] = "120x120" }
            link(rel = "apple-touch-icon-precomposed", href = "/ico/apple-touch-icon-76x76.png") { attributes["sizes"] = "76x76" }
            link(rel = "apple-touch-icon-precomposed", href = "/ico/apple-touch-icon-152x152.png") { attributes["sizes"] = "152x152" }
            link(rel = "icon", type = "image/png", href = "/ico/favicon-196x196.png") { attributes["sizes"] = "196x196" }
            link(rel = "icon", type = "image/png", href = "/ico/favicon-96x96.png") { attributes["sizes"] = "96x96" }
            link(rel = "icon", type = "image/png", href = "/ico/favicon-32x32.png") { attributes["sizes"] = "32x32" }
            link(rel = "icon", type = "image/png", href = "/ico/favicon-16x16.png") { attributes["sizes"] = "16x16" }
            link(rel = "icon", type = "image/png", href = "/ico/favicon-128.png") { attributes["sizes"] = "128x128" }
            meta(name = "application-name", content = "Associação Share")
            meta(name = "msapplication-TileColor", content = "#FFFFFF")
            meta(name = "msapplication-TileImage", content = "/ico/mstile-144x144.png")
            meta(name = "msapplication-square70x70logo", content = "/ico/mstile-70x70.png")
            meta(name = "msapplication-square150x150logo", content = "/ico/mstile-150x150.png")
            meta(name = "msapplication-wide310x150logo", content = "/ico/mstile-310x150.png")
            meta(name = "msapplication-square310x310logo", content = "/ico/mstile-310x310.png")
        }
        body {
            header { renderHeader() }
            main { renderMain(ctx) }

            if ("public" in type.cssModules) {
                footer("public-footer") { renderFooter() }
            }
            beforeScripts(ctx)
            renderScripts()
            afterScripts(ctx)
        }
    }

    protected open fun HEAD.afterLinks(ctx: Context) {
    }

    protected open fun MAIN.renderMain(ctx: Context) {
    }

    protected open fun BODY.beforeScripts(ctx: Context) {
    }

    protected open fun BODY.afterScripts(ctx: Context) {
    }

    private fun HEADER.renderHeader() {
        nav("nav-wrapper transparent") {
            div("container") {
                a(mainPage, classes = "brand-logo") {
                    img("Logo da Share", "/img/navbar-brand.png", "share-brand")
                }
                a("#", classes = "sidenav-trigger") {
                    attributes["data-target"] = "mobile-menu"
                    i("material-icons") { +"menu" }
                }

                ul("right hide-on-med-and-down") {
                    model.navLinks.forEach { li { a(it.href, classes = "link_menu") { +it.nome } } }
                }
                ul("sidenav lighten-2") {
                    id = "mobile-menu"
                    model.navLinks.forEach { li { a(it.href, classes = "link_menu") { +it.nome } } }
                }
            }
        }
        /*if (type == Type.INDEX) {
            img("'Share' em Manuscrito", "/img/handwritten.png", "handwritten-logo")
        }*/
    }

    private fun FOOTER.renderFooter() {
        /*Editado pela Braca*/
        ul("social-networks") {
            li { a("https://www.facebook.com/shareideias/", classes = "img-fb-footer") { +"Facebook" } }
            li { a("https://www.instagram.com/shareideias/", classes = "img-ig-footer") { +"Instagram" } }
            li { a("https://www.linkedin.com/company/associacaoshare/", classes = "img-ln-footer") { +"LinkedIn" } }
        }
        a(classes = "white-text") {
            href = "https://br.freepik.com/fotos-vetores-gratis/escola"
            +"""Foto do header criado por jcomp - br.freepik.com"""
        }
        br{

        }
        a(classes = "white-text") {
            href = "https://br.freepik.com/fotos-vetores-gratis"
            +"""Fotos das notícias criadas por freepik"""
        }
        br{

        }
        div(classes="white-text") {
            +"""Icons feitos por """
            a(classes="white-text") {
                href = "https://www.flaticon.com/authors/freepik"
                title = "Freepik"
                +""" Freepik"""
            }
            +""" de """
            a(classes="white-text") {
                href = "https://www.flaticon.com/"
                title = "Flaticon"
                +"""www.flaticon.com"""
            }
        }
        p("center-align bold white-text") { +"© Share. Todos os direitos reservados." }
        /*Editado pela Braca*/
    }

    private fun BODY.renderScripts() {
        script(src = "/js/materialize.min.js") {}
        script(src = "/js/m_sidenav.js") {}
    }

    private fun MetaDataContent.opgProperty(property: String, content: String) {
        meta {
            attributes["property"] = property
            attributes["content"] = content
        }
    }
}