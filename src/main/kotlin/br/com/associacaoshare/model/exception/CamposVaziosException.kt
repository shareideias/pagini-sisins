package br.com.associacaoshare.model.exception

class CamposVaziosException (
        campo: String
) : Exception("O campo $campo é obrigatório e não foi preenchido.")