package br.com.associacaoshare.view.alunos

import br.com.associacaoshare.view.base.HtmlBuilderView
import br.com.associacaoshare.view.base.SisInsAlunoView
import io.javalin.http.Context
import kotlinx.html.*

class LoginView(private val errormsg: String?) : SisInsAlunoView() {
    override val pageTitle: String
        get() = "Login"

    override fun BODY.renderBody(ctx: Context) {
        link(type = "text/css", rel = "stylesheet", href = "/css/sisins_login.css")

        link(type = "text/css", rel = "stylesheet", href = "/css/alerts.css")

        header{
            nav("nav-wrapper transparent") {
                div("container") {
                    /*a("brand-logo") {
                        img("Logo da Share", "/img/navbar-brand.png", "share-brand")
                    }*/
                    a("#", classes = "sidenav-trigger") {
                        attributes["data-target"] = "mobile-menu"
                        i("material-icons") { +"menu" }
                    }

                    ul("right hide-on-med-and-down") {
                        li {
                            a("/", classes = "link_menu desknav") { +"Home" }
                        }
                        li {
                            a("/cursos", classes = "link_menu desknav") { +"Cursos" }
                        }
                        li {
                            a("/faq", classes = "link_menu desknav") { +"F.A.Q." }
                        }
                        li {
                            a("/blog", classes = "link_menu desknav") { +"Blog" }
                        }
                        li {
                            a("/alunos", classes = "link_menu desknav") { +"Alunos" }
                        }
                        li {
                            a("/adm", classes = "link_menu desknav") { +"Administrativo" }
                        }
                    }
                    ul("sidenav lighten-2") {
                        id = "mobile-menu"
                        li {
                            a("/", classes = "link_menu") { +"Home" }
                        }
                        li {
                            a("/cursos", classes = "link_menu") { +"Cursos" }
                        }
                        li {
                            a("/faq", classes = "link_menu") { +"F.A.Q." }
                        }
                        li {
                            a("/blog", classes = "link_menu") { +"Blog" }
                        }
                        li {
                            a("/alunos", classes = "link_menu") { +"Alunos" }
                        }
                        li {
                            a("/adm", classes = "link_menu") { +"Administrativo" }
                        }
                    }
                }
            }
        }


        if (!errormsg.isNullOrEmpty()) {
            div("materialert error") {
                div("material-icons") { +"error_outline" }
                +"$errormsg"
            }
        }

        div("row") {

            div("col s12 m12 l3 offset-l3") {
                div("card") {
                    div("card-image") {
                        img("Logo da Share", "../img/share-logo.png", "logo")
                    }
                }
            }
            div("center col s12 m12 l3") {
                div("caixa_login") {
                    div("lighten-4 row geral_login") {
                        form("/inscricoes/dologin", classes = "col s12", method = FormMethod.post) {
                            div("row") {
                                div("input-field col s12") {
                                    input(InputType.text, classes = "validate", name = "user") {
                                        id = "user"
                                    }
                                    label() {
                                        htmlFor = "user"
                                        +"Email"
                                    }
                                }
                            }

                            div("row") {
                                div("input-field col s12") {
                                    input(InputType.password, classes = "validate", name = "password") {
                                        id = "password"
                                    }
                                    label() {
                                        htmlFor = "password"
                                        +"Senha"
                                    }
                                }
                            }

                            button(type = ButtonType.submit, classes = "entrar waves-effect waves-light btn") {
                                +"Entrar"
                            }
                        }
                    }
                }

            }

        }
    }
}
