package br.com.associacaoshare.controller

import br.com.associacaoshare.view.adm.*
import br.com.associacaoshare.controller.security.ShareAccessManager.Roles.*
import br.com.associacaoshare.model.Curso
import br.com.associacaoshare.model.dao.DataAccessObject
import br.com.associacaoshare.view.adm.abasDasInscricoes.*
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.EndpointGroup
import io.javalin.core.security.SecurityUtil.roles
import io.javalin.http.Context
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.net.URLDecoder

class AdminSisinsController(override val kodein: Kodein) : EndpointGroup, KodeinAware {
    val dao: DataAccessObject by instance()
    val utf8 = Charsets.UTF_8.toString()

    override fun addEndpoints() {
        get(::cursos, roles(AVALIADOR))
        get("inscricoes", ::inscricoes, roles(AVALIADOR))
        get("naoAvaliados", ::naoAvaliados, roles(AVALIADOR))
        get("aprovados", ::aprovados, roles(AVALIADOR))
        get("aprovados2", ::aprovados2, roles(AVALIADOR))
        get("espera", ::espera, roles(AVALIADOR))
        get("reprovados", ::reprovados, roles(AVALIADOR))
        get("desistencias", ::desistencias, roles(AVALIADOR))

        get("inscricoesgerais", ::inscricoesgerais, roles(AVALIADOR))
        get("influencers", ::influencers, roles(AVALIADOR))

        get("candidato", ::candidato, roles(AVALIADOR))
        post("CandidatoProc", ::candidatoProc, roles(AVALIADOR))

        get("perfildocandidato", ::perfildocandidato, roles(AVALIADOR))
        post("EdicaoSenha", ::EdicaoSenha, roles(AVALIADOR))

        get("addCurso", ::addCurso, roles(AVALIADOR))
        post("adicionaCurso", ::adicionaCurso, roles(AVALIADOR))
        get("addProva", ::addProva, roles(AVALIADOR))
        post("adicionaProva", ::adicionaProva, roles(AVALIADOR))

        get("excluircurso", ::excluircurso, roles(AVALIADOR))
        get("cursoexcluido", ::cursoexcluido, roles(AVALIADOR))

        get("editarcurso", ::editarcurso, roles(AVALIADOR))
        post("cursoeditado", ::cursoeditado, roles(AVALIADOR))
        get("editarprova", ::editarprova, roles(AVALIADOR))
        post("provaeditada", ::provaeditada, roles(AVALIADOR))

        get("abreinscricoes", ::abreinscricoes, roles(AVALIADOR))
        get("fechainscricoes", ::fechainscricoes, roles(AVALIADOR))

        get("exibirresultados", ::exibirresultados, roles(AVALIADOR))
        get("ocultarresultados", ::ocultarresultados, roles(AVALIADOR))

        get("aprova", ::aprova, roles(AVALIADOR))
        get("aprova2", ::aprova2, roles(AVALIADOR))
        get("listadeespera", ::listadeespera, roles(AVALIADOR))
        get("desistencia", ::desistencia, roles(AVALIADOR))
        get("reprova", ::reprova, roles(AVALIADOR))

        get("deleteuser", ::deleteuser, roles(AVALIADOR))
    }

    private fun cursos(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        var interruptor = dao.getInterruptor()
        var resultados = dao.getResultado()
        CursosView(errormsg, dao.allCurso(), interruptor, resultados).render(ctx)
    }

