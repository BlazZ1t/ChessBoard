public class Bishop extends Piece {
    Position position;
    Bishop(Position position, Color color) {
        super(color);
        this.position = position;
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {
        int currentCoordinateLetter = position.stringValue().charAt(0);
        int currentCoordinateNumber = position.stringValue().charAt(1);
        int targetCoordinateLetter = targetCoordinate.charAt(0);
        int targetCoordinateNumber = targetCoordinate.charAt(1);
        if (targetCoordinateLetter > currentCoordinateLetter){
            //Right up
            if (targetCoordinateNumber > currentCoordinateNumber){
                boolean flag = true;
                int j = currentCoordinateLetter + 1;
                for (int i = currentCoordinateNumber + 1; i < targetCoordinateNumber; i++){
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
                for (int i = currentCoordinateNumber - 1; i > targetCoordinateNumber; i--){
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
            //Left up
            if (targetCoordinateNumber > currentCoordinateNumber){
                boolean flag = true;
                int j = currentCoordinateLetter - 1;
                for (int i = currentCoordinateNumber + 1; i < targetCoordinateNumber; i++){
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
                for (int i = currentCoordinateNumber - 1; i > targetCoordinateNumber; i--){
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
}
