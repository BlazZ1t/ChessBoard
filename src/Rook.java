import java.util.ArrayList;
import java.util.Map;

public class Rook extends Piece {
    private Position position;
    private boolean canCastle = true;

    Rook(Position position, Color color) {
        super(color);
        this.position = position;
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {
        int currentCoordinateLetter = position.stringValue().charAt(0);
        int currentCoordinateNumber = position.stringValue().charAt(1);
        int targetCoordinateLetter = targetCoordinate.charAt(0);
        int targetCoordinateNumber = targetCoordinate.charAt(1);
        if ((currentCoordinateLetter == targetCoordinateLetter) && Math.abs(currentCoordinateNumber - targetCoordinateNumber) >= 1) {
            boolean flag = true;
            if (targetCoordinateNumber > currentCoordinateNumber) {
                for (int i = currentCoordinateNumber + 1; i < targetCoordinateNumber; i++) {
                    String positionCheck = (char) currentCoordinateLetter + String.valueOf((char) i);
                    if (board.getPieceViaPosition(positionCheck) != null) {
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    Piece targetPiece = board.getPieceViaPosition(targetCoordinate);
                    if (targetPiece != null){
                        return targetPiece.getColor() != this.color;
                    } else{
                        return true;
                    }
                }
            } else {
                for (int i = currentCoordinateNumber - 1; i > targetCoordinateNumber; i--){
                    String positionCheck = (char) currentCoordinateLetter + String.valueOf((char) i);
                    if (board.getPieceViaPosition(positionCheck) != null) {
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    Piece targetPiece = board.getPieceViaPosition(targetCoordinate);
                    if (targetPiece != null){
                        return targetPiece.getColor() != this.color;
                    } else {
                        return true;
                    }
                }
            }
        }
        if (Math.abs(currentCoordinateLetter - targetCoordinateLetter) >= 1 && currentCoordinateNumber == targetCoordinateNumber){
            boolean flag = true;
            if (targetCoordinateLetter > currentCoordinateLetter){
                for (int i = currentCoordinateLetter + 1; i < targetCoordinateLetter; i++){
                    String positionCheck = (char) currentCoordinateLetter + String.valueOf((char) i);
                    if (board.getPieceViaPosition(positionCheck) != null) {
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    Piece targetPiece = board.getPieceViaPosition(targetCoordinate);
                    if (targetPiece != null){
                        return targetPiece.getColor() != this.color;
                    } else {
                        return true;
                    }
                }
            } else {
                for (int i = currentCoordinateLetter - 1; i > targetCoordinateLetter; i--){
                    String positionCheck = (char) currentCoordinateLetter + String.valueOf((char) i);
                    if (board.getPieceViaPosition(positionCheck) != null) {
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    Piece targetPiece = board.getPieceViaPosition(targetCoordinate);
                    if (targetPiece != null){
                        return targetPiece.getColor() != this.color;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    @Override
    public boolean checkCastlePossibility(Board board, String castleType, Movement movement){
        if (canCastle){
            switch (position.stringValue()){
                case "H1" -> {
                    if (board.getPieceViaPosition("F1") == null && board.getPieceViaPosition("G1") == null){
                        board.addPiece(new Position('F', 1), "King", "W");
                        board.addPiece(new Position('G', 1), "King", "W");
                        for (Map.Entry<Position, Piece> entry : board.pieces.entrySet()){
                            Piece value = entry.getValue();
                            if (value != null){
                                if (value.getColor() != this.color){
                                    if (value.doesAttackKing(board)){
                                        board.removePiece("F1");
                                        board.removePiece("G1");
                                        return false;
                                    }
                                }
                            }
                        }
                        board.removePiece("F1");
                        board.removePiece("G1");
                        return true;
                    }
                }
                case "A1" -> {
                    if (board.getPieceViaPosition("B1") == null && board.getPieceViaPosition("C1") == null
                            && board.getPieceViaPosition("D1") == null){
                        board.addPiece(new Position('B', 1), "King", "W");
                        board.addPiece(new Position('C', 1), "King", "W");
                        board.addPiece(new Position('D', 1), "King", "W");
                        for (Map.Entry<Position, Piece> entry : board.pieces.entrySet()){
                            Piece value = entry.getValue();
                            if (value != null){
                                if (value.getColor() != this.color){
                                    if (value.doesAttackKing(board)){
                                        board.removePiece("B1");
                                        board.removePiece("C1");
                                        board.removePiece("D1");
                                        return false;
                                    }
                                }
                            }
                        }
                        board.removePiece("B1");
                        board.removePiece("C1");
                        board.removePiece("D1");
                        return true;
                    }
                }
                case "H8" -> {
                    if (board.getPieceViaPosition("F8") == null && board.getPieceViaPosition("G8") == null){
                        board.addPiece(new Position('F', 8), "King", "B");
                        board.addPiece(new Position('G', 8), "King", "B");
                        for (Map.Entry<Position, Piece> entry : board.pieces.entrySet()){
                            Piece value = entry.getValue();
                            if (value != null){
                                if (value.getColor() != this.color){
                                    if (value.doesAttackKing(board)){
                                        board.removePiece("F8");
                                        board.removePiece("G8");
                                        return false;
                                    }
                                }
                            }
                        }
                        board.removePiece("F8");
                        board.removePiece("G8");
                        return true;
                    }
                }
                case "A8" -> {
                    if (board.getPieceViaPosition("B8") == null && board.getPieceViaPosition("C8") == null
                            && board.getPieceViaPosition("D8") == null){
                        board.addPiece(new Position('B', 8), "King", "B");
                        board.addPiece(new Position('C', 8), "King", "B");
                        board.addPiece(new Position('D', 8), "King", "B");
                        for (Map.Entry<Position, Piece> entry : board.pieces.entrySet()){
                            Piece value = entry.getValue();
                            if (value != null){
                                if (value.getColor() != this.color){
                                    if (value.doesAttackKing(board)){
                                        board.removePiece("B8");
                                        board.removePiece("C8");
                                        board.removePiece("D8");
                                        return false;
                                    }
                                }
                            }
                        }
                        board.removePiece("B8");
                        board.removePiece("C8");
                        board.removePiece("D8");
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean doesAttackKing(Board board) {
        int positionLetter = position.stringValue().charAt(0);
        int positionNumber = position.stringValue().charAt(1);
        boolean flag;
        //Up
        for (int i = positionNumber + 1; i <= '8'; i++){
            Piece possibleTarget = board.getPieceViaPosition((char) positionLetter + String.valueOf((char) i));
            if (possibleTarget != null){
                flag = possibleTarget.getClass().getSimpleName().equals("King") && !possibleTarget.getColor().equals(color);
                if (flag){
                    return true;
                } else {
                    break;
                }
            }
        }
        //Down
        for (int i = positionNumber - 1; i >= '1'; i--){
            Piece possibleTarget = board.getPieceViaPosition((char) positionLetter + String.valueOf((char) i));
            if (possibleTarget != null){
                flag = possibleTarget.getClass().getSimpleName().equals("King") && !possibleTarget.getColor().equals(color);
                if (flag) {
                    return true;
                } else {
                    break;
                }
            }
        }
        //Right
        for (int i = positionLetter + 1; i <= 'H'; i++){
            Piece possibleTarget = board.getPieceViaPosition((char) i + String.valueOf((char) positionNumber));
            if (possibleTarget != null){
                flag = possibleTarget.getClass().getSimpleName().equals("King") && !possibleTarget.getColor().equals(color);
                if (flag) {
                    return true;
                } else {
                    break;
                }
            }
        }
        //Left
        for (int i = positionLetter - 1; i >= 'A'; i--){
            Piece possibleTarget = board.getPieceViaPosition((char) i + String.valueOf((char) positionNumber));
            if (possibleTarget != null){
                flag =  possibleTarget.getClass().getSimpleName().equals("King") && !possibleTarget.getColor().equals(color);
                if (flag) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> possibleMovesSet(Movement movement, Board board) {
        ArrayList<String> moveSet = new ArrayList<>();
        char positionLetter = position.stringValue().charAt(0);
        char positionNumber = position.stringValue().charAt(1);
        //Up
        for (int i = positionNumber + 1; i <= '8'; i++){
            moveSet.add(String.valueOf(positionLetter) + (char) i);
        }
        //Down
        for (int i = positionNumber - 1; i >= '1'; i--){
            moveSet.add(String.valueOf(positionLetter) + (char) i);
        }
        //Right
        for (int i = positionLetter + 1; i <= 'H'; i++){
            moveSet.add((char) i + String.valueOf(positionNumber));
        }
        //Left
        for (int i = positionLetter - 1; i >= 'A'; i--){
            moveSet.add((char) i + String.valueOf(positionNumber));
        }
        int counter = 0;
        for (int i = 0; i < moveSet.size(); i++){
            if (!movement.checkPosition(moveSet.get(i)) || !isMovePossible(moveSet.get(i), board)){
                moveSet.set(i, null);
                counter++;
            }
        }
        for (int i = 0; i < counter; i++){
            moveSet.remove(null);
        }
        return moveSet;

    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void setCanCastle(boolean canCastle) {
        this.canCastle = canCastle;
    }
}
