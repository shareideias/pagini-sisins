package br.com.associacaoshare.controller

import br.com.associacaoshare.controller.SisinsAccessManager.Roles.*
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.model.dao.DataAccessObject
import br.com.associacaoshare.model.exception.FalhaSessaoException
import br.com.associacaoshare.view.alunos.*
import io.javalin.apibuilder.EndpointGroup
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.core.security.SecurityUtil.roles
import io.javalin.http.Context
import org.kodein.di.generic.instance
import java.net.URLDecoder.decode
import java.time.OffsetDateTime
import kotlin.text.Charsets.UTF_8

class AlunosController (override val kodein: Kodein) : EndpointGroup, KodeinAware {
    val dao: DataAccessObject by instance()

    override fun addEndpoints() {
        get(::inscricoes, roles(PARTICIPANTE))

        get("editar", ::edicao, roles(PARTICIPANTE))
        post("EditaProc", ::edicaoProc, roles(PARTICIPANTE))

        get("curso1", ::listacurso1, roles(PARTICIPANTE))
        post("CadastraCurso1", ::cadastraCurso1, roles(PARTICIPANTE))
        post("DeleteCurso1", ::DeleteCurso1, roles(PARTICIPANTE))

        get("prova", ::prova, roles(PARTICIPANTE))
        post("ProvaProc", ::provaProc, roles(PARTICIPANTE))

        get("curso2", ::listacurso2, roles(PARTICIPANTE))
        post("CadastraCurso2", ::cadastraCurso2, roles(PARTICIPANTE))
        post("DeleteCurso2", ::DeleteCurso2, roles(PARTICIPANTE))

        get("prova2", ::prova2, roles(PARTICIPANTE))
        post("ProvaProc2", ::provaProc2, roles(PARTICIPANTE))
    }

    private fun inscricoes (ctx: Context) {
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        val curso1 = ctx.sessionAttribute<Int?>("ID")?.let { dao.getCurso(participante!!.curso1_id) }
        val curso2 = ctx.sessionAttribute<Int?>("ID")?.let { dao.getCurso(participante!!.curso2_id) }
        val errormsg = ctx.cookie("errorMsg")?.let{decode(it , UTF_8)}
        var interruptor = dao.getInterruptor()
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        if(participante != null) {
            InscricoesAlunoView(errormsg, participante, curso1, curso2, interruptor).render(ctx)
        } else {
            throw FalhaSessaoException()
        }
    }

    private fun edicao (ctx: Context) {
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        val errormsg = ctx.cookie("errorMsg")?.let{decode(it , UTF_8)}
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        if(participante != null) {
            EdicaoView(errormsg, participante).render(ctx)
        } else {
            throw FalhaSessaoException()
        }
    }

    private fun edicaoProc (ctx: Context) {
        val resp = ctx.formParamMap()
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        if(participante != null) {
            participante.atualizaDados(resp)
            dao.updateParticipante(participante)
        }
        ctx.redirect("/inscricoes/alunos")
    }

    private fun listacurso1 (ctx: Context) {
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        val errormsg = ctx.cookie("errorMsg")?.let{decode(it , UTF_8)}
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        var interruptor = dao.getInterruptor()
        if(participante != null) {
            Lista1View(errormsg, participante, dao.allCurso(), interruptor).render(ctx)
        } else {
            throw FalhaSessaoException()
        }
    }

    private fun cadastraCurso1 (ctx: Context) {
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        val id = ctx.formParam("id") ?: ""
        val categoria = ctx.formParam("categoria") ?:""

        if (participante != null) {
            dao.updateCurso1inParticipante(participante, id.toInt())
            preselecao1(participante)
        }

        if (categoria == "1"){
            ctx.redirect("/inscricoes/alunos/prova?id=${id}")
        }else{
            ctx.redirect("/inscricoes/alunos")
        }
    }

    private fun DeleteCurso1 (ctx: Context) {
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        if (participante != null) {
            dao.updateCurso1inParticipante(participante, null)
        }
        ctx.redirect("/inscricoes/alunos")
    }

