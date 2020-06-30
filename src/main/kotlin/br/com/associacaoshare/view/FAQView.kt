package br.com.associacaoshare.view

import br.com.associacaoshare.model.Noticia
import br.com.associacaoshare.model.page.IndexViewModel
import br.com.associacaoshare.utils.compressSpaces
import br.com.associacaoshare.utils.limit
import br.com.associacaoshare.view.base.PagIniView
import br.com.associacaoshare.view.base.PagIniView.Type.INDEX
import io.javalin.http.Context
import kotlinx.html.*
import org.jsoup.Jsoup
import java.time.format.DateTimeFormatter

class FAQView(override val model: IndexViewModel) : PagIniView(INDEX) {
    override val pageTitle = "FAQ"

    override fun MAIN.renderMain(ctx: Context) {
        link("/css/pagini_cursos.css", "stylesheet")

        h1(classes="titulo_faq") { +"""F.A.Q.""" }
        h2{ b{+"FREQUENTLY ASKED QUESTIONS"}}
        h3{+"O F.A.Q. é uma compilação de perguntas frequentes,"}
        h3(classes="texto_faq_titulo"){+"esclarecendo possíveis dúvidas que possam surgir."}
        div(classes = "row") {
            div(classes = "col s12 m10 offset-m1 l10 offset-l1") {
                ul(classes = "collapsible") {
                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Como eu faço para ser aluno?"""
                        }
                        div(classes = "collapsible-body") {
                            span {
                                +"O processo é feito a partir de um formulário online através da nossa página do facebook."
                            }
                        }
                    }

                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Como eu faço para ser professor?"""
                        }
                        div(classes = "collapsible-body") {
                            span {
                                +"O processo seletivo é feito a partir de um formulário online através da nossa página do facebook, seguido por uma entrevista a ser agendada."

                            }
                        }
                    }

                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Como eu faço para ser um membro administrativo?"""
                        }
                        div(classes = "collapsible-body") {
                            span { +"""O processo seletivo é feito a partir de um formulário online através da nossa página do facebook, seguido por uma entrevista a ser agendada.""" }
                        }
                    }

                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Quando ocorrem os processos seletivos?"""
                        }
                        div(classes = "collapsible-body") {
                            span { +"""No primeiro semestre, ocorre geralmente no mês de março. No segundo semestre, ocorre geralmente no mês de agosto.Os processos ocorrem na seguinte ordem: 1.Membros administrativos; 2.Professores; 3.Alunos""" }
                        }
                    }

                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Para quantos cursos posso me inscrever?"""
                        }
                        div(classes = "collapsible-body") {
                            span { +"""Você pode se inscrever em até dois cursos. As pessoas que se inscreverem em mais do que dois cursos, serão excluídas da seleção.""" }
                        }
                    }

                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Preciso comparecer à aula magna/confirmação de matrícula?"""
                        }
                        div(classes = "collapsible-body") {
                            span { +"""Sim, o comparecimento é obrigatório. A ausência no evento, sem justificativa enviada para o e-mail share.projeto.ufscar@gmail.com, pode acarretar na perda de sua vaga.""" }
                        }
                    }

                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Posso enviar outra pessoa no meu lugar, para a confirmação de matrícula?"""
                        }
                        div(classes = "collapsible-body") {
                            span { +"""Não, a matrícula não pode ser realizada por terceiros. Caso você, não posso comparecer em nenhum dos dias da confirmação de matrícula, entre em contato por e-mail.""" }
                        }
                    }

                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""O pagamento da taxa de matrícula é obrigatório?"""
                        }
                        div(classes = "collapsible-body") {
                            span { +"""Somente em cursos presenciais, exceto no caso de o aprovado apresente-se em situação de carência financeira, neste caso, pedimos que entre em contato conosco.""" }
                        }
                    }

                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Qual o valor da taxa?"""
                        }
                        div(classes = "collapsible-body") {
                            span { +"""A taxa de matrícula tem valor simbólico de R${'$'}5,00. Esse valor é revertido para melhorias nas aulas e na entidade com um todo.""" }
                        }
                    }

                    li {
                        div(classes = "collapsible-header") {
                            i(classes = "material-icons") { +"""expand_more""" }
                            +"""Quando receberei meu certificado de conclusão?"""
                        }
                        div(classes = "collapsible-body ultimo") {
                            span { +"""Os certificados são enviados após o final do curso por e-mail. Geralmente, levam cerca de 2 meses para serem confeccionados e enviados a todos os concluintes. Este certificado é enviado apenas para aqueles que foram aprovados no(s) seu(s) curso(s).""" }
                        }
                    }

                }
            }
        }

        script("text/javascript", "/js/m_collapsible.js") {}
    }

}