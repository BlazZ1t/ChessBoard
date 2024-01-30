public abstract class Piece {
    protected Color color;

    Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }


    public abstract boolean isMovePossible(String targetCoordinate, Board board);
}
