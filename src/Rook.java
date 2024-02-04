public class Rook extends Piece {
    private Position position;

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
    public boolean checkCastlePossibility(Board board){
        if (canCastle){
            switch (position.stringValue()){
                case "H1" -> {
                    return board.getPieceViaPosition("F1") == null && board.getPieceViaPosition("G1") == null;
                }
                case "A1" -> {
                    return board.getPieceViaPosition("B1") == null && board.getPieceViaPosition("C1") == null
                            && board.getPieceViaPosition("D1") == null;
                }
                case "H8" -> {
                    return board.getPieceViaPosition("F8") == null && board.getPieceViaPosition("G8") == null;
                }
                case "A8" -> {
                    return board.getPieceViaPosition("B8") == null && board.getPieceViaPosition("C8") == null
                            && board.getPieceViaPosition("D8") == null;
                }
            }
        }

        return false;
    }
}
