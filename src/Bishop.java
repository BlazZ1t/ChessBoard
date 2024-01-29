public class Bishop extends Piece{
    Bishop(Position position, Color color) {
        super(color);
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {
        return true;
    }
}
