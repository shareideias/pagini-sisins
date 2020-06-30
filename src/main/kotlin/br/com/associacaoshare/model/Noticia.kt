package br.com.associacaoshare.model

import java.time.OffsetDateTime

data class Noticia(
    val id: Int,
    var titulo: String, //[!]
    var html: String, //[!] summernote
    val criadoPorPessoa: Int,
    val dataCriacao: OffsetDateTime = OffsetDateTime.now(),
    var dataModificacao: OffsetDateTime? = null,
    var ultimaModificacaoPorPessoa: Int? = null
)