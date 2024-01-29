public class Pawn extends Piece{
    private boolean hasMoved = false;
    private boolean justDoubleMoved = false;
    private Position position;
    Pawn(Position position, Color color) {
        super(color);
        this.position = position;
    }
    @Override
    public boolean isMovePossible(String targetCoordinate, Board board){
        int targetCoordinateNumber = Integer.parseInt(String.valueOf(targetCoordinate.charAt(1)));
        int currentCoordinateNumber = Integer.parseInt(String.valueOf(position.stringValue().charAt(1)));
        if (targetCoordinate.charAt(0) - 1 == position.stringValue().charAt(0) ||
                targetCoordinate.charAt(0) + 1 == position.stringValue().charAt(0)){
            if (color.equals(Color.WHITE)){

            }
        }
        if (color.equals(Color.WHITE)){
            if (!hasMoved){
                if (targetCoordinateNumber - 2 == currentCoordinateNumber ||
                        targetCoordinateNumber - 1 == currentCoordinateNumber){
                    if (targetCoordinate.charAt(0) == position.stringValue().charAt(0)) {
                        boolean flag = true;
                        for (int i = currentCoordinateNumber + 1; i <= targetCoordinateNumber; i++){
                            if (board.findPieceTypeViaPosition(targetCoordinate.charAt(0) + String.valueOf(i)) != null){
                                flag = false;
                            }
                        }
                        return flag;
                    }
                }
            } else {
                if (targetCoordinateNumber - 1 == currentCoordinateNumber){
                    if (targetCoordinate.charAt(0) == position.stringValue().charAt(0)){
                        return board.findPieceTypeViaPosition(targetCoordinate) == null;
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
                            if (board.findPieceTypeViaPosition(targetCoordinate.charAt(0) + String.valueOf(i)) != null) {
                                flag = false;
                            }
                        }
                        return flag;
                    }
                }
            } else {
                if (targetCoordinateNumber + 1 == currentCoordinateNumber) {
                    if (targetCoordinate.charAt(0) == position.stringValue().charAt(0)) {
                        return board.findPieceTypeViaPosition(targetCoordinate) == null;
                    }
                }
            }
        }
        return false;
    }


}
