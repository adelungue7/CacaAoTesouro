# 🏴‍☠️Jogo Caça ao Tesouro (Java)


## Descrição
**Caça ao Tesouro** é um jogo simples de terminal feito em Java, onde o jogador precisa se movimentar por um tabuleiro 10x10 em busca de 3 tesouros, enquanto evita armadilhas. O objetivo é explorar o mapa, encontrar os tesouros e vencer sem cair em nenhuma armadilha!


---

## Código

o Código esta divido em 5 funções e a main:

### 1 `inicializarTabuleiro(int[][] tabuleiro)`

Essa função serve para posicionar aleatoriamente os tesouros e as armadilhas:

- 3 Tesouros

- 5 Armadilhas 


```java
for (int i = 0; i < 3; i++) {
    int x, y;
    do {
        x = random.nextInt(10);
        y = random.nextInt(10);
    } while (tabuleiro[x][y] != 0);
    tabuleiro[x][y] = 1;
}
```

--- 

### 2`exibirMensagemInicial()`

Essa função basicamente mostra as instruções para o jogador: 

```java
System.out.println("Bem-vindo ao Caça ao Tesouro!\n");
System.out.println("Você deve encontrar 3 tesouros e evitar as armadilhas. Boa sorte!\n");
```

---

### 3`imprimirTabuleiro(int[][] tabuleiro, boolean[][] posicoesVisitadas, int posicaoJogadorX, int posicaoJogadorY)`

Essa função serve para a impressão do tabuleiro, conforme o jogador vai andando e descobrindo o tabuleiro, ele vai mudando:

- `P` = Posição atual do jogador
- `-` = Posição não visitada
- `X` = Posição já visitada 

```java
if (i == posicaoJogadorX && j == posicaoJogadorY) {
    System.out.print("P "); // Jogador
} else if (posicoesVisitadas[i][j]) {
    System.out.print("X "); // Visitada
} else {
    System.out.print("- "); // Não visitada
}
```
---

### 4`movimentarJogador(int[][] tabuleiro, boolean[][] posicoesVisitadas, int posicaoJogadorX, int posicaoJogadorY, int tesourosEncontrados)`

Essa função pede ao jogador algum movimento usando (`w`, `s`, `a`, `d`):

Essa função ainda verifica se:  

- Se a posição escolhida é válida
- Se encontrou um tesouro
- Se caiu em uma armadilha
- Se ja visitou a posição escolhida

```java
char movimento = input.next().charAt(0);
switch (movimento) {
    case 'w': novaPosicaoX--; break;
    case 's': novaPosicaoX++; break;
    case 'a': novaPosicaoY--; break;
    case 'd': novaPosicaoY++; break;
}
```

---

### 5`dicaAlgoPerto(int[][] tabuleiro, int x, int y)`

Essa função serve para dar dicas ao jogador caso tenha algum tesouro ou alguma bomba por perto.

```java
if ((x > 0 && tabuleiro[x - 1][y] != 0) || (x < 9 && tabuleiro[x + 1][y] != 0) || (y > 0 && tabuleiro[x][y - 1] != 0) || (y < 9 && tabuleiro[x][y + 1] != 0)) {
        // x > 0 ou y > 0 verifica se nã irá sair do tabuleiro
        // x < 9 ou y > 9 verifica se nã irá sair do tabuleiro

        // [x - 1][y]!=0 verifica a posição acima
        // [x + 1][y]!=0 verifica a posição abaixo

        // [x][y - 1]!=0 verifica a posição à esquerda
        // [x][y + 1]!=0 verifica a posição à direita

        System.out.println();
        System.out.println("Tem algo por perto.");
    }
```

---
    imprimirTabuleiro(...);

### `main`

Função principal que inicia o jogo e mantém o loop até que os 3 tesouros sejam encontrados ou o jogador perca.

```java
while (tesourosEncontrados < 3) {
    imprimirTabuleiro(...);
    int[] resultado = movimentarJogador(...);
    posicaoJogadorX = resultado[0];
    posicaoJogadorY = resultado[1];
    tesourosEncontrados = resultado[2];
}
System.out.println("Parabéns! Você encontrou todos os tesouros!");
```
---


### `Regras do jogo`

**Objetivo:** Encontrar 3 Tesouros

**Vitória:** Encontrar  os 3 tesouros sem cair em nenhuma armadilha

**Derrota:** Caso caia em um armadilha, você perde imediatamente 

**Riscos:** Existem 5 minas espalhadas pelo mapa, **CUIDADO!!**

---

### `Saída`
```
Bem-vindo ao Caça ao Tesouro!

Você deve encontrar 3 tesouros e evitar as armadilhas. Boa sorte!

P = jogador, - = posição não visitada, X = posição visitada, 1 = tesouro, -1 = armadilha

P - - - - - - - - -
- - - - - - - - - -
- - - - - - - - - -
- - - - - - - - - -
- - - - - - - - - -
- - - - - - - - - -
- - - - - - - - - -
- - - - - - - - - -
- - - - - - - - - -
- - - - - - - - - -
Digite o movimento (w = cima, s = baixo, a = esquerda, d = direita):
```
---

### `Autores`

Desenvolvedores:  
**Gustavo Gonçalves Viana** - [GitHub: gustavo578](https://github.com/gustavo578)  

---

**João Paulo Figueiredo Serafim** - [GitHub: joaopaulofserafim](https://github.com/joaopaulofserafim)  

---

**João Vitor Reis Alves** - [GitHub: johnz07](https://github.com/johnz07)  

---

**Rafael Adelungue Da Silva** - [GitHub: adelungue07](https://github.com/adelungue07)

---
