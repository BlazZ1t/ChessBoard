import java.util.ArrayList;

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

    @Override
    public boolean doesAttackKing(Board board) {
        int positionLetter = position.stringValue().charAt(0);
        int positionNumber = position.stringValue().charAt(1);
        ArrayList<String> attackingTiles = new ArrayList<>();
        attackingTiles.add((char) (positionLetter + 1) + String.valueOf((char) (positionNumber + 2)));
        attackingTiles.add((char) (positionLetter + 2) + String.valueOf((char) (positionNumber + 1)));
        attackingTiles.add((char) (positionLetter - 1) + String.valueOf((char) (positionNumber + 2)));
        attackingTiles.add((char) (positionLetter - 2) + String.valueOf((char) (positionNumber + 1)));
        attackingTiles.add((char) (positionLetter + 2) + String.valueOf((char) (positionNumber - 1)));
        attackingTiles.add((char) (positionLetter + 2) + String.valueOf((char) (positionNumber - 1)));
        attackingTiles.add((char) (positionLetter - 2) + String.valueOf((char) (positionNumber - 1)));
        attackingTiles.add((char) (positionLetter - 1) + String.valueOf((char) (positionNumber - 2)));
        for (int i = 0; i < attackingTiles.size(); i++){
            Piece possiblePiece = board.getPieceViaPosition(attackingTiles.get(i));
            if (possiblePiece != null){
                if (possiblePiece.getClass().getSimpleName().equals("King") && possiblePiece.getColor() != color){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}
