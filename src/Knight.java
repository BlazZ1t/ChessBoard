public class Knight extends Piece{
    Knight(Position position, Color color) {
        super(color);
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {
        return true;
    }
}
