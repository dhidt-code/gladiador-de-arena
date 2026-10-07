import kotlin.random.Random

// Constantes do jogo
const val HP_MAX_JOGADOR = 100
const val HP_MAX_INIMIGO = 100
const val STAMINA_MAX = 100
const val CUSTO_ATAQUE_FORTE = 25
const val CUSTO_ATAQUE_FRACO = 10
const val REGEN_STAMINA = 8

fun main() {
    println("========================================")
    println("       ⚔  GLADIADOR DE ARENA  ⚔        ")
    println("========================================")
    println("Sobreviva ao seu oponente!")
    println("Ataque Forte: muito dano, gasta 25 de stamina")
    println("Ataque Fraco: dano leve, gasta 10 de stamina")
    println("Defesa: reduz dano recebido, regenera stamina")
    println("========================================\n")

    var hpJogador = HP_MAX_JOGADOR
    var staminaJogador = STAMINA_MAX
    var hpInimigo = HP_MAX_INIMIGO
    var turno = 1
    var defendendo = false

    while (hpJogador > 0 && hpInimigo > 0) {
        exibirStatus(turno, hpJogador, staminaJogador, hpInimigo)

        val escolha = lerEscolha()
        if (escolha == null) {
            println("\nEntrada encerrada. Batalha cancelada.")
            break
        }

        val resultadoAcao =
            processarAcaoJogador(escolha, hpJogador, staminaJogador, hpInimigo)
        hpJogador = resultadoAcao.first
        staminaJogador = resultadoAcao.second
        hpInimigo = resultadoAcao.third
        defendendo = resultadoAcao.fourth

        if (hpInimigo <= 0) {
            println("\n🏆 VITÓRIA! Você derrotou o oponente em $turno turnos!")
            break
        }

        // Turno do inimigo
        println("\n--- Turno do Inimigo ---")
        val danoRecebido = calcularDanoInimigo(defendendo)
        hpJogador = (hpJogador - danoRecebido).coerceAtLeast(0)

        if (defendendo && danoRecebido < 15) {
            println("🛡  Você bloqueou parte do ataque! Dano recebido: $danoRecebido")
        } else {
            println("💥 O inimigo atacou! Dano recebido: $danoRecebido")
        }

        // Verifica a derrota antes de avançar o turno.
        if (hpJogador <= 0) {
            println("\n💀 DERROTA! Você foi derrotado no turno $turno.")
            break
        }

        // Regeneração natural de stamina com bônus a cada 3 turnos.
        if (turno % 3 == 0) {
            val bonus = turno / 3
            val regeneracao = REGEN_STAMINA + bonus
            staminaJogador =
                (staminaJogador + regeneracao).coerceAtMost(STAMINA_MAX)
            println("♻  Regeneração extra de stamina (+$regeneracao)")
        }

        defendendo = false
        turno++
    }

    println("\n========= FIM DA BATALHA =========")
}

/**
 * Exibe o status atual do combate.
 */
fun exibirStatus(turno: Int, hpJogador: Int, stamina: Int, hpInimigo: Int) {
    println("\n╔═══════════ TURNO $turno ═══════════╗")
    println("  Jogador  HP: $hpJogador/$HP_MAX_JOGADOR | Stamina: $stamina/$STAMINA_MAX")
    println("  Inimigo  HP: $hpInimigo/$HP_MAX_INIMIGO")
    println("╠════════════════════════════════════╣")
    println("  [1] Ataque Forte  (custo: $CUSTO_ATAQUE_FORTE)")
    println("  [2] Ataque Fraco  (custo: $CUSTO_ATAQUE_FRACO)")
    println("  [3] Defesa        (regenera stamina)")
    println("╚════════════════════════════════════╝")
}

/**
 * Lê a escolha e repete a solicitação até receber uma opção válida.
 * Retorna null quando a entrada termina.
 */
fun lerEscolha(): Int? {
    while (true) {
        print("Escolha sua ação: ")
        val entrada = readlnOrNull() ?: return null
        val opcao = entrada.trim().toIntOrNull()

        if (opcao != null && opcao in 1..3) {
            return opcao
        }

        println("⚠  Entrada inválida! Digite 1, 2 ou 3.")
    }
}

/**
 * Processa a ação do jogador. Retorna:
 * (hpJogador, stamina, hpInimigo, defendendo)
 */
fun processarAcaoJogador(
    escolha: Int,
    hpJogador: Int,
    stamina: Int,
    hpInimigo: Int
): Quadruple<Int, Int, Int, Boolean> {
    var hpJ = hpJogador
    var st = stamina
    var hpI = hpInimigo
    var def = false

    when (escolha) {
        1 -> {
            if (st >= CUSTO_ATAQUE_FORTE) {
                val dano = calcularDanoAtaqueForte()
                hpI -= dano
                st -= CUSTO_ATAQUE_FORTE
                println("🗡  ATAQUE FORTE! Dano causado: $dano")
            } else {
                println("❌ Stamina insuficiente para Ataque Forte! Você perde o turno.")
            }
        }

        2 -> {
            if (st >= CUSTO_ATAQUE_FRACO) {
                val dano = calcularDanoAtaqueFraco()
                hpI -= dano
                st -= CUSTO_ATAQUE_FRACO
                println("⚔  Ataque fraco. Dano causado: $dano")
            } else {
                println("❌ Stamina insuficiente! Você perde o turno.")
            }
        }

        3 -> {
            val regeneracao = REGEN_STAMINA + 5
            st = (st + regeneracao).coerceAtMost(STAMINA_MAX)
            def = true
            println("🛡  Postura defensiva! Stamina recuperada: +$regeneracao")
        }

        else -> println("Ação desconhecida.")
    }

    hpI = hpI.coerceAtLeast(0)
    hpJ = hpJ.coerceAtLeast(0)
    st = st.coerceAtLeast(0)

    return Quadruple(hpJ, st, hpI, def)
}

/**
 * Calcula dano do Ataque Forte (entre 18 e 30).
 */
fun calcularDanoAtaqueForte(): Int = Random.nextInt(18, 31)

/**
 * Calcula dano do Ataque Fraco (entre 6 e 14).
 */
fun calcularDanoAtaqueFraco(): Int = Random.nextInt(6, 15)

/**
 * Calcula o dano do inimigo, reduzido se o jogador estiver defendendo.
 */
fun calcularDanoInimigo(defendendo: Boolean): Int {
    val danoBase = Random.nextInt(8, 20)

    return if (defendendo) {
        (danoBase * 40 / 100).coerceAtLeast(1)
    } else {
        danoBase
    }
}

/**
 * Classe simples para retornar múltiplos valores.
 */
data class Quadruple<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)
