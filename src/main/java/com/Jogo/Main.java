package com.Jogo;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] tabuleiro = new int[10][10];
        boolean[][] posicoesVisitadas = new boolean[10][10];
        Random random = new Random();
        Scanner input = new Scanner(System.in);

        // Posicionando três tesouros no tabuleiro sem repetir posições (representados por 1)
        for (int i = 0; i < 3; i++) {
            int x, y;
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            } while (tabuleiro[x][y] != 0); // Garante que tesouros não se repitam 
            tabuleiro[x][y] = 1;
        }

        // Posicionando 10 armadilhas no tabuleiro (representadas por -1) sem repetir posições
        for (int i = 0; i < 10; i++) { 
            int x, y;
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            } while (tabuleiro[x][y] != 0); // Garante que armadilhas não se repitam
            tabuleiro[x][y] = -1;
        }

        // Posição inicial do jogador e contagem de tesouros encontrados
        int posicaoJogadorX = 0, posicaoJogadorY = 0;
        int tesourosEncontrados = 0;

        System.out.println("Bem-vindo ao Caça ao Tesouro!\n");
        System.out.println("Você deve encontrar 3 tesouros e evitar as armadilhas. Boa sorte!\n");
        System.out.println("P = jogador, - = posição não visitada, X = posição visitada, 1 = tesouro, -1 = armadilha\n");

        // Printando o tabuleiro
        while (tesourosEncontrados < 3) {
            for (int i = 0; i < tabuleiro.length; i++) {
                for (int j = 0; j < tabuleiro[i].length; j++) {
                    if (i == posicaoJogadorX && j == posicaoJogadorY) {
                        System.out.print("P "); // Jogador
                    } else if (posicoesVisitadas[i][j] == true) {
                        System.out.print("X "); // Posição já visitada
                    } else {
                        System.out.print("- "); // Posição não visitada
                    }
                }
                System.out.println();
            }

            // Movimento do jogador
            System.out.println("Digite o movimento (w = cima, s = baixo, a = esquerda, d = direita): ");
            char movimento = input.next().charAt(0);
            int novaPosicaoX = posicaoJogadorX, novaPosicaoY = posicaoJogadorY;

            switch (movimento) {
                case 'w': novaPosicaoX--; break;
                case 's': novaPosicaoX++; break;
                case 'a': novaPosicaoY--; break;
                case 'd': novaPosicaoY++; break;
                default: System.out.println("Movimento inválido!"); continue;
            }

            // Verificar limites do tabuleiro
            if (novaPosicaoX < 0 || novaPosicaoX >= 10 || novaPosicaoY < 0 || novaPosicaoY >= 10) {
                System.out.println("Movimento fora do tabuleiro!");
                continue;
            }

            posicaoJogadorX = novaPosicaoX;
            posicaoJogadorY = novaPosicaoY;

            // Marcar como visitado
            posicoesVisitadas[posicaoJogadorX][posicaoJogadorY] = true;

            // Verificar o que há na posição
            if (tabuleiro[posicaoJogadorX][posicaoJogadorY] == 1) {
                System.out.println("Você encontrou um tesouro!");
                tesourosEncontrados++;
                tabuleiro[posicaoJogadorX][posicaoJogadorY] = 0; // Remover tesouro
            } else if (tabuleiro[posicaoJogadorX][posicaoJogadorY] == -1) {
                System.out.println("Você caiu em uma armadilha! Fim de jogo.");
                return;
            }
        }

        System.out.println("Parabéns! Você encontrou todos os tesouros!");
    }
}