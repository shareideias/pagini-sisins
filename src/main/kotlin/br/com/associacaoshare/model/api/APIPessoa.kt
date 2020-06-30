package br.com.associacaoshare.model.api

import java.time.OffsetDateTime

data class APIPessoa(
    val id: Int,
    val nome: String,
    val dataCriacao: OffsetDateTime = OffsetDateTime.now()
)

