package br.com.associacaoshare.model.page

import br.com.associacaoshare.model.Link
import br.com.associacaoshare.model.Pagina

class PaginaViewModel(
    navLinks: List<Link>,
    val pagina: Pagina
) : PagIniModel(navLinks)