package game;

public class GridImpl implements Grid {

    //fields
    private final int size;
    private final PieceColour[][] board;

    //construct empty grid + initialize all for NONE (constructor)
    public GridImpl(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        this.size = size;
        this.board = new PieceColour[size][size];
        
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = PieceColour.NONE;
            }
        }
    }

    //copy constructor for independent grid
    public GridImpl(Grid other) {
        this.size = other.getSize();
        this.board = new PieceColour[size][size];
        
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.board[row][col] = other.getPiece(row, col);
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public PieceColour getPiece(int row, int col) {
        return board[row][col];
    }

    private boolean outOfBounds(int row, int col) {
        return row < 0 || row >= size || col < 0 || col >= size;
    }

    @Override
    public void setPiece(int row, int col, PieceColour piece) {
    if (piece == null) {
        throw new IllegalArgumentException("Cannot place null piece");
    }
    if (outOfBounds(row, col)) {
        throw new IllegalArgumentException("Position out of bounds: (" + row + "," + col + ")");
    }
    board[row][col] = piece;
}

    @Override
    public Grid copy() {
        return new GridImpl(this);
    }

    @Override
    //return string for grid (w b or .)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                PieceColour piece = board[row][col];
                if (piece == PieceColour.WHITE) {
                    sb.append("W");
                } else if (piece == PieceColour.BLACK) {
                    sb.append("B");
                } else {
                    sb.append(".");
                }
        }
            sb.append("\n");
        }
        return sb.toString();
    }
}
