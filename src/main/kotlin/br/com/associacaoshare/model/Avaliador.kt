package br.com.associacaoshare.model

import br.com.associacaoshare.model.dao.DataAccessObject

data class Avaliador(
        val id: Int,
        var username: String,
        var hash: String,
        var nome: String
) {
    fun hashPassword(password: String) {
        hash = DataAccessObject.hashPassword(password)
    }
}