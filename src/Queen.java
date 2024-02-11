public class Queen extends Piece {
    private Position position;
    Queen(Position position, Color color) {
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
                for (int i = currentCoordinateNumber + 1; i <= targetCoordinateNumber; i++) {
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
                for (int i = currentCoordinateNumber - 1; i >= targetCoordinateNumber; i--){
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
        } else if (Math.abs(currentCoordinateLetter - targetCoordinateLetter) >= 1 && currentCoordinateNumber == targetCoordinateNumber){
            boolean flag = true;
            if (targetCoordinateLetter > currentCoordinateLetter){
                for (int i = currentCoordinateLetter + 1; i <= targetCoordinateLetter; i++){
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
                for (int i = currentCoordinateLetter - 1; i >= targetCoordinateLetter; i--){
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
            //Diagonal movement
        } else if (targetCoordinateLetter > currentCoordinateLetter){
            if (Math.abs(targetCoordinateLetter - currentCoordinateLetter) != Math.abs(targetCoordinateNumber - currentCoordinateNumber)){
                return false;
            }
            //Right up
            if (targetCoordinateNumber > currentCoordinateNumber){
                boolean flag = true;
                int j = currentCoordinateLetter + 1;
                for (int i = currentCoordinateNumber + 1; i <= targetCoordinateNumber; i++){
                    if (j >= targetCoordinateLetter){
                        break;
                    }
                    Piece positionCheck = board.getPieceViaPosition((char) j + String.valueOf((char) i));
                    if (positionCheck != null){
                        flag = false;
                        break;
                    }
                    j++;
                }
                if (flag){
                    Piece targetPiece = board.getPieceViaPosition(targetCoordinate);
                    if (targetPiece != null){
                        return targetPiece.getColor() != this.color;
                    } else {
                        return true;
                    }
                }
                //Right down
            } else {
                boolean flag = true;
                int j = currentCoordinateLetter + 1;
                for (int i = currentCoordinateNumber - 1; i >= targetCoordinateNumber; i--){
                    if (j >= targetCoordinateLetter){
                        break;
                    }
                    Piece positionCheck = board.getPieceViaPosition((char) j + String.valueOf((char) i));
                    if (positionCheck != null){
                        flag = false;
                        break;
                    }
                    j++;
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
        } else if (targetCoordinateLetter < currentCoordinateLetter){
            if (Math.abs(targetCoordinateLetter - currentCoordinateLetter) != Math.abs(targetCoordinateNumber - currentCoordinateNumber)){
                return false;
            }
            //Left up
            if (targetCoordinateNumber > currentCoordinateNumber){
                boolean flag = true;
                int j = currentCoordinateLetter - 1;
                for (int i = currentCoordinateNumber + 1; i <= targetCoordinateNumber; i++){
                    if (j <= targetCoordinateLetter){
                        break;
                    }
                    Piece positionCheck = board.getPieceViaPosition((char) j + String.valueOf((char) i));
                    if (positionCheck != null){
                        flag = false;
                        break;
                    }
                    j--;
                }
                if (flag){
                    Piece targetPiece = board.getPieceViaPosition(targetCoordinate);
                    if (targetPiece != null){
                        return targetPiece.getColor() != this.color;
                    } else {
                        return true;
                    }
                }
                //Left down
            } else {
                boolean flag = true;
                int j = currentCoordinateLetter - 1;
                for (int i = currentCoordinateNumber - 1; i >= targetCoordinateNumber; i--){
                    if (j <= targetCoordinateLetter){
                        break;
                    }
                    Piece positionCheck = board.getPieceViaPosition((char) j + String.valueOf((char) i));
                    if (positionCheck != null){
                        flag = false;
                        break;
                    }
                    j--;
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
    public boolean doesAttackKing(Board board) {
        int positionLetter = position.stringValue().charAt(0);
        int positionNumber = position.stringValue().charAt(1);
        boolean flag;
        //Up
        for (int i = positionNumber; i <= '8'; i++){
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
        for (int i = positionNumber; i >= '1'; i--){
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
        for (int i = positionLetter; i <= 'H'; i++){
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
        for (int i = positionLetter; i >= 'A'; i--){
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
        //Up Right
        int numberChange = positionNumber;
        for (int i = positionLetter; i <= 'H'; i++){
            Piece possibleTarget = board.getPieceViaPosition((char) i + String.valueOf((char) numberChange));
            if (possibleTarget != null){
                flag =  possibleTarget.getClass().getSimpleName().equals("King") && possibleTarget.getColor() != color;
                if (flag) {
                    return true;
                } else {
                    break;
                }
            }
            numberChange++;
        }
        //Up Left
        numberChange = positionNumber;
        for (int i = positionLetter; i >= 'A'; i--){
            Piece possibleTarget = board.getPieceViaPosition((char) i + String.valueOf((char) numberChange));
            if (possibleTarget != null){
                flag =  possibleTarget.getClass().getSimpleName().equals("King") && possibleTarget.getColor() != color;
                if (flag){
                    return true;
                } else {
                    break;
                }
            }
            numberChange++;
        }
        //Down Right
        numberChange = positionNumber;
        for (int i = positionLetter; i <= 'H'; i++){
            Piece possibleTarget = board.getPieceViaPosition((char) i + String.valueOf((char) numberChange));
            if (possibleTarget != null){
                flag =  possibleTarget.getClass().getSimpleName().equals("King") && possibleTarget.getColor() != color;
                if (flag) {
                    return true;
                } else {
                    break;
                }
            }
            numberChange--;
        }
        //Down Left
        for (int i = positionLetter; i >= 'A'; i--){
            Piece possibleTarget = board.getPieceViaPosition((char) i + String.valueOf((char) numberChange));
            if (possibleTarget != null){
                flag = possibleTarget.getClass().getSimpleName().equals("King") && possibleTarget.getColor() != color;
                if (flag) {
                    return true;
                } else {
                    break;
                }
            }
            numberChange--;
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
