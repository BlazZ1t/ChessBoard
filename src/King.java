public class King extends Piece {
    private boolean canCastle = true;

    King(Position position, Color color) {
        super(color);
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {


        return false;
    }
}
