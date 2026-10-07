# ⚔ Gladiador de Arena

Um jogo de combate em turnos executado via terminal, desenvolvido em **Kotlin** como estudo de caso para praticar fundamentos da linguagem.

## 📖 Sobre o projeto

**Gladiador de Arena** é um combate por turnos onde o jogador enfrenta um oponente controlado pelo computador. Em cada turno, o jogador escolhe uma ação e o programa resolve o dano, atualiza o HP e a stamina, e verifica as condições de vitória ou derrota.

### Mecânicas principais

- **Ataque Forte** — causa bastante dano, mas consome muita stamina (25 pontos).
- **Ataque Fraco** — causa dano moderado e consome pouca stamina (10 pontos).
- **Defesa** — não causa dano, mas reduz o dano recebido no próximo turno em 60% e regenera stamina.
- **Regeneração natural** — a cada 3 turnos, o jogador recupera stamina extra.

O combate continua em loop até que o HP do jogador ou do inimigo chegue a zero.

### Conceitos de Kotlin aplicados

- Entrada e saída de dados via terminal (`println`, `readlnOrNull`).
- Operadores matemáticos (`+`, `-`, `*`, `/`, `%`), lógicos (`&&`, `||`, `!`) e relacionais (`>`, `<`, `>=`, `==`).
- Estruturas condicionais: `if/else` e `when`.
- Laços de repetição: `while` (loop principal e validação de entrada).
- Funções para separar a lógica (cálculo de dano, exibição de status, processamento de ações).
- **Null Safety**: uso de `readlnOrNull()?.trim() ?: ""` e `toIntOrNull()` com verificação de nulo.

## 🚀 Como rodar o programa

### Pré-requisitos

- Ter o **Kotlin** instalado ([instruções oficiais](https://kotlinlang.org/docs/command-line.html))
- Ou usar uma IDE como **IntelliJ IDEA**, **Android Studio** ou **VS Code com extensão Kotlin**.

### Opção 1 — Linha de comando (kotlinc)

```bash
# Clone o repositório
git clone https://github.com/SEU-USUARIO/gladiador-de-arena.git
cd gladiador-de-arena

# Compile o arquivo
kotlinc src/Main.kt -include-runtime -d gladiador.jar

# Execute
java -jar gladiador.jar
```

### Opção 2 — IntelliJ IDEA

1. Abra o IntelliJ IDEA e escolha **Open**.
2. Selecione a pasta do projeto.
3. Abra o arquivo `src/Main.kt`.
4. Clique no ícone ▶ ao lado da função `main` para executar.

### Opção 3 — Kotlin Playground (online)

Se preferir testar rapidamente sem instalar nada, copie o conteúdo de `src/Main.kt` em [https://play.kotlinlang.org](https://play.kotlinlang.org) e clique em **Run**.

## 🎮 Como jogar

Ao iniciar, o terminal mostrará o status da batalha e um menu com três opções:

```
[1] Ataque Forte  (custo: 25)
[2] Ataque Fraco  (custo: 10)
[3] Defesa        (regenera stamina)
```

Digite o número da ação desejada e pressione **Enter**. O jogo continua até alguém ser derrotado.

## 📁 Estrutura do projeto

```
gladiador-de-arena/
├── src/
│   └── Main.kt       # Código-fonte completo do jogo
├── README.md         # Este arquivo
└── .gitignore        # Arquivos ignorados pelo Git
```

## 👤 Diogo Dutra Jacó

 [@dhidt-code](https://github.com/dhidt-code)
