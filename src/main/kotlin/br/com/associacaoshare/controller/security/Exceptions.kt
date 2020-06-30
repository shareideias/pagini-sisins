package br.com.associacaoshare.controller.security

/**
 * Throw this to show a pretty 404 NOT_FOUND_404
 */
class NotFoundException(val isAdmin: Boolean, val type: ContentType) : Exception()

/**
 * Throw this to show a pretty PRECONDITION_FAILED_412
 */
class UnableToEditException(val isAdmin: Boolean, val type: ContentType) : Exception()

/**
 * Throw this to show a pretty FORBIDDEN_403
 */
class ForbiddenAccessException : Exception()

enum class ContentType(val what: String) {
    PAGINA("uma página"), NOTICIA("uma notícia"),
    USUARIO("um usuário"), PESSOA("uma pessoa"), OUTRO("algo")
}
