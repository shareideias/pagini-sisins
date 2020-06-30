import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    application
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "br.com.associacaoshare"
version = "1.1"

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("io.javalin:javalin:3.5.0")
    compile("ch.qos.logback:logback-classic:1.2.3")
    compile("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.12")
    compile("org.kodein.di:kodein-di-generic-jvm:6.4.0")
    compile("com.auth0:java-jwt:3.8.3")
    compile("org.jdbi:jdbi3-core:3.10.1")
    compile("org.jdbi:jdbi3-kotlin:3.10.1")
    compile("org.jdbi:jdbi3-postgres:3.10.1")
    compile("org.jdbi:jdbi3-json:3.10.1")
    compile("org.jdbi:jdbi3-jackson2:3.10.1")
    compile("com.fasterxml.jackson.core:jackson-databind:2.10.0")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.0")
    compile("org.jsoup:jsoup:1.12.1")
    compile("org.postgresql:postgresql:42.2.8")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "br.com.associacaoshare.PagIniKt"
}


tasks.withType<ShadowJar> {
    exclude("about.html", "jetty-dir.css", "module-info.class")
    exclude("META-INF/LICENSE", "META-INF/LICENSE.txt", "META-INF/NOTICE", "META-INF/NOTICE.txt", "META-INF/README.md", "META-INF/CHANGES", "META-INF/maven/**")
}