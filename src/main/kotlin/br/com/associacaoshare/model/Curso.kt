package br.com.associacaoshare.model

import java.time.LocalDate

data class Curso(
    val id: Int,
    var nome: String,
    var categoria: String,
    var horario: String,
    var pergunta1: String?,
    var pergunta2: String?,
    var pergunta3: String?,
    var pergunta4: String?,
    var pergunta5: String?,
    var pergunta6: String?,
    var alternativa11: String?,
    var alternativa12: String?,
    var alternativa13: String?,
    var alternativa14: String?,
    var alternativa15: String?,
    var alternativa21: String?,
    var alternativa22: String?,
    var alternativa23: String?,
    var alternativa24: String?,
    var alternativa25: String?,
    var alternativa31: String?,
    var alternativa32: String?,
    var alternativa33: String?,
    var alternativa34: String?,
    var alternativa35: String?,
    var alternativa41: String?,
    var alternativa42: String?,
    var alternativa43: String?,
    var alternativa44: String?,
    var alternativa45: String?,
    var alternativa51: String?,
    var alternativa52: String?,
    var alternativa53: String?,
    var alternativa54: String?,
    var alternativa55: String?,
    var alternativa61: String?,
    var alternativa62: String?,
    var alternativa63: String?,
    var alternativa64: String?,
    var alternativa65: String?
) {

    fun atualizaDados(respostasPack: Map<String, List<String>>) {
        val respostas : Map<String, String> = respostasPack.mapValues {(key, value) -> (value[0])}

        val nome: String by respostas
        this.nome = nome
        val horario: String by respostas
        this.horario = horario
        val categoria: String by respostas
        this.categoria = categoria
    }

    fun atualizaProva(respostasPack: Map<String, List<String>>) {
        val respostas : Map<String, String> = respostasPack.mapValues {(key, value) -> (value[0])}

        val pergunta1: String by respostas
        this.pergunta1 = pergunta1
        val pergunta2: String by respostas
        this.pergunta2 = pergunta2
        val pergunta3: String by respostas
        this.pergunta3 = pergunta3
        val pergunta4: String by respostas
        this.pergunta4 = pergunta4
        val pergunta5: String by respostas
        this.pergunta5 = pergunta5
        val pergunta6: String by respostas
        this.pergunta6 = pergunta6
        val alternativa11: String by respostas
        this.alternativa11 = alternativa11
        val alternativa12: String by respostas
        this.alternativa12 = alternativa12
        val alternativa13: String by respostas
        this.alternativa13 = alternativa13
        val alternativa14: String by respostas
        this.alternativa14 = alternativa14
        val alternativa15: String by respostas
        this.alternativa15 = alternativa15
        val alternativa21: String by respostas
        this.alternativa21 = alternativa21
        val alternativa22: String by respostas
        this.alternativa22 = alternativa22
        val alternativa23: String by respostas
        this.alternativa23 = alternativa23
        val alternativa24: String by respostas
        this.alternativa24 = alternativa24
        val alternativa25: String by respostas
        this.alternativa25 = alternativa25
        val alternativa31: String by respostas
        this.alternativa31 = alternativa31
        val alternativa32: String by respostas
        this.alternativa32 = alternativa32
        val alternativa33: String by respostas
        this.alternativa33 = alternativa33
        val alternativa34: String by respostas
        this.alternativa34 = alternativa34
        val alternativa35: String by respostas
        this.alternativa35 = alternativa35
        val alternativa41: String by respostas
        this.alternativa41 = alternativa41
        val alternativa42: String by respostas
        this.alternativa42 = alternativa42
        val alternativa43: String by respostas
        this.alternativa43 = alternativa43
        val alternativa44: String by respostas
        this.alternativa44 = alternativa44
        val alternativa45: String by respostas
        this.alternativa45 = alternativa45
        val alternativa51: String by respostas
        this.alternativa51 = alternativa51
        val alternativa52: String by respostas
        this.alternativa52 = alternativa52
        val alternativa53: String by respostas
        this.alternativa53 = alternativa53
        val alternativa54: String by respostas
        this.alternativa54 = alternativa54
        val alternativa55: String by respostas
        this.alternativa55 = alternativa55
        val alternativa61: String by respostas
        this.alternativa61 = alternativa61
        val alternativa62: String by respostas
        this.alternativa62 = alternativa62
        val alternativa63: String by respostas
        this.alternativa63 = alternativa63
        val alternativa64: String by respostas
        this.alternativa64 = alternativa64
        val alternativa65: String by respostas
        this.alternativa65 = alternativa65
    }
}