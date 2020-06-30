package br.com.associacaoshare.view.base

import br.com.associacaoshare.model.page.PagIniAdminModel
import br.com.associacaoshare.view.base.PagIniView.Type.ADMIN_PAGE

abstract class PagIniAdminView : PagIniView(ADMIN_PAGE) {
    override val model: PagIniAdminModel = PagIniAdminModel
    override val mainPage = "/admin"
}