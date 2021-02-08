package br.com.associacaoshare.model.exception

class FalhaNaInscricao (
        msg: String = "Erro na inscrição, tente novamente."
) : Exception(msg)