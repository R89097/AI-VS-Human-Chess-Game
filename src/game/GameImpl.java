package game;

import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {
    //set fields  )board, player, move)
    private final Grid board;
    private PieceColour currentPlayer;
    private final List<Move> moveHistory;

    public GameImpl(int size) {
        if (size <= 3){
            throw new IllegalArgumentException("Board size must be at least 3.");
        }
        this.board = new GridImpl(size);
        this.currentPlayer = PieceColour.WHITE;
        this.moveHistory = new ArrayList<>();
    }

    public GameImpl(Grid board, PieceColour currentPlayer, List<Move> moveHistory) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.moveHistory = new ArrayList<>(moveHistory);
    }

    @Override
    public PieceColour currentPlayer() {
        return currentPlayer;
    }

    @Override
    public boolean isOver() {
        return winner() != PieceColour.NONE || getMoves().isEmpty();
    }

    @Override
    //using PathFinder if there is a winning path + winner(W or B)
    public PieceColour winner() {
         if (PathFinder.topToBottom(board, PieceColour.WHITE) || PathFinder.leftToRight(board, PieceColour.WHITE)) {
        return PieceColour.WHITE;
    }
        if (PathFinder.topToBottom(board, PieceColour.BLACK) || PathFinder.leftToRight(board, PieceColour.BLACK)) {
            return PieceColour.BLACK;
        }
        return PieceColour.NONE;
    }

    @Override
    public List<Move> getMoves() {
        List<Move> moves = new ArrayList<>();
        int size = board.getSize();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.getPiece(row, col) == PieceColour.NONE) {
                    moves.add(new MoveImpl(row, col));
                }
            }
        }
        return moves;
    }


    @Override
    //last move made
    public void makeMove(Move move){
        int row = move.getRow();
        int col = move.getCol();
        //check if move within bounds and is empty
        if (!isValidMove(row, col)) {
            throw new IllegalArgumentException("Invalid move");
        }
        board.setPiece(row, col, currentPlayer);
        moveHistory.add(move);

        //swap player
        if (currentPlayer == PieceColour.WHITE) {
            currentPlayer = PieceColour.BLACK;
        } else {
            currentPlayer = PieceColour.WHITE;
        }
    }

    @Override
    //copy of board and moves
    public Game copy() {
        Grid copiedBoard = board.copy();
        List<Move> copiedMoves = new ArrayList<>();
        for (Move m : moveHistory) {
            copiedMoves.add(new MoveImpl(m.getRow(), m.getCol()));
        }
        return new GameImpl(copiedBoard, currentPlayer, copiedMoves);
    }
    

    @Override
    public Grid getGrid() {
        return board;
    }

        //check if move within bounds and is empty
    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            return false;
        }
        return board.getPiece(row, col) == PieceColour.NONE;

    }

    @Override
    public String toString() {
        return board.toString() + "\n" + currentPlayer;
    }
}

