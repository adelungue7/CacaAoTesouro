package com.Jogo;

import java.util.Random;
import java.util.Scanner;

public class Main {
    

    public static void inicializarTabuleiro(int[][] tabuleiro) {
        Random random = new Random();

        // Posicionando três tesouros no tabuleiro
        for (int i = 0; i < 3; i++) {
            int x, y;
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            } while (tabuleiro[x][y] != 0);
            tabuleiro[x][y] = 1;
        }

        // Posicionando 10 armadilhas no tabuleiro
        for (int i = 0; i < 5; i++) {
            int x, y;
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            } while (tabuleiro[x][y] != 0);
            tabuleiro[x][y] = -1;
        }
    }

    public static void exibirMensagemInicial() {
        System.out.println("Bem-vindo ao Caça ao Tesouro!\n");
        System.out.println("Você deve encontrar 3 tesouros e evitar as armadilhas. Boa sorte!\n");
        System.out.println("P = jogador, - = posição não visitada, X = posição visitada, 1 = tesouro, -1 = armadilha\n");
    }

    public static void imprimirTabuleiro(int[][] tabuleiro, boolean[][] posicoesVisitadas, int posicaoJogadorX, int posicaoJogadorY) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (i == posicaoJogadorX && j == posicaoJogadorY) {
                    System.out.print("P "); // Jogador
                } 
                else if (posicoesVisitadas[i][j] == true) {
                    System.out.print("X "); // Posição já visitada
                } else {
                    System.out.print("- "); // Posição não visitada
                }
            }
            System.out.println();
        }
    }

    public static int[] movimentarJogador(int[][] tabuleiro, boolean[][] posicoesVisitadas, int posicaoJogadorX, int posicaoJogadorY, int tesourosEncontrados) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o movimento (w = cima, s = baixo, a = esquerda, d = direita): ");
        char movimento = input.next().charAt(0);
        int novaPosicaoX = posicaoJogadorX, novaPosicaoY = posicaoJogadorY;
    
        switch (movimento) {
            case 'w': novaPosicaoX--; break;
            case 's': novaPosicaoX++; break;
            case 'a': novaPosicaoY--; break;
            case 'd': novaPosicaoY++; break;
            default:
                System.out.println("Movimento inválido!");
                break;
        }
    
        // Verificar limites do tabuleiro
        if (novaPosicaoX >= 0 && novaPosicaoX < 10 && novaPosicaoY >= 0 && novaPosicaoY < 10) {
            posicaoJogadorX = novaPosicaoX;
            posicaoJogadorY = novaPosicaoY;
    
            // Marcar como visitado
            if (!posicoesVisitadas[posicaoJogadorX][posicaoJogadorY]) {
                posicoesVisitadas[posicaoJogadorX][posicaoJogadorY] = true;
    
                // Verificar o que há na posição
                if (tabuleiro[posicaoJogadorX][posicaoJogadorY] == 1) {
                    System.out.println("Você encontrou um tesouro!");
                    tesourosEncontrados++;
                    
                } else if (tabuleiro[posicaoJogadorX][posicaoJogadorY] == -1) {
                    System.out.println("Você caiu em uma armadilha! Fim de jogo.");
                    System.exit(0);
                }
            } else {
                System.out.println("Você já visitou essa posição.");
            }
        } else {
            System.out.println("Movimento fora do tabuleiro!");
        }
    
        return new int[]{posicaoJogadorX, posicaoJogadorY, tesourosEncontrados};
    }

     public static void dicaAlgoPerto(int[][] tabuleiro, int x, int y) {
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
}
    public static void main(String[] args) {
        int[][] tabuleiro = new int[10][10];
        boolean[][] posicoesVisitadas = new boolean[10][10];
        int posicaoJogadorX = 0, posicaoJogadorY = 0;
        int tesourosEncontrados = 0;

        inicializarTabuleiro(tabuleiro);
        exibirMensagemInicial();

        while (tesourosEncontrados < 3) {
            imprimirTabuleiro(tabuleiro, posicoesVisitadas, posicaoJogadorX, posicaoJogadorY);
            int[] resultado = movimentarJogador(tabuleiro, posicoesVisitadas, posicaoJogadorX, posicaoJogadorY, tesourosEncontrados);
            posicaoJogadorX = resultado[0];
            posicaoJogadorY = resultado[1];
            tesourosEncontrados = resultado[2];

            if (tesourosEncontrados < 3) {
                dicaAlgoPerto(tabuleiro, posicaoJogadorX, posicaoJogadorY);
            }

            System.out.println();
            System.out.println("Você está com: " + tesourosEncontrados + " TESOUROS!");
        }

        System.out.println("Parabéns! Você encontrou todos os tesouros!");
    }
}