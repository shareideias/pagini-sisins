package br.com.associacaoshare.model

import br.com.associacaoshare.model.dao.DataAccessObject
import java.time.LocalDate

data class Participante(
    val id: Int,
    var categoria: Int,
    var nome: String,
    var data_nascimento: LocalDate,
    var telefone: String,
    var email: String,
    var hash: String,
    var tipo_sem_vinculo: Int,
    var vinculo_ufscar: Int,
    var escola: String,
    var esteve_ufscar: Int,
    var edital: Int,
    var onde_conheceu: Int,
    var local_aulas: Int,
    var disponibilidade: String,
    var objetivo: Int,
    var cursou_share: Int,
    var desistencia: Int,
    var redacao_entrada: String,

    var curso1_id: Int?,
    var data_inscricao_c1: LocalDate?,
    var resposta1_c1: Int?,
    var resposta2_c1: Int?,
    var resposta3_c1: Int?,
    var resposta4_c1: Int?,
    var resposta5_c1: Int?,
    var resposta6_c1: Int?,
    var avaliador_id_c1: Int?,
    var resultado_c1: Int? = -1,

    var curso2_id: Int?,
    var data_inscricao_c2: LocalDate?,
    var resposta1_c2: Int?,
    var resposta2_c2: Int?,
    var resposta3_c2: Int?,
    var resposta4_c2: Int?,
    var resposta5_c2: Int?,
    var resposta6_c2: Int?,
    var avaliador_id_c2: Int?,
    var resultado_c2: Int? = -1
) {
    fun hashPassword(password: String) {
        hash = DataAccessObject.hashPassword(password)
    }

    fun atualizaDados(respostasPack: Map<String, List<String>>) {
        val respostas : Map<String, String> = respostasPack.mapValues {(key, value) -> (value[0])}
        val categoria: String by respostas
        this.categoria = categoria.toInt()
        val nome: String by respostas
        this.nome = nome
        val data_nascimento: String by respostas
        val dataNascimentoLD = LocalDate.parse(data_nascimento)
        this.data_nascimento = dataNascimentoLD
        val telefone: String by respostas
        this.telefone = telefone
        val email: String by respostas
        this.email = email
        val tipo_sem_vinculo: String by respostas
        this.tipo_sem_vinculo = tipo_sem_vinculo.toInt()
        val vinculo_ufscar: String by respostas
        this.vinculo_ufscar = vinculo_ufscar.toInt()
        val escola: String by respostas
        this.escola = escola
        val edital: String by respostas
        val editalInt: Int = edital.toInt()
        this.edital = editalInt
        val onde_conheceu: String by respostas
        val ondeConheceuInt: Int = onde_conheceu.toInt()
        this.onde_conheceu = ondeConheceuInt
        val esteve_ufscar: String by respostas
        val esteveUfscarInt: Int = esteve_ufscar.toInt()
        this.esteve_ufscar = esteveUfscarInt
        val local_aulas: String by respostas
        val localAulasInt: Int = local_aulas.toInt()
        this.local_aulas = localAulasInt
        val disponibilidade: String by respostas
        this.disponibilidade = disponibilidade
        val objetivo: String by respostas
        val objetivoInt: Int = objetivo.toInt()
        this.objetivo = objetivoInt
        val cursou_share: String by respostas
        val cursouShareInt: Int = cursou_share.toInt()
        this.cursou_share = cursouShareInt
        val desistencia: String by respostas
        val desistenciaInt: Int = desistencia.toInt()
        this.desistencia = desistenciaInt
        val redacao_entrada: String by respostas
        this.redacao_entrada = redacao_entrada

        val password: String by respostas
        if(password.isNotEmpty())
            hashPassword(password)
    }

    fun atualizaSenha(respostasPack: Map<String, List<String>>) {
        val respostas : Map<String, String> = respostasPack.mapValues {(key, value) -> (value[0])}
        val password: String by respostas
        if(password.isNotEmpty())
            hashPassword(password)
    }

    fun atualizaProva(respostasPack: Map<String, List<String>>){
        val respostas : Map<String, String> = respostasPack.mapValues {(key, value) -> (value[0])}
        val resposta1_c1: String by respostas
        this.resposta1_c1 = resposta1_c1.toInt()
        val resposta2_c1: String by respostas
        this.resposta2_c1 = resposta2_c1.toInt()
        val resposta3_c1: String by respostas
        this.resposta3_c1 = resposta3_c1.toInt()
        val resposta4_c1: String by respostas
        this.resposta4_c1 = resposta4_c1.toInt()
        val resposta5_c1: String by respostas
        this.resposta5_c1 = resposta5_c1.toInt()
        val resposta6_c1: String by respostas
        this.resposta6_c1 = resposta6_c1.toInt()
    }

    fun atualizaProva2(respostasPack: Map<String, List<String>>){
        val respostas : Map<String, String> = respostasPack.mapValues {(key, value) -> (value[0])}
        val resposta1_c2: String by respostas
        this.resposta1_c2 = resposta1_c2.toInt()
        val resposta2_c2: String by respostas
        this.resposta2_c2 = resposta2_c2.toInt()
        val resposta3_c2: String by respostas
        this.resposta3_c2 = resposta3_c2.toInt()
        val resposta4_c2: String by respostas
        this.resposta4_c2 = resposta4_c2.toInt()
        val resposta5_c2: String by respostas
        this.resposta5_c2 = resposta5_c2.toInt()
        val resposta6_c2: String by respostas
        this.resposta6_c2 = resposta6_c2.toInt()
    }
}