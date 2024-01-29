public class Rook extends Piece{
    private boolean canCastle = true;
    Rook(Position position, Color color) {
        super(color);
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {
        return true;
    }
}
