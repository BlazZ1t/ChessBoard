public class King extends Piece {

    King(Position position, Color color) {
        super(color);
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {
        return false;
    }

    @Override
    public boolean checkCastlePossibility(Board board) {
        return canCastle;
    }
}
