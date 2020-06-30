package br.com.associacaoshare.model

import br.com.associacaoshare.model.dao.DataAccessObject

data class Usuario(
    var username: String,
    var hash: String,
    var pessoaId: Int,
    var admin: Boolean
) {
    fun hashPassword(password: String) {
        hash = DataAccessObject.hashPassword(password)
    }
}

