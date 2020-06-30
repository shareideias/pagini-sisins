package br.com.associacaoshare.model.exception

class FalhaSessaoException (
        msg: String = "Faça login para acessar esta página."
) : Exception(msg)