package com.Jogo;

import java.util.Random;
import java.util.Scanner;

/**
 * Jogo Caça ao Tesouro
 * 
 * O objetivo é encontrar 3 tesouros em um tabuleiro 10x10, evitando armadilhas.
 * O jogador se move pelo tabuleiro e recebe dicas para ajudar na busca.
 * 
 * Funcionalidades implementadas:
 * - Delay de 1 segundo após cada movimento para melhor experiência visual.
 * - Posições não visitadas são exibidas em amarelo no console.
 * - Tesouros encontrados aparecem como 'T', outras posições visitadas como 'X'.
 * - A posição inicial já começa marcada como visitada.
 * - Dicas simples sobre tesouros/armadilhas nas casas próximas, linhas e colunas.
 * - A cada 15 passos, um tesouro não encontrado é revelado no mapa e aparece a mensagem "BÔNUS: TESOURO REVELADO".
 * 
 * Legenda do mapa:
 * P = jogador
 * - = posição não visitada (amarelo)
 * X = posição visitada
 * T = tesouro já visitado ou revelado
 */
public class Main {

    /**
     * Inicializa o tabuleiro com 3 tesouros (1) e 5 armadilhas (-1) em posições aleatórias.
     */
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

        // Posicionando 5 armadilhas no tabuleiro
        for (int i = 0; i < 5; i++) {
            int x, y;
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            } while (tabuleiro[x][y] != 0);
            tabuleiro[x][y] = -1;
        }
    }

    /**
     * Exibe a mensagem inicial do jogo e a legenda do mapa.
     */
    public static void exibirMensagemInicial() {
        System.out.println("Bem-vindo ao Caça ao Tesouro!\n");
        System.out.println("Você deve encontrar 3 tesouros e evitar as armadilhas. Boa sorte!\n");
        System.out.println("P = jogador, - = posição não visitada, X = posição visitada, 1 = tesouro, -1 = armadilha\n");
    }

    /**
     * Imprime o tabuleiro no console, mostrando o jogador, posições visitadas, tesouros e cores.
     * 
     * @param tabuleiro matriz com tesouros e armadilhas
     * @param posicoesVisitadas matriz de posições já visitadas
     * @param posicaoJogadorX linha do jogador
     * @param posicaoJogadorY coluna do jogador
     */
    public static void imprimirTabuleiro(int[][] tabuleiro, boolean[][] posicoesVisitadas, int posicaoJogadorX, int posicaoJogadorY) {
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (i == posicaoJogadorX && j == posicaoJogadorY) {
                    System.out.print("P "); // Jogador
                } 
                else if (posicoesVisitadas[i][j]) {
                    if (tabuleiro[i][j] == 1) {
                        System.out.print("T "); // Tesouro já visitado
                    } else {
                        System.out.print("X "); // Posição já visitada
                    }
                } else {
                    System.out.print(ANSI_YELLOW + "- " + ANSI_RESET); // Posição não visitada em amarelo
                }
            }
            System.out.println();
        }
    }

    /**
     * Move o jogador conforme o comando do usuário.
     * Marca a posição como visitada e verifica se encontrou tesouro ou armadilha.
     * Adiciona um delay de 1 segundo após o movimento.
     * 
     * @return vetor com nova posição do jogador e quantidade de tesouros encontrados
     */
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

        // Adiciona delay de 1 segundo após o movimento
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return new int[]{posicaoJogadorX, posicaoJogadorY, tesourosEncontrados};
    }

    /**
     * Fornece dicas ao jogador sobre a presença de tesouros ou armadilhas próximos,
     * incluindo nas casas ao redor, linhas e colunas.
     */
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
            
            // DICA EXTRA: Tesouro na mesma linha
            boolean tesouroLinha = false;
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[x][j] == 1 && j != y) {
                    tesouroLinha = true;
                    break;
                }
            }
            if (tesouroLinha) {
                System.out.println("DICA EXTRA: Existe um TESOURO em algum lugar desta linha!");
            }

            // DICA EXTRA: Tesouro na mesma coluna
            boolean tesouroColuna = false;
            for (int i = 0; i < 10; i++) {
                if (tabuleiro[i][y] == 1 && i != x) {
                    tesouroColuna = true;
                    break;
                }
            }
            if (tesouroColuna) {
                System.out.println("DICA EXTRA: Existe um TESOURO em algum lugar desta coluna!");
            }

            // DICA EXTRA: Armadilha na mesma linha
            boolean armadilhaLinha = false;
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[x][j] == -1 && j != y) {
                    armadilhaLinha = true;
                    break;
                }
            }
            if (armadilhaLinha) {
                System.out.println("CUIDADO EXTRA: Existe uma ARMADILHA em algum lugar desta linha!");
            }

            // DICA EXTRA: Armadilha na mesma coluna
            boolean armadilhaColuna = false;
            for (int i = 0; i < 10; i++) {
                if (tabuleiro[i][y] == -1 && i != x) {
                    armadilhaColuna = true;
                    break;
                }
            }
            if (armadilhaColuna) {
                System.out.println("CUIDADO EXTRA: Existe uma ARMADILHA em algum lugar desta coluna!");
            }
        }
    }

    /**
     * Revela automaticamente a posição de um tesouro não encontrado no mapa,
     * marcando-o como visitado e exibindo a mensagem de bônus.
     * É chamada a cada 15 passos do jogador.
     */
    public static void revelarTesouro(int[][] tabuleiro, boolean[][] posicoesVisitadas) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[i][j] == 1 && !posicoesVisitadas[i][j]) {
                    System.out.println("BÔNUS: TESOURO REVELADO");
                    System.out.println("Tesouro revelado na posição (" + i + ", " + j + ")!");
                    return;
                }
                
            }
        }
    }

    /**
     * Função principal do jogo.
     * Controla o loop de jogadas, contagem de passos, dicas e revelação de tesouros.
     */
    public static void main(String[] args) {
        int[][] tabuleiro = new int[10][10];
        boolean[][] posicoesVisitadas = new boolean[10][10];
        int posicaoJogadorX = 0, posicaoJogadorY = 0;
        int tesourosEncontrados = 0;
        int passos = 0; // contador de passos

        inicializarTabuleiro(tabuleiro);
        exibirMensagemInicial();

        // Marca a posição inicial como visitada
        posicoesVisitadas[posicaoJogadorX][posicaoJogadorY] = true;

        while (tesourosEncontrados < 3) {
            imprimirTabuleiro(tabuleiro, posicoesVisitadas, posicaoJogadorX, posicaoJogadorY);
            int[] resultado = movimentarJogador(tabuleiro, posicoesVisitadas, posicaoJogadorX, posicaoJogadorY, tesourosEncontrados);
            posicaoJogadorX = resultado[0];
            posicaoJogadorY = resultado[1];
            tesourosEncontrados = resultado[2];

            passos++; // incrementa passos

            if (passos > 0 && passos % 15 == 0) {
                revelarTesouro(tabuleiro, posicoesVisitadas);
            }

            if (tesourosEncontrados < 3) {
                dicaAlgoPerto(tabuleiro, posicaoJogadorX, posicaoJogadorY);
            }

            System.out.println();
            System.out.println("Você está com: " + tesourosEncontrados + " TESOUROS!");
        }

        System.out.println("Parabéns! Você encontrou todos os tesouros!");
    }
}