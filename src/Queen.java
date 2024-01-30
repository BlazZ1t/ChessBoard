public class Queen extends Piece {
    Queen(Position position, Color color) {
        super(color);
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {
        return true;
    }
}
