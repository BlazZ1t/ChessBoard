import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        startGame();
    }

    public static void inputs(Board board, Movement movement) {
        Color turnColor = Color.WHITE;
        while (movement.isGameInProgress()) {
            System.out.println("It's " + turnColor.name() + " move");
            Scanner input = new Scanner(System.in);
            String pieceCoordinate = input.next().toUpperCase();
            if (pieceCoordinate.equals("ADD")) {
                String position = input.next();
                String pieceName = input.next();
                String color = input.next();
                board.addPiece(new Position(position.charAt(0), Integer.parseInt(String.valueOf(position.charAt(1)))), pieceName, color);
                continue;
            } else if (pieceCoordinate.equals("REMOVE")) {
                String position = input.next();
                board.removePiece(position);
                continue;
            }
            Piece piece = board.findPieceTypeViaPosition(pieceCoordinate);
            if (!piece.getColor().equals(turnColor)) {
                System.out.println("Wrong Color");
                continue;
            }
            if (pieceCoordinate.equals("0-0-0") || pieceCoordinate.equals("0-0")) {
                movement.moveCastle();
                //Checker here
            } else if (movement.checkPosition(pieceCoordinate)) {
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
                            if (piece.isMovePossible(targetCoordinate, board)) {
                                System.out.println("Move Possible");
                            } else {
                                System.out.println("Wrong Input");
                                continue;
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
                        if (piece.isMovePossible(targetCoordinate, board)) {
                            System.out.println("Move Possible");
                        } else {
                            System.out.println("Wrong Input");
                            continue;
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
        board.displayBoard();
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