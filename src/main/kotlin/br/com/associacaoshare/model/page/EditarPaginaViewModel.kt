package br.com.associacaoshare.model.page

import br.com.associacaoshare.model.Pagina
import com.fasterxml.jackson.databind.ObjectMapper

class EditarPaginaViewModel(
    val mapper: ObjectMapper,
    val pagina: Pagina?,
    val editing: Boolean
) : PagIniAdminModel()