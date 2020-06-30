package br.com.associacaoshare.view

import br.com.associacaoshare.model.page.EditarUsuarioViewModel
import br.com.associacaoshare.view.base.PagIniAdminView
import io.javalin.http.Context
import kotlinx.html.*
import kotlinx.html.ButtonType.submit
import kotlinx.html.FormMethod.post

class EditarUsuarioView(override val model: EditarUsuarioViewModel) : PagIniAdminView() {
    override val pageTitle = if (model.self) "Perfil do Usuário" else if (model.usuario == null) "Criação de Usuário" else "Edição de Usuário"

    override fun MAIN.renderMain(ctx: Context) {
        div("container") {
            h5("underlined") { +pageTitle }
            form(method = post) {
                div("row") {
                    div("input-field col s12 mb-0") {
                        label {
                            htmlFor = "inputNome"
                            +"Nome Completo"
                        }
                        input(InputType.text, classes = "validate", name = "nome") {
                            id = "inputNome"
                            placeholder = "Nome Completo"
                            value = model.pessoa.nome
                        }
                    }

                    div("input-field col s12 mb-0") {
                        label {
                            htmlFor = "inputUsername"
                            +"Nome de usuário"
                        }
                        input(InputType.text, classes = "validate", name = "username") {
                            id = "inputUsername"
                            placeholder = "Nome de usuário"
                            model.usuario?.let { value = it.username }
                        }
                    }

                    div("input-field col s12 mb-0") {
                        label {
                            htmlFor = "inputPassword"
                            +"Senha"
                        }
                        input(InputType.password, classes = "validate", name = "password") {
                            id = "inputPassword"
                            if (model.usuario != null) {
                                placeholder = "Nova Senha (Opcional)"
                            } else {
                                placeholder = "Senha"
                            }
                        }
                    }

                    if (!model.self) {
                        div("input-field col s12 mb-0") {
                            p {
                                label {
                                    checkBoxInput(name = "superadmin") {
                                        model.usuario?.let {
                                            checked = it.admin
                                        }
                                    }
                                    span { +"Dar superpoderes" }
                                }
                            }
                        }
                    }

                    div("col s12 input-field") {
                        button(type = submit, classes = "btn waves-effect light-blue lighten-2") {
                            +if (model.self) "Salvar" else if (model.usuario == null) "Criar Usuário" else "Editar Usuário"
                            i("material-icons right") { +"send" }
                        }
                    }
                }
            }
        }
    }
}
//