package br.com.associacaoshare.controller

import br.com.associacaoshare.controller.security.ForbiddenAccessException
import br.com.associacaoshare.controller.security.NotFoundException
import br.com.associacaoshare.controller.security.UnableToEditException
import br.com.associacaoshare.model.dao.DataAccessObject
import br.com.associacaoshare.model.exception.CamposVaziosException
import br.com.associacaoshare.model.exception.FalhaSessaoException
import br.com.associacaoshare.model.exception.UsuarioNaoEncontrado
import br.com.associacaoshare.model.page.ErrorViewModel
import br.com.associacaoshare.view.ErrorView
import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.net.URLEncoder

class ErrorHandler(override val kodein: Kodein) : KodeinAware {
    val dao: DataAccessObject by instance()
    fun Javalin.addErrorHandlers() {
        exception(NotFoundException::class.java) { e, ctx ->
            ErrorView(ErrorViewModel(
                e.isAdmin,
                HttpStatus.NOT_FOUND_404,
                "Não Encontrado",
                "Você está tentando acessar ${e.type.what} que não existe mais."
            )).render(ctx)
        }

        exception(UnableToEditException::class.java) { e, ctx ->
            ErrorView(ErrorViewModel(
                e.isAdmin,
                HttpStatus.PRECONDITION_FAILED_412,
                "Falha na Edição",
                "Você tentou editar ${e.type.what} que não existe. Ao menos, não mais."
            )).render(ctx)
        }

        exception(ForbiddenAccessException::class.java) { _, ctx ->
            ErrorView(ErrorViewModel(
                false,
                HttpStatus.FORBIDDEN_403,
                "Acesso Negado",
                "Você está tentando acessar uma página que você não pode acessar."
            )).render(ctx)
        }

        error(HttpStatus.FORBIDDEN_403) { ctx ->
            ErrorView(ErrorViewModel(
                false,
                HttpStatus.FORBIDDEN_403,
                "Acesso Negado",
                "Você está tentando acessar uma página que você não pode acessar."
            )).render(ctx)
        }
        error(HttpStatus.NOT_FOUND_404) { ctx ->
            ErrorView(ErrorViewModel(
                false,
                HttpStatus.NOT_FOUND_404,
                "Não Encontrado",
                "Você está tentando acessar algo que não existe mais."
            )).render(ctx)
        }
        error(HttpStatus.INTERNAL_SERVER_ERROR_500) { ctx ->
            ErrorView(ErrorViewModel(
                false,
                HttpStatus.INTERNAL_SERVER_ERROR_500,
                "Erro Interno no Servidor",
                "O servidor sofreu uma falha enquanto processava a página. Pedimos desculpas."
            )).render(ctx)
        }

        exception(org.jdbi.v3.core.statement.UnableToExecuteStatementException::class.java) {
            //Deu erro na execução de alguma query no BD
            e, ctx ->
            val s = (e.cause?.message)
            val messagemap = s?.split("\n")
                    ?.associate { it.split(": ")
                            .let { (first, last) -> first to last} }
            val message = messagemap?.get("ERROR") ?: "Um erro desconhecido ocorreu."
            ctx.cookie("errorMsg", URLEncoder.encode(message, Charsets.UTF_8))
            ctx.redirect(ctx.header("Referer") ?: "/inscricoes")
        }

        exception(java.util.NoSuchElementException::class.java) {
            //Faltou um campo obrigatório
            e, ctx ->
            val message = (e.message) ?: "Um erro desconhecido ocorreu."
            ctx.cookie("errorMsg", URLEncoder.encode(message, Charsets.UTF_8))
            ctx.redirect(ctx.header("Referer") ?: "/inscricoes")
        }

        exception(java.time.format.DateTimeParseException::class.java) {
            //Erro no parsing, pode não ter sido preenchido
            e, ctx ->
            val message = (e.message) ?: "Um erro desconhecido ocorreu."
            ctx.cookie("errorMsg", URLEncoder.encode(message, Charsets.UTF_8))
            ctx.redirect(ctx.header("Referer") ?: "/inscricoes")
        }

        exception(FalhaSessaoException::class.java) {
            //Exception lançada pelo sisins para o usuário refazer a sessão no login
            e, ctx ->
            val message = (e.message) ?: "Um erro desconhecido ocorreu."
            ctx.cookie("errorMsg", URLEncoder.encode(message, Charsets.UTF_8))
            ctx.redirect("/inscricoes/login")
        }

        exception(CamposVaziosException::class.java) {
            //Exception lançada pelo sisins quando um campo obrigatório não foi preenchido
            e, ctx ->
            val message = (e.message) ?: "Um erro desconhecido ocorreu."
            ctx.cookie("errorMsg", URLEncoder.encode(message, Charsets.UTF_8))
            ctx.redirect(ctx.header("Referer") ?: "/inscricoes")
        }

        exception(UsuarioNaoEncontrado::class.java) {
            e, ctx ->
            val message = (e.message) ?: "Um erro desconhecido ocorreu."
            ctx.cookie("errorMsg", URLEncoder.encode(message, Charsets.UTF_8))
            ctx.redirect("/inscricoes/login")
        }

        exception(Exception::class.java) {
            //Exception desconhecida
            e, ctx ->
            val message = (e.message) ?: "Um erro desconhecido ocorreu."
            println(message)
            println(e.stackTrace.toString())
            ctx.cookie("errorMsg", URLEncoder.encode(message, Charsets.UTF_8))
            ctx.redirect("/inscricoes")
        }
    }
}