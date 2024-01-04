// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

// Enumeração para representar os níveis de formação
enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

// Classe representando um usuário com um nome
class Usuario(val nome: String)

// Classe que representa um conteúdo educacional com um nome e uma duração padrão
data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

// Exception para lidar com erros específicos de matrícula
class MatriculaException(message: String) : Exception(message)

// Classe que representa uma formação, contendo um nome, uma lista de conteúdos educacionais e alunos matriculados
class Formacao(val nome: String, vararg conteudos: ConteudoEducacional) {

    // Lista de alunos matriculados nesta formação
    private val inscritos = mutableListOf<Usuario>()

    // Lista imutável de conteúdos educacionais para garantir consistência
    private val conteudosList = conteudos.toList()

    // Inicialização da classe, onde fazemos algumas verificações e configurações iniciais
    init {
        // Verifica se o nome da formação não está vazio
        if (nome.isBlank()) {
            throw MatriculaException("Nome da formação não pode ser vazio.")
        }
        // Verifica se os nomes dos conteúdos não estão vazios
        conteudosList.forEach {
            if (it.nome.isBlank()) {
                throw MatriculaException("Nome do conteúdo não pode ser vazio.")
            }
        }
    }

    // Método para matricular um ou mais alunos
    fun matricular(vararg usuarios: Usuario) {
        // Itera sobre a lista de usuários a serem matriculados
        usuarios.forEach { usuario ->
            // Verifica se o aluno já está matriculado
            if (inscritos.contains(usuario)) {
                // Exibe uma mensagem indicando que o aluno já está matriculado
                println("${usuario.nome} já está matriculado nesta formação.")
            } else {
                // Matricula o aluno e exibe uma mensagem de sucesso
                inscritos.add(usuario)
                println("${usuario.nome} matriculado com sucesso na formação $nome.")
            }
        }
    }

    // Método para remover um aluno da formação
    fun removerAluno(usuario: Usuario) {
        if (inscritos.contains(usuario)) {
            inscritos.remove(usuario)
            println("${usuario.nome} removido da formação $nome.")
        } else {
            println("${usuario.nome} não está matriculado nesta formação.")
        }
    }

    // Método para obter a lista de conteúdos educacionais
    fun obterConteudos(): List<ConteudoEducacional> = conteudosList

    // Método para obter a lista de alunos matriculados
    fun obterAlunosMatriculados(): List<Usuario> = inscritos.toList()
}

// Função para exibir o menu
fun exibirMenu() {
    println("1. Matricular aluno")
    println("2. Remover aluno")
    println("3. Exibir alunos matriculados")
    println("4. Exibir conteúdos da formação")
    println("5. Sair")
}

// Função para processar a escolha do usuário
fun processarEscolha(escolha: Int, formacao: Formacao) {
    when (escolha) {
        1 -> {
            print("Digite o nome do aluno a ser matriculado: ")
            val nomeAluno = readLine() ?: ""
            formacao.matricular(Usuario(nomeAluno))
        }
        2 -> {
            print("Digite o nome do aluno a ser removido: ")
            val nomeAluno = readLine() ?: ""
            formacao.removerAluno(Usuario(nomeAluno))
        }
        3 -> {
            println("Alunos matriculados na formação ${formacao.nome}: ${formacao.obterAlunosMatriculados().map { it.nome }}")
        }
        4 -> {
            println("Conteúdos da formação ${formacao.nome}: ${formacao.obterConteudos().map { it.nome }}")
        }
        5 -> {
            println("Saindo do programa.")
            System.exit(0)
        }
        else -> {
            println("Opção inválida. Tente novamente.")
        }
    }
}

fun main() {
    try {
        val conteudo1 = ConteudoEducacional("Introdução ao Kotlin")
        val conteudo2 = ConteudoEducacional("Programação Orientada a Objetos", 90)
        val formacao = Formacao("Desenvolvimento Kotlin", conteudo1, conteudo2)

        while (true) {
            exibirMenu()
            print("Escolha uma opção: ")
            val escolha = readLine()?.toIntOrNull() ?: 0

            processarEscolha(escolha, formacao)
        }
    } catch (ex: MatriculaException) {
        println("Erro ao matricular: ${ex.message}")
    }
}
