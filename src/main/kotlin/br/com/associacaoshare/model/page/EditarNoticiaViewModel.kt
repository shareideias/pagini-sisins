package br.com.associacaoshare.model.page

import br.com.associacaoshare.model.Noticia
import com.fasterxml.jackson.databind.ObjectMapper

class EditarNoticiaViewModel(
    val mapper: ObjectMapper,
    val noticia: Noticia?,
    val editing: Boolean
) : PagIniAdminModel()