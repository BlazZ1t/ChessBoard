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

    public void moveCastle(Color moveColor, String castleType, Board board) {
        if (moveColor.equals(Color.WHITE)){
            Piece king = board.getPieceViaPosition("E1");
            if (castleType.equals("0-0")){
                Piece rook = board.getPieceViaPosition("H1");
                if (rook != null && king != null){
                   if (rook.checkCastlePossibility(board, castleType, this) && king.checkCastlePossibility(board, castleType, this)){
                       board.removePiece("E1");
                       board.removePiece("H1");
                       king.setPosition(new Position('G', 1));
                       rook.setPosition(new Position('F', 1));
                       board.addPieceWithEntity("G1", king);
                       board.addPieceWithEntity("F1", rook);
                   }
                }
            } else {
                Piece rook = board.getPieceViaPosition("A1");
                if (rook != null && king != null){
                    if (rook.checkCastlePossibility(board, castleType, this) && king.checkCastlePossibility(board, castleType, this)){
                        board.removePiece("E1");
                        board.removePiece("A1");
                        king.setPosition(new Position('C', 1));
                        rook.setPosition(new Position('D', 1));
                        board.addPieceWithEntity("C1", king);
                        board.addPieceWithEntity("D1", rook);
                    }
                }
            }
        } else {
            Piece king = board.getPieceViaPosition("E8");
            if (castleType.equals("0-0")){
                Piece rook = board.getPieceViaPosition("H8");
                if (rook != null && king != null){
                    if (rook.checkCastlePossibility(board, castleType, this) && king.checkCastlePossibility(board, castleType, this)){
                        board.removePiece("E8");
                        board.removePiece("H8");
                        king.setPosition(new Position('G', 8));
                        rook.setPosition(new Position('H', 8));
                        board.addPieceWithEntity("G8", king);
                        board.addPieceWithEntity("H8", rook);
                    }
                }
            } else {
                Piece rook = board.getPieceViaPosition("A8");
                if (rook != null && king != null){
                    if (rook.checkCastlePossibility(board, castleType, this) && king.checkCastlePossibility(board, castleType, this)){
                        board.removePiece("E8");
                        board.removePiece("A8");
                        king.setPosition(new Position('C', 8));
                        rook.setPosition(new Position('D', 8));
                        board.addPieceWithEntity("C8", king);
                        board.addPieceWithEntity("D8", rook);
                    }
                }
            }
        }
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

    public void makeMove(String currentCoordinate, String targetCoordinate, Board board){
        Piece movingPiece = board.getPieceViaPosition(currentCoordinate);
        Piece possibleTargetPiece = board.getPieceViaPosition(targetCoordinate);
        board.removeJustDoubleMovedState();
        if (movingPiece.getClass().getSimpleName().equals("Pawn")){
            movingPiece.setHasMoved(true);
            if (Math.abs(targetCoordinate.charAt(1) - movingPiece.position.stringValue().charAt(1)) == 2){
                board.makePawnDoubleMove(movingPiece.position.stringValue());
            }
        }
        if (possibleTargetPiece != null){
            board.removePiece(targetCoordinate);
            movingPiece.setPosition(new Position(targetCoordinate.charAt(0), Integer.parseInt(String.valueOf(targetCoordinate.charAt(1)))));
            board.removePiece(currentCoordinate);
            board.addPieceWithEntity(targetCoordinate, movingPiece);
        } else {
            movingPiece.setPosition(new Position(targetCoordinate.charAt(0), Integer.parseInt(String.valueOf(targetCoordinate.charAt(1)))));
            board.removePiece(currentCoordinate);
            board.addPieceWithEntity(targetCoordinate, movingPiece);
        }

        if (movingPiece.getClass().getSimpleName().equals("Rook") || movingPiece.getClass().getSimpleName().equals("King")){
            movingPiece.setCanCastle(false);
        }
    }

    public boolean isPlayerMated(){

        return false;
    }

    public void endGame(){
        gameInProgress = false;
    }
}
