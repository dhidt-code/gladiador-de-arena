// Q1:Sistema de Cupons Avançado
fun calcularDesconto(valor: Double, cupom: String?): Double {
    return when (cupom) {
        "PROMO10" -> valor - 10
        "PROMO20" -> valor - 20
        else -> valor
    }
}

// Q2:Auditoria de Entregas
fun auditarEntregas(enderecos: List<String?>) {
    for (endereco in enderecos) {
        val enderecoFinal = endereco ?: "Endereço Desconhecido"
        
        if (enderecoFinal == "Endereço Desconhecido") {
            println("Entrega Pendente: Falta de dados")
        } else {
            println("Rota traçada para: $enderecoFinal")
        }
    }
}

// Q3:Validação de Perfil de Streaming
fun validarBioInfantil(bio: String?) {
    val tamanho = bio?.length ?: 0
    
    if (tamanho <= 50) {
        println("Bio aceita")
    } else {
        println("Bio muito longa")
    }
}

// Q4:Processamento de Transações Pix
fun processarPix() {
    val transacoes = listOf(50.0, null, 120.5, null, 10.0)
    var total = 0.0
    
    for (valor in transacoes) {
        if (valor != null) {
            total += valor
        } else {
            println("Transação ignorada")
        }
    }
    
    println("Total processado: R$ $total")
}

// ============================================
// QUESTÃO 5: Classificação de Feedback de Motoristas
// ============================================
fun avaliarMotorista(nota: Int?) {
    val notaSegura = nota ?: 0
    
    when (notaSegura) {
        5 -> println("Excelente corrida!")
        4 -> println("Boa corrida.")
        1, 2, 3 -> println("Precisamos melhorar.")
        0 -> println("Nenhuma avaliação fornecida.")
        else -> println("Nota inválida.")
    }
}

// Q6:Função Lambda para Cálculo de Gorjeta
val calcularGorjeta: (Double?) -> Double = {
    if (it == null || it < 0) 0.0 else it
}

// Q7:Limpeza de Banco de Dados de Usuários
fun limparBancoDeDados(emails: List<String?>) {
    var contasInvalidas = 0
    
    for (email in emails) {
        val tamanho = email?.length ?: 0
        
        if (email == null || tamanho == 0) {
            contasInvalidas++
            println("Deletando conta inválida...")
        } else {
            println("Conta válida: $email")
        }
    }
    
    println("Total de contas a apagar: $contasInvalidas")
}

// MAIN
fun main() {
    println("\nQ1:Cupons")
    println(calcularDesconto(100.0, "PROMO10"))   // 90.0
    println(calcularDesconto(100.0, "PROMO20"))   // 80.0
    println(calcularDesconto(100.0, null))        // 100.0
    println(calcularDesconto(100.0, "INVALIDO"))  // 100.0
    
    println("\nQ2:Entregas")
    val listaEntregas = listOf("Rua A, 123", null, "Av. Brasil, 456", null, "Rua C, 789")
    auditarEntregas(listaEntregas)
    
    println("\nQ3:Bio Infantil")
    validarBioInfantil("Adoro desenhos animados!")   // Bio aceita
    validarBioInfantil(null)                          // Bio aceita
    validarBioInfantil("A".repeat(51))                // Bio muito longa
    
    println("\nQ4:Transações Pix")
    processarPix()
    
    println("\nQ5:Avaliação Motorista")
    avaliarMotorista(5)
    avaliarMotorista(4)
    avaliarMotorista(2)
    avaliarMotorista(null)
    
    println("\nQ6:Gorjeta (Lambda)")
    println(calcularGorjeta(10.0))  // 10.0
    println(calcularGorjeta(null))  // 0.0
    println(calcularGorjeta(-5.0))  // 0.0
    println(calcularGorjeta(0.0))   // 0.0
    
    println("\nQl7:Limpeza de BD")
    val emails = listOf("joao@email.com", null, "", "maria@email.com", null, "")
    limparBancoDeDados(emails)
}
