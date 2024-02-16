import java.util.ArrayList;

public class King extends Piece {
    private Position position;
    private boolean canCastle;

    King(Position position, Color color) {
        super(color);
        this.position = position;
        this.canCastle = true;
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {
        int currentCoordinateLetter = position.stringValue().charAt(0);
        int currentCoordinateNumber = position.stringValue().charAt(1);
        int targetCoordinateLetter = targetCoordinate.charAt(0);
        int targetCoordinateNumber = targetCoordinate.charAt(1);
        if (targetCoordinateLetter == currentCoordinateLetter){
            return Math.abs(targetCoordinateNumber - currentCoordinateNumber) == 1;
        } else if (Math.abs(targetCoordinateLetter - currentCoordinateLetter) == 1){
            return Math.abs(targetCoordinateNumber - currentCoordinateNumber) <= 1;
        }
        return false;
    }

    @Override
    public boolean checkCastlePossibility(Board board, String castleType, Movement movement) {
        return canCastle;
    }

    @Override
    public boolean doesAttackKing(Board board) {
        int positionLetter = position.stringValue().charAt(0);
        int positionNumber = position.stringValue().charAt(1);
        ArrayList<String> targetTiles = new ArrayList<>();
        targetTiles.add((char) (positionLetter + 1) + String.valueOf((char) positionNumber));
        targetTiles.add((char) (positionLetter + 1) + String.valueOf((char) (positionNumber + 1)));
        targetTiles.add((char) (positionLetter) + String.valueOf((char) (positionNumber + 1)));
        targetTiles.add((char) (positionLetter - 1) + String.valueOf((char) (positionNumber + 1)));
        targetTiles.add((char) (positionLetter - 1) + String.valueOf((char) positionNumber));
        targetTiles.add((char) (positionLetter - 1) + String.valueOf((char) (positionNumber - 1)));
        targetTiles.add((char) (positionLetter) + String.valueOf((char) (positionNumber - 1)));
        targetTiles.add((char) (positionLetter + 1) + String.valueOf((char) (positionNumber - 1)));
        for (int i = 0; i < targetTiles.size(); i++){
            Piece possibleTarget = board.getPieceViaPosition(targetTiles.get(i));
            if (possibleTarget != null){
                if (possibleTarget.getClass().getSimpleName().equals("King") && possibleTarget.getColor() != color){
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public ArrayList<String> possibleMovesSet(Movement movement, Board board) {
        ArrayList<String> movesSet = new ArrayList<>();
        char currentLetter = position.stringValue().charAt(0);
        char currentNumber = position.stringValue().charAt(1);
        int counter = 0;
        movesSet.add(currentLetter + String.valueOf((char) (currentNumber + 1)));
        movesSet.add(currentLetter + String.valueOf((char) (currentNumber - 1)));
        movesSet.add(String.valueOf((char) (currentLetter + 1)) + currentNumber);
        movesSet.add(String.valueOf((char) (currentLetter - 1)) + currentNumber);
        movesSet.add(String.valueOf((char) (currentLetter + 1)) + ((char)(currentNumber + 1)));
        movesSet.add(String.valueOf((char)(currentLetter + 1)) + ((char)(currentNumber - 1)));
        movesSet.add(String.valueOf((char)(currentLetter - 1)) + ((char)(currentNumber + 1)));
        movesSet.add(String.valueOf((char)(currentLetter - 1)) + ((char)(currentNumber - 1)));
        for (int i = 0; i < 8; i++){
            if (!movement.checkPosition(movesSet.get(i))){
                movesSet.set(i, null);
                counter++;
            }
        }
        for (int i = 0; i < counter; i++){
            movesSet.remove(null);
        }
        return movesSet;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setCanCastle(boolean canCastle) {
        this.canCastle = canCastle;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}
