import java.util.Map;

public class Movement {
    private boolean gameInProgress;

    public Movement() {
        this.gameInProgress = true;
    }

    public boolean checkPosition(String position) {
        if (position.charAt(0) <= 72 && position.charAt(0) >= 65) {
            return position.charAt(1) >= '1' && position.charAt(1) <= '8';
        }
        return false;
    }

    public boolean checkTransformation(String targetPiece) {
        return switch (targetPiece) {
            case "Q", "R", "B", "N" -> true;
            default -> false;
        };
    }

    public void moveCastle() {

    }


    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public boolean moveCreatesCheck(Board board, Color currentColor, Piece piece, String targetPosition) {
        board.removePiece(piece.getPosition().stringValue());
        Position savedPosition = piece.getPosition();
        for (Map.Entry<Position, Piece> entry : board.pieces.entrySet()) {
            Piece value = entry.getValue();
            if (value.getColor().equals(currentColor)) {
                if (value.doesAttackKing(board)) {
                    return true;
                }
            }
        }
        piece.setPosition(new Position(targetPosition.charAt(0), Integer.parseInt(String.valueOf(targetPosition.charAt(1)))));
        if (piece.doesAttackKing(board)) {
            board.addPieceWithEntity(savedPosition.stringValue(), piece);
            piece.setPosition(savedPosition);
            return true;
        } else {
            board.addPieceWithEntity(savedPosition.stringValue(), piece);
            piece.setPosition(savedPosition);
        }
        return false;
    }

    public boolean moveCreatesSelfCheck(Board board, Color currentColor, Piece piece, String targetPosition){
        board.removePiece(piece.getPosition().stringValue());
        Position savedPosition = piece.getPosition();
        Piece possibleTargetPiece = board.getPieceViaPosition(targetPosition);
        if (possibleTargetPiece != null){
            board.removePiece(targetPosition);
            board.addPieceWithEntity(targetPosition, piece);
        } else {
            board.addPieceWithEntity(targetPosition, piece);
        }
        piece.setPosition(new Position(targetPosition.charAt(0), Integer.parseInt(String.valueOf(targetPosition.charAt(1)))));
        for (Map.Entry<Position, Piece> entry : board.pieces.entrySet()) {
            Piece value = entry.getValue();
            if (!value.getColor().equals(currentColor)) {
                if (value.doesAttackKing(board)) {
                    board.removePiece(targetPosition);
                    piece.setPosition(savedPosition);
                    board.addPieceWithEntity(savedPosition.stringValue(), piece);
                    if (possibleTargetPiece != null){
                        board.addPieceWithEntity(targetPosition, possibleTargetPiece);
                    }
                    return true;
                }
            }
        }
        board.removePiece(targetPosition);
        piece.setPosition(savedPosition);
        board.addPieceWithEntity(savedPosition.stringValue(), piece);
        if (possibleTargetPiece != null){
            board.addPieceWithEntity(targetPosition, possibleTargetPiece);
        }

        return false;
    }

    public boolean isPlayerMated(){

        return false;
    }

    public void endGame(){
        gameInProgress = false;
    }
}
