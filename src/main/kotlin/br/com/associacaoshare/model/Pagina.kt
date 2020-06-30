package br.com.associacaoshare.model

import java.time.OffsetDateTime

data class Pagina(
    val linkPagina: String, //[!]
    var titulo: String, //[!]
    var html: String, //[!]note
    val criadoPorPessoa: Int,
    var dataCriacao: OffsetDateTime = OffsetDateTime.now(),
    var dataModificacao: OffsetDateTime? = null,
    var ultimaModificacaoPorPessoa: Int? = null
) {
    fun setModificado(pessoaId: Int) {
        dataModificacao = OffsetDateTime.now()
        ultimaModificacaoPorPessoa = pessoaId
    }
}