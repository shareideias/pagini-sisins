package br.com.associacaoshare.model.page

import br.com.associacaoshare.model.Link

private val adminLinks = listOf(
    Link(0, "Ver Site", "/"),
    Link(1, "Perfil", "/admin/perfil"),
    Link(2, "Logout", "/logout")
)

open class PagIniAdminModel : PagIniModel(adminLinks) {
    companion object : PagIniAdminModel()
}