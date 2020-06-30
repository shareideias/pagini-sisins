package br.com.associacaoshare.model

import java.time.OffsetDateTime

data class Pessoa(
    val id: Int,
    var nome: String,
    val dataCriacao: OffsetDateTime = OffsetDateTime.now()
)

