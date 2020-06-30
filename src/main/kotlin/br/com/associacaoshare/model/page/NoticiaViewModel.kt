package br.com.associacaoshare.model.page

import br.com.associacaoshare.model.Link
import br.com.associacaoshare.model.Noticia
import br.com.associacaoshare.model.Pessoa

class NoticiaViewModel(
    navLinks: List<Link>,
    val noticia: Noticia,
    val criadoPorPessoa: Pessoa?,
    val ultimaModificacaoPorPessoa: Pessoa?
) : PagIniModel(navLinks)