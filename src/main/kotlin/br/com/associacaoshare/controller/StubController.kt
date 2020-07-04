package br.com.associacaoshare.controller

import br.com.associacaoshare.model.Avaliador
import br.com.associacaoshare.model.Participante
import br.com.associacaoshare.model.dao.DataAccessObject
import br.com.associacaoshare.model.exception.CamposVaziosException
import br.com.associacaoshare.model.exception.UsuarioNaoEncontrado
import br.com.associacaoshare.view.alunos.*

import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.EndpointGroup
import io.javalin.http.Context
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.net.URLDecoder.decode
import kotlin.text.Charsets.UTF_8

class StubController(override val kodein: Kodein) : EndpointGroup, KodeinAware {
    val dao: DataAccessObject by instance()

    override fun addEndpoints() {
        get("inscricoes", ::indexsisins)

        get("inscricoes/cadastro", ::cadastro)
        post("inscricoes/CadastroProc", ::cadastroProc)

        get("inscricoes/login", ::login)
        post("inscricoes/dologin", ::doLogin)
        get("inscricoes/logout", ::doLogout)

        path("inscricoes/adm") {
            AdminSisinsController(kodein).addEndpoints()
        }
        path("inscricoes/alunos") {
            AlunosController(kodein).addEndpoints()
        }
    }

    private fun indexsisins (ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{decode(it , UTF_8)}
        if(errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val interruptor = dao.getInterruptor()
        IndexSisinsView(errormsg, interruptor).render(ctx)
    }

    private fun cadastro (ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{decode(it , UTF_8)}
        if(errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val interruptor = dao.getInterruptor()
        CadastroView(errormsg, interruptor).render(ctx)
    }

    private fun cadastroProc (ctx: Context) {
        val resp = ctx.formParamMap()
        val novoParticipante: Participante = dao.insertParticipante(resp)
        loginRoutine(ctx, novoParticipante)
        ctx.redirect("/inscricoes/alunos")
    }

    private fun login (ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{decode(it , UTF_8)}
        if(errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        LoginView(errormsg).render(ctx)
    }

    private fun doLogin (ctx: Context) {
        val user = ctx.formParam("user") ?: ""
        val pass = ctx.formParam("password") ?: ""

        //Provavelmente não vai ocorrer, por causa da validação do front
        if(user.isEmpty()) {
            throw CamposVaziosException("usuário")
        }
        if(pass.isEmpty()) {
            throw CamposVaziosException("senha")
        }

        dao.getParticipantebyEmail(user)?.let { p ->
            if (p.hash == DataAccessObject.hashPassword(pass)) {
                loginRoutine(ctx, p)
                return
            } else {
                throw UsuarioNaoEncontrado()
            }
        }

        dao.getAvaliadorbyUsername(user)?.let { p ->
            if (p.hash == DataAccessObject.hashPassword(pass)) {
                loginRoutine(ctx, p)
                return
            } else {
                throw UsuarioNaoEncontrado()
            }
        }

        ctx.redirect("/inscricoes/login")
    }

    private fun loginRoutine(ctx: Context, obj: Avaliador) {
        ctx.sessionAttribute("ROLE", "AVALIADOR")
        ctx.sessionAttribute("ID", obj.id)
        ctx.redirect("/inscricoes/adm")
    }

    private fun loginRoutine(ctx: Context, obj: Participante) {
        ctx.sessionAttribute("ROLE", "PARTICIPANTE")
        ctx.sessionAttribute("ID", obj.id)
        ctx.redirect("/inscricoes/alunos")
    }

    private fun doLogout(ctx: Context) {
        ctx.sessionAttribute("ROLE", null)
        ctx.redirect("/inscricoes")
    }
}