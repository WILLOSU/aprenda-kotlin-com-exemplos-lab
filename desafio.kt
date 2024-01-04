// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String)



data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

// Exception para lidar com erros específicos de matrícula
class MatriculaException(message: String) : Exception(message)

class Formacao(val nome: String, vararg conteudos: ConteudoEducacional){

   
    private val inscritos = mutableListOf<Usuario>()   
    private val conteudosList = conteudos.toList()
   
    init {
        
        if (nome.isBlank()) {
            throw MatriculaException("Nome da formação não pode ser vazio.")
        }
       
        conteudosList.forEach {
            if (it.nome.isBlank()) {
                throw MatriculaException("Nome do conteúdo não pode ser vazio.")
            }
        }
    }

    fun matricular(vararg usuarios: Usuario) {
        // Itera sobre a lista de usuários a serem matriculados
        usuarios.forEach { usuario ->
           
            if (inscritos.contains(usuario)) {
               
                println("${usuario.nome} já está matriculado nesta formação.")
            } else {
              
                inscritos.add(usuario)
                println("${usuario.nome} matriculado com sucesso na formação $nome.")
            }
        }
    }

    fun obterConteudos(): List<ConteudoEducacional> = conteudosList
    
    fun obterAlunosMatriculados(): List<Usuario> = inscritos.toList()
}



fun main() {
    try {
        val conteudo1 = ConteudoEducacional("Introdução ao Kotlin")
        val conteudo2 = ConteudoEducacional("Programação Orientada a Objetos", 90)

        val formacao = Formacao("Desenvolvimento Kotlin", conteudo1, conteudo2)

        val usuario1 = Usuario("Alice")
        val usuario2 = Usuario("Bob")
        val usuario3 = Usuario("Charlie")
        val usuario4 = Usuario("Diana")
        val usuario5 = Usuario("Eva")
        val usuario6 = Usuario("Teresvaldo")

        // Tentativa de matricular os usuários
        formacao.matricular(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6)

        // Exibição da lista de alunos matriculados na formação
        val alunosMatriculados = formacao.obterAlunosMatriculados()
        println("Alunos matriculados na formação ${formacao.nome}:")
        for (aluno in alunosMatriculados) {
            println(aluno.nome)
        }

        println("Conteúdos da formação ${formacao.nome}: ${formacao.obterConteudos().map { it.nome }}")
    } catch (ex: MatriculaException) {
        println("Erro ao matricular: ${ex.message}")
    }
}