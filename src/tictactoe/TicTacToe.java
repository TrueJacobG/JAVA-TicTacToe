package tictactoe;

import java.util.*;

public class TicTacToe {

    private static String winner = "";
    private static List<Integer> placedSymbolsPositions = new LinkedList<>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
    private static char[][] gameBoard = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
    };


    public static void run(){
        Scanner scanner = new Scanner(System.in);

        int pos;


        while(true) {
            System.out.println("Where do you wanna put a symbol?");

            while(true){
                pos = scanner.nextInt();
                if(placedSymbolsPositions.contains(pos)){
                    break;
                }
                System.out.println("You can't place a symbol here! Select another place.");
            }

            changeSymbol(pos, true);
            placedSymbolsPositions.remove(Integer.valueOf(pos));

            if(isWinner()){
                printBoard();
                break;
            }

            pos = getRandomPosition();
            changeSymbol(pos, false);
            placedSymbolsPositions.remove(Integer.valueOf(pos));
            printBoard();

            if(isWinner()){
                break;
            }

        }

        if(winner.equals("")){
            draw();
        } else {
            congratulations();
        }
    }




    private static boolean isWinner() {

        if((gameBoard[0][0] == 'X' && gameBoard[2][2] == 'X' && gameBoard[4][4] == 'X') ||
                (gameBoard[0][4] == 'X' && gameBoard[2][2] == 'X' && gameBoard[4][0] == 'X')){
            winner = "Player";
            return true;
        }

        if((gameBoard[0][0] == 'O' && gameBoard[2][2] == 'O' && gameBoard[4][4] == 'O') ||
                (gameBoard[0][4] == 'O' && gameBoard[2][2] == 'O' && gameBoard[4][0] == 'O')){
            winner = "Bot";
            return true;
        }

        for(int i = 0; i < 5; i += 2){
            if((gameBoard[0][i] == 'X' && gameBoard[2][i] == 'X' && gameBoard[4][i] == 'X') ||
                    (gameBoard[i][0] == 'X' && gameBoard[i][2] == 'X' && gameBoard[i][4] == 'X')){
                winner = "Player";
                return true;
            }

            if((gameBoard[0][i] == 'O' && gameBoard[2][i] == 'O' && gameBoard[4][i] == 'O') ||
                    (gameBoard[i][0] == 'O' && gameBoard[i][2] == 'O' && gameBoard[i][4] == 'O')){
                winner = "Bot";
                return true;
            }
        }

        if(placedSymbolsPositions.size() == 0){
            return true;
        }

        return false;

    }

    private static int getRandomPosition() {
        Random random = new Random();
        return placedSymbolsPositions.get(random.nextInt(placedSymbolsPositions.size()));

    }

    private static void congratulations() {
        System.out.println("Congrats " + winner + "!");
    }

    private static void draw() {
        System.out.println("Draw!");
    }

    private static void printBoard() {
        for(char[] row: gameBoard){
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void changeSymbol(int pos, boolean isPlayerMove){
        char symbol;
        if(isPlayerMove) {
            symbol = 'X';
        } else {
            symbol = 'O';
        }


        switch (pos) {
            case 1 -> gameBoard[0][0] = symbol;
            case 2 -> gameBoard[0][2] = symbol;
            case 3 -> gameBoard[0][4] = symbol;
            case 4 -> gameBoard[2][0] = symbol;
            case 5 -> gameBoard[2][2] = symbol;
            case 6 -> gameBoard[2][4] = symbol;
            case 7 -> gameBoard[4][0] = symbol;
            case 8 -> gameBoard[4][2] = symbol;
            case 9 -> gameBoard[4][4] = symbol;
        }
    }

}
