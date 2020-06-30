package br.com.associacaoshare.model.api

import java.time.OffsetDateTime

data class APIPagina(
    val linkPagina: String,
    val titulo: String,
    val html: String,
    val texto: String,
    val criadoPor: APIPessoa,
    val dataCriacao: OffsetDateTime,
    val dataModificacao: OffsetDateTime?,
    val ultimaModificacaoPorPessoa: APIPessoa?
)