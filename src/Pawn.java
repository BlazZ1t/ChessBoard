public class Pawn extends Piece {
    private boolean hasMoved = false;
    private Position position;

    Pawn(Position position, Color color) {
        super(color);
        this.position = position;
    }

    @Override
    public boolean isMovePossible(String targetCoordinate, Board board) {
        int targetCoordinateNumber = Integer.parseInt(String.valueOf(targetCoordinate.charAt(1)));
        int currentCoordinateNumber = Integer.parseInt(String.valueOf(position.stringValue().charAt(1)));
        char currentCoordinateLetter = position.stringValue().charAt(0);
        //Capture move
        if (targetCoordinate.charAt(0) - 1 == position.stringValue().charAt(0) ||
                targetCoordinate.charAt(0) + 1 == position.stringValue().charAt(0)) {
            //Checks for white pawn
            if (color.equals(Color.WHITE)) {
                if (targetCoordinateNumber - currentCoordinateNumber == 1){
                    Piece targetPiece = board.getPieceViaPosition(targetCoordinate);
                    if (targetPiece != null){
                        if (targetPiece.getColor().equals(Color.BLACK)){
                            if (!targetPiece.getClass().getSimpleName().equals("King")){
                                return true;
                            }
                        }
                        //Check for the French move
                    } else {
                        Piece enPassantCapture = board.getPieceViaPosition((char) (currentCoordinateLetter + 1) + String.valueOf(currentCoordinateNumber));
                        if (enPassantCapture != null) {
                            if (enPassantCapture.getClass().getSimpleName().equals("Pawn")) {
                                if (enPassantCapture.getColor().equals(Color.BLACK)) {
                                    if (enPassantCapture.justDoubleMoved) {
                                        return true;
                                    }
                                }
                            }
                        } else {
                            enPassantCapture = board.getPieceViaPosition((char) (currentCoordinateLetter - 1) + String.valueOf(currentCoordinateNumber));
                            if (enPassantCapture != null){
                                if (enPassantCapture.getClass().getSimpleName().equals("Pawn")) {
                                    if (enPassantCapture.getColor().equals(Color.BLACK)) {
                                        if (enPassantCapture.justDoubleMoved) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            //Checks for black pawn
            } else {
                if (currentCoordinateNumber - targetCoordinateNumber == 1){
                    Piece targetPiece = board.getPieceViaPosition(targetCoordinate);
                    if (targetPiece != null){
                        if (targetPiece.getColor().equals(Color.WHITE)){
                            if (!targetPiece.getClass().getSimpleName().equals("King")){
                                return true;
                            }
                        }
                    }  else {
                        Piece enPassantCapture = board.getPieceViaPosition((char) (currentCoordinateLetter + 1) + String.valueOf(currentCoordinateNumber));
                        if (enPassantCapture != null) {
                            if (enPassantCapture.getClass().getSimpleName().equals("Pawn")) {
                                if (enPassantCapture.getColor().equals(Color.WHITE)) {
                                    if (enPassantCapture.justDoubleMoved) {
                                        return true;
                                    }
                                }
                            }
                        } else {
                            enPassantCapture = board.getPieceViaPosition((char) (currentCoordinateLetter - 1) + String.valueOf(currentCoordinateNumber));
                            if (enPassantCapture != null){
                                if (enPassantCapture.getClass().getSimpleName().equals("Pawn")) {
                                    if (enPassantCapture.getColor().equals(Color.WHITE)) {
                                        if (enPassantCapture.justDoubleMoved) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //Basic move (double or single)
        if (color.equals(Color.WHITE)) {
            //Check if double move is possible
            if (!hasMoved) {
                //Check if target coordinate doesn't exceed limit
                if (targetCoordinateNumber - 2 == currentCoordinateNumber ||
                        targetCoordinateNumber - 1 == currentCoordinateNumber) {
                    if (targetCoordinate.charAt(0) == position.stringValue().charAt(0)) {
                        //Check  if there is a piece blocking the way
                        boolean flag = true;
                        for (int i = currentCoordinateNumber + 1; i <= targetCoordinateNumber; i++) {
                            if (board.getPieceViaPosition(targetCoordinate.charAt(0) + String.valueOf(i)) != null) {
                                flag = false;
                            }
                        }
                        return flag;
                    }
                }
            } else {
                if (targetCoordinateNumber - 1 == currentCoordinateNumber) {
                    if (targetCoordinate.charAt(0) == position.stringValue().charAt(0)) {
                        return board.getPieceViaPosition(targetCoordinate) == null;
                    }
                }
            }
        } else if (color.equals(Color.BLACK)) {
            if (!hasMoved) {
                if (targetCoordinateNumber + 2 == currentCoordinateNumber ||
                        targetCoordinateNumber + 1 == currentCoordinateNumber) {
                    if (targetCoordinate.charAt(0) == position.stringValue().charAt(0)) {
                        boolean flag = true;
                        for (int i = currentCoordinateNumber - 1; i <= targetCoordinateNumber; i++) {
                            if (board.getPieceViaPosition(targetCoordinate.charAt(0) + String.valueOf(i)) != null) {
                                flag = false;
                            }
                        }
                        return flag;
                    }
                }
            } else {
                if (targetCoordinateNumber + 1 == currentCoordinateNumber) {
                    if (targetCoordinate.charAt(0) == position.stringValue().charAt(0)) {
                        return board.getPieceViaPosition(targetCoordinate) == null;
                    }
                }
            }
        }
        return false;
    }
}
