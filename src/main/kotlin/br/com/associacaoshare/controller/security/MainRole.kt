package br.com.associacaoshare.controller.security

import io.javalin.core.security.Role

enum class MainRole : Role {
    ANYONE, ADMIN, SUPERADMIN
}