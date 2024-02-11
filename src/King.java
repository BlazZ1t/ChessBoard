public class King extends Piece {
    private Position position;

    King(Position position, Color color) {
        super(color);
        this.position = position;
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {
        return false;
    }

    @Override
    public boolean checkCastlePossibility(Board board) {
        return canCastle;
    }

    @Override
    public boolean doesAttackKing(Board board) {
        return false;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
