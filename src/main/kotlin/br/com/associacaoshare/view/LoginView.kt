package br.com.associacaoshare.view

import br.com.associacaoshare.model.page.PagIniModel
import br.com.associacaoshare.view.base.PagIniView
import io.javalin.http.Context
import kotlinx.html.*
import kotlinx.html.ButtonType.submit
import kotlinx.html.FormMethod.post
import kotlinx.html.InputType.password
import kotlinx.html.InputType.text

class LoginView(override val model: PagIniModel) : PagIniView(Type.LOGIN) {
    override val pageTitle = "Login"

    override fun MAIN.renderMain(ctx: Context) {
        div("container loginbox") {
            div("loginbox-inner row grey lighten-4 z-depth-1") {
                img("Logo da Share", "/img/share-loginbox.png", "loginbox-img")
                form("/doLogin", method = post, classes = "col s12") {
                    ctx.queryParam("err")?.let { err ->
                        div("card-panel loginbox-warnbox red lighten-2 white-text center-align") {
                            when (err) {
                                "invalidcreds" -> +"Credenciais inválidas."
                                "unauthorized" -> +"Por favor, faça login."
                                else -> +"Erro desconhecido."
                            }
                        }
                    }

                    ctx.queryParam("then")?.let { then -> hiddenInput(name = "then") { value = then } }

                    div("input-field") {
                        label {
                            htmlFor = "username"
                            +"Nome de usuário"
                        }
                        input(text, classes = "validate", name = "username") {
                            id = "username"
                            ctx.queryParam("username")?.let { value = it }
                        }
                    }

                    div("input-field") {
                        label {
                            htmlFor = "password"
                            +"Senha"
                        }
                        input(password, classes = "validate", name = "password") {
                            id = "password"
                        }
                    }

                    p("center-align") {
                        label {
                            checkBoxInput(name = "ilovecookies")
                            span("bold") { +"Lembrar-me" }
                        }
                    }

                    button(type = submit, classes = "col s12 btn btn-large waves-effect light-blue lighten-2") {
                        +"Entrar"
                    }
                }
            }
        }
    }
}