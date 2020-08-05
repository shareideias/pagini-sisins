package br.com.associacaoshare.model.dao

import br.com.associacaoshare.model.*
import java.security.MessageDigest
import java.time.LocalDate
import java.util.*

interface DataAccessObject {
    fun getUsuario(username: String): Usuario?
    fun getUsuarioByPessoa(pessoaId: Int): Usuario?
    fun getPessoa(id: Int): Pessoa?
    fun getNoticia(codigo: Int): Noticia?
    fun getPagina(link: String): Pagina?
    fun getLink(id: Int): Link?

    fun allUsuarios(): List<Usuario>
    fun allPessoas(): List<Pessoa>
    fun allNoticias(): List<Noticia>
    fun allPaginas(): List<Pagina>
    fun allLinks(): List<Link>

    fun insertUsuario(username: String, password: String, pessoaId: Int, admin: Boolean): Usuario
    fun insertPessoa(nome: String): Pessoa
    fun insertNoticia(titulo: String, html: String, criadoPorPessoa: Int): Noticia
    fun insertPagina(linkPagina: String, titulo: String, html: String, criadoPorPessoa: Int): Pagina
    fun insertLink(nome: String, href: String): Link

    fun updateUsuario(usuario: Usuario)
    fun updatePessoa(pessoa: Pessoa)
    fun updateNoticia(noticia: Noticia)
    fun updatePagina(pagina: Pagina)
    fun updateLink(link: Link)

    fun removeUsuario(username: String)
    fun removePessoa(id: Int)
    fun removeNoticia(id: Int)
    fun removePagina(link: String)
    fun removeLink(id: Int)

    fun swapLinks(links: Pair<Link, Link>)
    fun paginateNoticias(pagina: Int): List<Noticia>

    fun getAvaliador(id: Int): Avaliador?
    fun getAvaliadorbyUsername(user: String): Avaliador?
    fun getCurso(id: Int?): Curso?
    fun getParticipante(id: Int): Participante?
    fun getParticipantebyEmail(email: String): Participante?
    fun getParticipantesbyCurso(curso: Int): List<Participante>
    fun getInterruptor(): Int
    fun getResultado(): Int

    fun countParticipante(): Int
    fun countParticipantebyCurso(curso: Int): Int

    fun allAvaliador(): List<Avaliador>
    fun allCurso(): List<Curso>
    fun allParticipante(): List<Participante>

    fun insertAvaliador(username: String, password: String, nome: String): Avaliador

    fun insertCurso(nome: String, categoria: String, horario: String,
                    pergunta1: String, pergunta2: String, pergunta3: String, pergunta4: String, pergunta5: String, pergunta6: String,
                    alternativa11: String, alternativa12: String, alternativa13: String, alternativa14: String, alternativa15: String,
                    alternativa21: String, alternativa22: String, alternativa23: String, alternativa24: String, alternativa25: String,
                    alternativa31: String, alternativa32: String, alternativa33: String, alternativa34: String, alternativa35: String,
                    alternativa41: String, alternativa42: String, alternativa43: String, alternativa44: String, alternativa45: String,
                    alternativa51: String, alternativa52: String, alternativa53: String, alternativa54: String, alternativa55: String,
                    alternativa61: String, alternativa62: String, alternativa63: String, alternativa64: String, alternativa65: String): Curso

    fun insertCurso(respostas: Map<String, List<String>>): Curso

    fun insertParticipante(categoria: Int, nome: String, data_nascimento: LocalDate, telefone: String, email: String, password: String, tipo_sem_vinculo: Int, vinculo_ufscar: Int, escola: String, edital: Int, onde_conheceu: Int, esteve_ufscar: Int, local_aula: Int, disponibilidade: String, objetivo: Int, cursou_share: Int, desistencia: Int, redacao_entrada: String,
                           curso1_id: Int, data_inscricao_c1: LocalDate, resposta1_c1: Int, resposta2_c1: Int, resposta3_c1: Int, resposta4_c1: Int, resposta5_c1: Int, resposta6_c1: Int, avaliador_id_c1: Int, resultado_c1: Int,
                           curso2_id: Int, data_inscricao_c2: LocalDate, resposta1_c2: Int, resposta2_c2: Int, resposta3_c2: Int, resposta4_c2: Int, resposta5_c2: Int, resposta6_c2: Int, avaliador_id_c2: Int, resultado_c2: Int): Participante
    fun insertParticipante(respostas: Map<String, List<String>>): Participante
    fun insertInterruptor()
    fun insertResultado()

    fun updateAvaliacaoParticipante(participante: Participante, c1Id: Int?, c2Id: Int?)

    fun updateCurso1inParticipante(participante: Participante, id: Int?)
    fun updateCurso2inParticipante(participante: Participante, id: Int?)

    fun updateProva1inParticipante(participante: Participante)
    fun updateProva2inParticipante(participante: Participante)

    fun updateResultado1(idParticipante: Int, valor: Int)
    fun updateResultado2(idParticipante: Int, valor: Int)

    fun updateAvaliador(avaliador: Avaliador)
    fun updateCurso(curso: Curso)
    fun updateParticipante(participante: Participante)
    fun updateInterruptor(valor: Int)
    fun updateResultadoAvaliacao(valor: Int)

    fun removeAvaliador(id: Int)
    fun removeCurso(id: Int)
    fun removeParticipante(id: Int)

    companion object {
        fun hashPassword(password: String): String {
            return Base64.getEncoder().encodeToString(
                MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
            )
        }
    }
}