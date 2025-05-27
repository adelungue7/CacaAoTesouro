# 🏴‍☠️Jogo Caça ao Tesouro (Java)


## 📌Descrição
***Caça ao Tesouro** é um jogo simples de terminal feito em Java, onde o jogador precisa se movimentar por um tabuleiro 10x10 em busca de 3 tesouros, enquanto evita armadilhas. O objetivo é explorar o mapa, encontrar os tesouros e vencer sem cair em nenhuma armadilha!


---

#  📂Código

o Código esta divido em 5 funções e a main:

### `inicializarTabuleiro(int[][] tabuleiro)`

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

### `exibirMensagemInicial()`

Essa função basicamente mostra as instruções para o jogador: 

```java
System.out.println("Bem-vindo ao Caça ao Tesouro!\n");
System.out.println("Você deve encontrar 3 tesouros e evitar as armadilhas. Boa sorte!\n");
```

---

### `imprimirTabuleiro(int[][] tabuleiro, boolean[][] posicoesVisitadas, int posicaoJogadorX, int posicaoJogadorY)`

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

### `movimentarJogador(int[][] tabuleiro, boolean[][] posicoesVisitadas, int posicaoJogadorX, int posicaoJogadorY, int tesourosEncontrados)`

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

### `FUNÇÕES DE DICA (A FAZER)`

---
    imprimirTabuleiro(...);

### `main`

