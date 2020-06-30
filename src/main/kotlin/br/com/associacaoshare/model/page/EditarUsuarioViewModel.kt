package br.com.associacaoshare.model.page

import br.com.associacaoshare.model.Pessoa
import br.com.associacaoshare.model.Usuario

class EditarUsuarioViewModel(
    val pessoa: Pessoa,
    val usuario: Usuario?,
    val self: Boolean
) : PagIniAdminModel()