    // INICIO dos resultados das inscrições
    private fun inscricoes(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}
        if (curso == null) {
            ctx.redirect("/inscricoes/adm")
            return
        }
        val inscritos = dao.getParticipantesbyCurso(curso.id)
        var qtdParticipantes = dao.countParticipantebyCurso(curso.id)
        InscricoesView(errormsg, curso, inscritos, qtdParticipantes).render(ctx)
    }

    private fun naoAvaliados(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}
        if (curso == null) {
            ctx.redirect("/inscricoes/adm")
            return
        }
        val inscritos = dao.getParticipantesbyCurso(curso.id)
        var qtdParticipantes = dao.countParticipantebyCurso(curso.id)
        NaoAvaliadosView(errormsg, curso, inscritos, qtdParticipantes).render(ctx)
    }

    private fun aprovados(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}
        if (curso == null) {
            ctx.redirect("/inscricoes/adm")
            return
        }
        val inscritos = dao.getParticipantesbyCurso(curso.id)
        var qtdParticipantes = dao.countParticipantebyCurso(curso.id)
        AprovadosView(errormsg, curso, inscritos, qtdParticipantes).render(ctx)
    }

    private fun aprovados2(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}
        if (curso == null) {
            ctx.redirect("/inscricoes/adm")
            return
        }
        val inscritos = dao.getParticipantesbyCurso(curso.id)
        var qtdParticipantes = dao.countParticipantebyCurso(curso.id)
        Aprovados2View(errormsg, curso, inscritos, qtdParticipantes).render(ctx)
    }

    private fun espera(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}
        if (curso == null) {
            ctx.redirect("/inscricoes/adm")
            return
        }
        val inscritos = dao.getParticipantesbyCurso(curso.id)
        var qtdParticipantes = dao.countParticipantebyCurso(curso.id)
        EsperaView(errormsg, curso, inscritos, qtdParticipantes).render(ctx)
    }

    private fun reprovados(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}
        if (curso == null) {
            ctx.redirect("/inscricoes/adm")
            return
        }
        val inscritos = dao.getParticipantesbyCurso(curso.id)
        var qtdParticipantes = dao.countParticipantebyCurso(curso.id)
        ReprovadosView(errormsg, curso, inscritos, qtdParticipantes).render(ctx)
    }

    private fun desistencias(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}
        if (curso == null) {
            ctx.redirect("/inscricoes/adm")
            return
        }
        val inscritos = dao.getParticipantesbyCurso(curso.id)
        var qtdParticipantes = dao.countParticipantebyCurso(curso.id)
        DesistenciasView(errormsg, curso, inscritos, qtdParticipantes).render(ctx)
    }
    // FIM dos resultados das inscrições

    private fun inscricoesgerais(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val inscritos = dao.allParticipante()
        val qtdParticipantes = dao.countParticipante()
        InscricoesGeraisView(errormsg, inscritos, qtdParticipantes).render(ctx)
    }

    private fun influencers(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val inscritos = dao.countInfluencer()
        val qtdParticipantes = dao.countParticipante()
        InfluencerView(errormsg, inscritos, qtdParticipantes).render(ctx)
    }

    private fun candidato(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val candidato = ctx.queryParam("id")?.toInt()?.let{dao.getParticipante(it)}

        val curso = ctx.queryParam("idC")?.toInt()?.let{dao.getCurso(it)}
        val curso1 = ctx.sessionAttribute<Int?>("ID")?.let { dao.getCurso(candidato!!.curso1_id) }
        val curso2 = ctx.sessionAttribute<Int?>("ID")?.let { dao.getCurso(candidato!!.curso2_id) }
        if (curso != null && candidato != null) {
            CandidatoView(errormsg, candidato, curso, curso1, curso2).render(ctx)
        }
    }

    private fun candidatoProc(ctx: Context){
        val r1 = ctx.formParam("resultado_c1")
        val r2 = ctx.formParam("resultado_c2")
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(it) }
        if(participante != null) {
            dao.updateAvaliacaoParticipante(participante, r1?.toInt(), r2?.toInt())
        }
        ctx.redirect("/inscricoes/adm")
    }

    private fun perfildocandidato(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val participante = ctx.queryParam("id")?.toInt()?.let{dao.getParticipante(it)}
        val curso1 = ctx.sessionAttribute<Int?>("ID")?.let { dao.getCurso(participante!!.curso1_id) }
        val curso2 = ctx.sessionAttribute<Int?>("ID")?.let { dao.getCurso(participante!!.curso2_id) }
        if (participante == null) {
            ctx.redirect("/inscricoes/adm")
            return
        }
        PerfilCandidatoView(errormsg, participante, curso1, curso2).render(ctx)
    }



    private fun EdicaoSenha (ctx: Context) {
        val resp = ctx.formParamMap()
        val id = ctx.formParam("id") ?: ""
        val participante = ctx.sessionAttribute<Int?>("ID")?.let { dao.getParticipante(id.toInt()) }

        if (participante != null) {
            participante.atualizaSenha(resp)
            dao.updateParticipante(participante)
        }

        ctx.redirect("inscricoesgerais")
    }

    private fun addCurso(ctx: Context) {
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        CadastroCursoView(errormsg).render(ctx)
    }

    private fun adicionaCurso(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val resp = ctx.formParamMap()
        val novoCurso: Curso = dao.insertCurso(resp)

        if(novoCurso.categoria == "1")
            ctx.redirect("addProva?id=${novoCurso.id}")
        else
            ctx.redirect("/inscricoes/adm")
    }

    private fun addProva(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}

        CadastrarProvaView(errormsg, curso).render(ctx)
    }

    private fun adicionaProva(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}
        val resp = ctx.formParamMap()

        if (curso != null) {
            curso.atualizaProva(resp)
            dao.updateCurso(curso)
        }

        ctx.redirect("/inscricoes/adm")

    }

    private fun excluircurso(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}
        if (curso != null) {
            ExcluirCursoView(errormsg, curso).render(ctx)
        }
    }

    private fun cursoexcluido(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}

        if (curso != null) {
            dao.removeCurso(curso.id)
        }

        ctx.redirect("/inscricoes/adm")
    }

    private fun editarcurso(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}
        if (curso != null) {
            EdicaoCursoView(errormsg, curso).render(ctx)
        }
    }

    private fun cursoeditado(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val resp = ctx.formParamMap()
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}

        if (curso != null) {
            curso.atualizaDados(resp)
            dao.updateCurso(curso)
        }

        if (curso != null) {
            if(curso.categoria == "1"){
                ctx.redirect("editarprova?id=${curso?.id}")
            } else if(curso.categoria == "2"){
                ctx.redirect("/inscricoes/adm")
            }
        }
    }

    private fun editarprova(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}
        if (curso != null) {
            EdicaoProvaView(errormsg, curso).render(ctx)
        }
    }

    private fun provaeditada(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val resp = ctx.formParamMap()
        val curso = ctx.queryParam("id")?.toInt()?.let{dao.getCurso(it)}

        if (curso != null) {
            curso.atualizaProva(resp)
            dao.updateCurso(curso)
        }

        ctx.redirect("/inscricoes/adm")
    }

    private fun abreinscricoes(ctx: Context) {
        dao.updateInterruptor(1)
        ctx.redirect("/inscricoes/adm")
    }

    private fun fechainscricoes(ctx: Context) {
        dao.updateInterruptor(0)
        ctx.redirect("/inscricoes/adm")
    }

    private fun exibirresultados(ctx: Context) {
        dao.updateResultadoAvaliacao(1)
        ctx.redirect("/inscricoes/adm")
    }

    private fun ocultarresultados(ctx: Context) {
        dao.updateResultadoAvaliacao(0)
        ctx.redirect("/inscricoes/adm")
    }

    // lista de aprovado primeira chamada
    private fun aprova(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val participante = ctx.queryParam("id")?.toInt()?.let{dao.getParticipante(it)}
        val curso = ctx.queryParam("idC")?.toInt()?.let{dao.getCurso(it)}

        if (participante != null && curso != null) {
            if(participante.curso1_id == curso.id)
                participante.id.let { dao.updateResultado1(it, 1) }

            if(participante.curso2_id == curso.id)
                participante.id.let { dao.updateResultado2(it, 1) }

            ctx.redirect("aprovados?id=${curso.id}")
        }
    }

    // lista de aprovado segunda chamada
    private fun aprova2(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val participante = ctx.queryParam("id")?.toInt()?.let{dao.getParticipante(it)}
        val curso = ctx.queryParam("idC")?.toInt()?.let{dao.getCurso(it)}

        if (participante != null && curso != null) {
            if(participante.curso1_id == curso.id)
                participante.id.let { dao.updateResultado1(it, 5) }

            if(participante.curso2_id == curso.id)
                participante.id.let { dao.updateResultado2(it, 5) }

            ctx.redirect("aprovados2?id=${curso.id}")
        }
    }

    private fun listadeespera(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val participante = ctx.queryParam("id")?.toInt()?.let{dao.getParticipante(it)}
        val curso = ctx.queryParam("idC")?.toInt()?.let{dao.getCurso(it)}

        if (participante != null && curso != null) {
            if(participante.curso1_id == curso.id)
                participante.id.let { dao.updateResultado1(it, 2) }

            if(participante.curso2_id == curso.id)
                participante.id.let { dao.updateResultado2(it, 2) }

            ctx.redirect("espera?id=${curso.id}")
        }
    }

    private fun desistencia(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val participante = ctx.queryParam("id")?.toInt()?.let{dao.getParticipante(it)}
        val curso = ctx.queryParam("idC")?.toInt()?.let{dao.getCurso(it)}

        if (participante != null && curso != null) {
            if(participante.curso1_id == curso.id)
                participante.id.let { dao.updateResultado1(it, 3) }

            if(participante.curso2_id == curso.id)
                participante.id.let { dao.updateResultado2(it, 3) }

            ctx.redirect("desistencias?id=${curso.id}")
        }
    }

    private fun reprova(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val participante = ctx.queryParam("id")?.toInt()?.let{dao.getParticipante(it)}
        val curso = ctx.queryParam("idC")?.toInt()?.let{dao.getCurso(it)}

        if (participante != null && curso != null) {
            if(participante.curso1_id == curso.id)
                participante.id.let { dao.updateResultado1(it, 4) }

            if(participante.curso2_id == curso.id)
                participante.id.let { dao.updateResultado2(it, 4) }

            ctx.redirect("reprovados?id=${curso.id}")
        }
    }


    private fun deleteuser(ctx: Context){
        val errormsg = ctx.cookie("errorMsg")?.let{ URLDecoder.decode(it, utf8) }
        if (errormsg != null)
            ctx.cookie("errorMsg", "", 0)

        val participante = ctx.queryParam("id")?.toInt()?.let{dao.getParticipante(it)}

        if (participante != null) {
            dao.removeParticipante(participante.id)
        }

        ctx.redirect("/inscricoes/adm/inscricoesgerais")
    }
}