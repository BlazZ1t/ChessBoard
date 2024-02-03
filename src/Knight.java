public class Knight extends Piece {
    private Position position;
    Knight(Position position, Color color) {
        super(color);
        this.position = position;
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {
        int targetCoordinateLetter = targetCoordinate.charAt(0);
        int targetCoordinateNumber = targetCoordinate.charAt(1);
        int currentCoordinateLetter = position.stringValue().charAt(0);
        int currentCoordinateNumber = position.stringValue().charAt(1);
        if (Math.abs(currentCoordinateNumber - targetCoordinateNumber) <= 2){
            if (Math.abs(currentCoordinateNumber - targetCoordinateNumber) == 2){
                if (Math.abs(targetCoordinateLetter - currentCoordinateLetter) == 1){
                    return checkCapturePossibility(targetCoordinate, board);
                }
            } else {
                if (Math.abs(targetCoordinateLetter - currentCoordinateLetter) == 2){
                    return checkCapturePossibility(targetCoordinate, board);
                }
            }

        }
        return false;
    }

    private boolean checkCapturePossibility(String targetCoordinate, Board board) {
        Piece targetPiece = board.getPieceViaPosition(targetCoordinate);
        if (targetPiece != null){
            if (targetPiece.getColor() != this.color){
                return !targetPiece.getClass().getSimpleName().equals("King");
            }
        } else {
            return true;
        }
        return false;
    }
}
