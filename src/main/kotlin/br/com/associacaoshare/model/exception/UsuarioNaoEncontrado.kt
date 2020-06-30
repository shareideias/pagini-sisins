package br.com.associacaoshare.model.exception

class UsuarioNaoEncontrado (
        msg: String = "Usuário não econtrado. Verifique os dados inseridos."
) : Exception(msg)