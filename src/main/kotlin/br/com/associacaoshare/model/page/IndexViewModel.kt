package br.com.associacaoshare.model.page

import br.com.associacaoshare.model.Link
import br.com.associacaoshare.model.Noticia
import br.com.associacaoshare.model.Pessoa

class IndexViewModel(
    navLinks: List<Link>,
    val pageNumber: Int,
    val cards: List<Noticia>,
    val noticias: List<Noticia>,
    val pessoas: Map<Int, Pessoa>
) : PagIniModel(navLinks)