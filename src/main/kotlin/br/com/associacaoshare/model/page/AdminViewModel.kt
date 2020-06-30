package br.com.associacaoshare.model.page

import br.com.associacaoshare.model.*

open class AdminViewModel(
    val noticias: List<Noticia>,
    val paginas: List<Pagina>,
    val pessoaMap: Map<Int, Pessoa>,
    val links: List<Link>
) : PagIniAdminModel()

class SuperAdminViewModel(
    val self: Usuario,
    val pessoas: List<Pair<Pessoa, Usuario?>>,
    noticias: List<Noticia>,
    paginas: List<Pagina>,
    pessoaMap: Map<Int, Pessoa>,
    links: List<Link>
) : AdminViewModel(noticias, paginas, pessoaMap, links)