    private fun prova (ctx: Context) {
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        val errormsg = ctx.cookie("errorMsg")?.let{decode(it , UTF_8)}
        val curso = ctx.queryParam("id")?.toInt().let { dao.getCurso(it) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        if(participante != null && curso != null) {
            ProvaView(errormsg, participante, curso).render(ctx)
        } else {
            throw FalhaSessaoException()
        }
    }

    private fun provaProc (ctx: Context) {
        val resp = ctx.formParamMap()
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        if(participante != null) {
            participante.atualizaProva(resp)
            dao.updateProva1inParticipante(participante)
        }
        ctx.redirect("/inscricoes/alunos")
    }

    private fun listacurso2 (ctx: Context) {
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        val errormsg = ctx.cookie("errorMsg")?.let{decode(it , UTF_8)}
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        var interruptor = dao.getInterruptor()
        if(participante != null) {
            Lista2View(errormsg, participante, dao.allCurso(), interruptor).render(ctx)
        } else {
            throw FalhaSessaoException()
        }
    }

    private fun cadastraCurso2 (ctx: Context) {
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        val id = ctx.formParam("id") ?: ""
        val categoria = ctx.formParam("categoria") ?:""

        if (participante != null) {
            dao.updateCurso2inParticipante(participante, id.toInt())
            preselecao2(participante)
        }
        if (categoria == "1"){
            ctx.redirect("/inscricoes/alunos/prova2?id=${id}")
        }else{
            ctx.redirect("/inscricoes/alunos")
        }
    }

    private fun DeleteCurso2 (ctx: Context) {
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        if (participante != null) {
            dao.updateCurso2inParticipante(participante, null)
        }
        ctx.redirect("/inscricoes/alunos")
    }

    private fun prova2 (ctx: Context) {
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        val errormsg = ctx.cookie("errorMsg")?.let{decode(it , UTF_8)}
        val curso = ctx.queryParam("id")?.toInt().let { dao.getCurso(it) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        if(participante != null && curso != null) {
            Prova2View(errormsg, participante, curso).render(ctx)
        } else {
            throw FalhaSessaoException()
        }
    }

    private fun provaProc2 (ctx: Context) {
        val resp = ctx.formParamMap()
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        if(participante != null) {
            participante.atualizaProva2(resp)
            dao.updateProva2inParticipante(participante)
        }
        ctx.redirect("/inscricoes/alunos")
    }

    private fun preselecao1(participante: Participante){
        if(!maior16(participante)){
            participante.id.let { dao.updateResultado1(it, 4) }
        }
        else if(participante.desistencia == 2){
            participante.id.let { dao.updateResultado1(it, 4) }
        }
        else if(participante.edital == 2){
            participante.id.let { dao.updateResultado1(it, 4) }
        }
        else if(participante.tipo_sem_vinculo == 4){
            participante.id.let { dao.updateResultado1(it, 2) }
        }
        else if(participante.local_aulas == 2){
            participante.id.let { dao.updateResultado1(it, 2) }
        }
        else{
            participante.id.let { dao.updateResultado1(it, -1) }
        }
    }

    private fun preselecao2(participante: Participante){
        if(!maior16(participante)){
            participante.id.let { dao.updateResultado2(it, 4) }
        }
        else if(participante.desistencia == 2){
            participante.id.let { dao.updateResultado2(it, 4) }
        }
        else if(participante.edital == 2){
            participante.id.let { dao.updateResultado2(it, 4) }
        }
        else if(participante.tipo_sem_vinculo == 4){
            participante.id.let { dao.updateResultado2(it, 2) }
        }
        else if(participante.local_aulas == 2){
            participante.id.let { dao.updateResultado2(it, 2) }
        }
        else{
            participante.id.let { dao.updateResultado2(it, -1) }
        }
    }

    private fun maior16(participante: Participante): Boolean{
        if((OffsetDateTime.now().year - participante.data_nascimento.year) > 16){
            return true
        } else if((OffsetDateTime.now().year - participante.data_nascimento.year) < 16){
            return false
        } else if((OffsetDateTime.now().year - participante.data_nascimento.year) == 16){
            if((OffsetDateTime.now().dayOfYear - participante.data_nascimento.dayOfYear) > 0 ){
                return true
            }
        }
        return false
    }
}