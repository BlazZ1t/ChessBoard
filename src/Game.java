import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        startGame();
    }

    public static void inputs(Board board, Movement movement) {
        Color turnColor = Color.WHITE;
        boolean isPlayerInCheck = false;
        while (movement.isGameInProgress()) {
            if (isPlayerInCheck) {
                System.out.println("You're in check");
            }
            board.displayBoard();
            System.out.println("It's " + turnColor.name() + " move");
            Scanner input = new Scanner(System.in);
            String pieceCoordinate = input.next().toUpperCase();
            switch (pieceCoordinate) {
                case "ADD" -> {
                    String position = input.next();
                    String pieceName = input.next();
                    String color = input.next();
                    board.addPiece(new Position(position.charAt(0), Integer.parseInt(String.valueOf(position.charAt(1)))), pieceName, color);
                    continue;
                }
                case "REMOVE" -> {
                    String position = input.next();
                    board.removePiece(position);
                    continue;
                }
                case "DM" -> {
                    String position = input.next();
                    board.makePawnDoubleMove(position);
                    continue;
                }
                case "CLEAR" -> {
                    board.clearBoard();
                    continue;
                }
                case "STOP" -> System.exit(0);
                case "SWITCH" -> {
                    turnColor = switchColor(turnColor);
                    continue;
                }
            }
            if (pieceCoordinate.equals("0-0-0") || pieceCoordinate.equals("0-0")) {
                if (turnColor.equals(Color.WHITE)) {
                    Piece king = board.getPieceViaPosition("E1");
                    if (king != null) {
                        if (king.checkCastlePossibility(board, pieceCoordinate, movement)) {
                            Piece rook;
                            if (pieceCoordinate.equals("0-0")) {
                                rook = board.getPieceViaPosition("H1");
                            } else {
                                rook = board.getPieceViaPosition("A1");
                            }
                            if (rook != null) {
                                if (rook.checkCastlePossibility(board, pieceCoordinate, movement)) {
                                    System.out.println("Move Possible");
                                    isPlayerInCheck = false;
                                    if (rook.getPosition().stringValue().equals("H1")) {
                                        String targetCoordinate = "F1";
                                        movement.moveCastle(turnColor, pieceCoordinate, board);
                                        if (movement.moveCreatesCheck(board, turnColor, rook, targetCoordinate)) {
                                            isPlayerInCheck = true;
                                        }
                                        turnColor = switchColor(turnColor);
                                    } else {
                                        String targetCoordinate = "D1";
                                        movement.moveCastle(turnColor, pieceCoordinate, board);
                                        if (movement.moveCreatesCheck(board, turnColor, rook, targetCoordinate)) {
                                            isPlayerInCheck = true;
                                        }
                                        turnColor = switchColor(turnColor);
                                    }
                                    continue;
                                } else {
                                    System.out.println("Wrong move");
                                    continue;
                                }
                            }
                        }
                    }
                } else {
                    Piece king = board.getPieceViaPosition("E8");
                    if (king != null) {
                        if (king.checkCastlePossibility(board, pieceCoordinate, movement)) {
                            Piece rook;
                            if (pieceCoordinate.equals("0-0")) {
                                rook = board.getPieceViaPosition("H8");
                            } else {
                                rook = board.getPieceViaPosition("A8");
                            }
                            if (rook != null) {
                                if (rook.checkCastlePossibility(board, pieceCoordinate, movement)) {
                                    isPlayerInCheck = false;
                                    System.out.println("Move Possible");
                                    if (rook.getPosition().stringValue().equals("H8")) {
                                        String targetCoordinate = "F8";
                                        movement.moveCastle(turnColor, pieceCoordinate, board);
                                        if (movement.moveCreatesCheck(board, turnColor, rook, targetCoordinate)) {
                                            isPlayerInCheck = true;
                                        }
                                        turnColor = switchColor(turnColor);
                                    } else {
                                        String targetCoordinate = "D8";
                                        movement.moveCastle(turnColor, pieceCoordinate, board);
                                        if (movement.moveCreatesCheck(board, turnColor, rook, targetCoordinate)) {
                                            isPlayerInCheck = true;
                                        }
                                        turnColor = switchColor(turnColor);

                                    }
                                    continue;
                                }
                            }
                        }
                    }
                }
                System.out.println("Wrong move");
                //Checker here
            } else if (movement.checkPosition(pieceCoordinate)) {
                Piece piece = board.getPieceViaPosition(pieceCoordinate);
                if (piece != null) {
                    if (!piece.getColor().equals(turnColor)) {
                        System.out.println("Wrong Color");
                        continue;
                    }
                } else {
                    System.out.println("No piece in given position");
                    continue;
                }
                String targetCoordinate = input.next().toUpperCase();
                //Checker here
                if (movement.checkPosition(targetCoordinate)) {
                    if ((targetCoordinate.charAt(1) == '1' && piece.getColor().equals(Color.BLACK)) || (targetCoordinate.charAt(1) == '8' && piece.getColor().equals(Color.WHITE))) {
                        if (piece.getClass().getSimpleName().equals("Pawn")) {
                            String transformationPiece = input.next().toUpperCase();
                            //Checker here
                            if (movement.checkTransformation(transformationPiece)) {
                                System.out.println("Pawn ->" + transformationPiece);
                            } else {
                                System.out.println("Wrong move");
                            }
                        } else {
                            if (piece.isMovePossible(targetCoordinate, board) && !movement.moveCreatesSelfCheck(board, turnColor, piece, targetCoordinate)) {
                                System.out.println("Move Possible");
                                movement.makeMove(pieceCoordinate, targetCoordinate, board);
                                isPlayerInCheck = false;
                            } else {
                                System.out.println("Wrong Input");
                                continue;
                            }
                            if (movement.moveCreatesCheck(board, turnColor, piece, targetCoordinate)) {
                                isPlayerInCheck = true;
                            }
                            turnColor = switchColor(turnColor);
                        }
                    } else {
                        if (piece.getClass().getSimpleName().equals("Pawn")) {
                            if ((targetCoordinate.charAt(1) == '1' && piece.getColor().equals(Color.WHITE)) || (targetCoordinate.charAt(1) == '8' && piece.getColor().equals(Color.BLACK))) {
                                System.out.println("Wrong Move");
                                continue;
                            }
                        }
                        if (piece.isMovePossible(targetCoordinate, board) && !movement.moveCreatesSelfCheck(board, turnColor, piece, targetCoordinate)) {
                            System.out.println("Move Possible");
                            movement.makeMove(pieceCoordinate, targetCoordinate, board);
                            isPlayerInCheck = false;
                        } else {
                            System.out.println("Wrong Input");
                            continue;
                        }
                        if (movement.moveCreatesCheck(board, turnColor, piece, targetCoordinate)) {
                            isPlayerInCheck = true;
                        }
                        turnColor = switchColor(turnColor);
                    }
                } else {
                    System.out.println("Wrong move");
                }
            } else {
                System.out.println("Wrong move");
            }
        }
    }

    public static void startGame() {
        Board board = new Board();
        Movement movement = new Movement();
        board.createTiles();
        board.setPieces();
        inputs(board, movement);
    }


    private static Color switchColor(Color color) {
        switch (color) {
            case BLACK -> {
                return Color.WHITE;
            }
            case WHITE -> {
                return Color.BLACK;
            }
        }
        return null;
    }

}