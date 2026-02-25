package game.tests;

import java.util.Collection;
import game.*;

public class GameTest extends Test {
    public static void main(String[] args) {
        boolean caught = false;
        try {
            new GameImpl(0);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(true, caught);

        caught = false;
        try {
            new GameImpl(5);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(false, caught);

        // TODO: Complete this test
        Game game = new GameImpl(5);

        //check if player is White at start
        expect(PieceColour.WHITE, game.currentPlayer());

        //check is valid move and if grid is updated correctly 
        Move move1 = new MoveImpl(0, 0);
        game.makeMove(move1);
        expect(PieceColour.BLACK, game.currentPlayer());
        expect(PieceColour.WHITE, game.getGrid().getPiece(0, 0));

        Grid g1 = game.getGrid();
        expect(PieceColour.WHITE, g1.getPiece(0, 0));

        //check if switch black (after move)
        expect(PieceColour.BLACK, game.currentPlayer());

        //if invalid (already made)
        caught = false;
        try{
            game.makeMove(move1);
        } catch (IllegalArgumentException e){
            caught = true;
        }
        expect(true, caught);

        //check 2nd move 
        Move move2 = new MoveImpl(1,1);
        game.makeMove(move2);
        expect(PieceColour.WHITE, game.currentPlayer());
        expect(PieceColour.BLACK, game.getGrid().getPiece(1, 1));

        //check getMoves excludes filled points (already existing moves)
        Collection<Move> moves = game.getMoves();
        boolean found = false;
        for (Move m:moves){
            if (m.getRow()==0 && m.getCol()==0){
                found = true;
                break; 
            }
        }
        expect(false, found);

        //check independent test copy
        Game copy = game.copy();
        copy.makeMove(new MoveImpl(0,1));
        expect(PieceColour.NONE, game.getGrid().getPiece(0, 1));

        //WHITE wim simulation
        WinSimulation();

        //draw simmulation
        DrawSimulation();

        checkAllTestsPassed();
    }

    //WHITE wim simulation
    private static void WinSimulation() {
        Game quickWin = new GameImpl(5);
        playMoves(quickWin, new int[][]{
            {0,0}, {1,0},  
            {0,1}, {1,1},  
            {0,2}, {2,0}, 
            {0,3}, {3,0},
            {0,4}         // W wins
        });
            
        expect(true, quickWin.isOver());
        expect(PieceColour.WHITE, quickWin.winner());
    }

    //draw simmulation
    private static void DrawSimulation() {
        Game drawGame = new GameImpl(4);
        //runs moves to fill board
        playMoves(drawGame, new int[][]{
            {0,0}, {0,1}, {0,2}, {0,3},
            {1,1}, {1,0}, {1,3}, {1,2},
            {2,0}, {2,2}, {2,1}, {2,3},
            {3,1}, {3,0}, {3,3}, {3,2}  //draw
        });

        expect(true, drawGame.isOver());
        expect(PieceColour.NONE, drawGame.winner());
    }

    //move game simulation
    private static void playMoves(Game game, int[][] coords) {
        for (int[] move : coords) {
            game.makeMove(new MoveImpl(move[0], move[1]));
        }
    }
}