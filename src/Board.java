import java.util.HashMap;
import java.util.Map;

public class Board {
    public Map<Position, Tile> tiles = new HashMap<>();
    public Map<Position, Piece> pieces = new HashMap<>();

    public void addPiece(Position position, String pieceName, String color) {
        pieces.put(position, createNewPieceViaString(pieceName, position, color));
    }

    public void removePiece(String position) {
        Piece piece = getPieceTypeViaPosition(position);
        Position key = getKeyViaPiece(piece);
        pieces.remove(key);

    }

    public void createTiles() {
        for (int i = 65; i <= 72; i++) {
            Color startingColor = Color.BLACK;
            if (i % 2 == 0) {
                startingColor = Color.WHITE;
            }
            for (int j = 1; j <= 8; j++) {
                tiles.put(new Position((char) i, j), new Tile(startingColor));
                switch (startingColor) {
                    case BLACK -> startingColor = Color.WHITE;
                    case WHITE -> startingColor = Color.BLACK;
                }
            }
        }
    }

    public void setPieces() {
        for (char i = 65; i <= 72; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    pieces.put(new Position(i, 7), new Pawn(new Position(i, 7), Color.BLACK));
                } else {
                    pieces.put(new Position(i, 2), new Pawn(new Position(i, 2), Color.WHITE));
                }
            }
        }
        for (int i = 65; i <= 72; i = i + 7) {
            for (int j = 1; j <= 8; j += 7) {
                if (j == 1) {
                    pieces.put(new Position((char) i, j), new Rook(new Position((char) i, j), Color.WHITE));
                } else {
                    pieces.put(new Position((char) i, j), new Rook(new Position((char) i, j), Color.BLACK));
                }
            }
        }
        for (int i = 66; i <= 71; i = i + 5) {
            for (int j = 1; j <= 8; j += 7) {
                if (j == 1) {
                    pieces.put(new Position((char) i, j), new Knight(new Position((char) i, j), Color.WHITE));
                } else {
                    pieces.put(new Position((char) i, j), new Knight(new Position((char) i, j), Color.BLACK));
                }
            }
        }
        for (int i = 67; i <= 70; i = i + 3) {
            for (int j = 1; j <= 8; j += 7) {
                if (j == 1) {
                    pieces.put(new Position((char) i, j), new Bishop(new Position((char) i, j), Color.WHITE));
                } else {
                    pieces.put(new Position((char) i, j), new Bishop(new Position((char) i, j), Color.BLACK));
                }
            }
        }
        pieces.put(new Position('E', 8), new King(new Position('E', 8), Color.BLACK));
        pieces.put(new Position('E', 1), new King(new Position('E', 1), Color.WHITE));
        pieces.put(new Position('D', 1), new Queen(new Position('D', 1), Color.WHITE));
        pieces.put(new Position('D', 8), new Queen(new Position('D', 8), Color.BLACK));
    }

    public void displayBoard() {
        System.out.print("  ");
        for (int i = 65; i <= 72; i++) {
            System.out.printf((char) i + "  ");
        }
        System.out.println();
        for (int i = 8; i >= 1; i--) {
            System.out.print(i);
            for (char j = 65; j <= 72; j++) {
                boolean foundPiece = false;
                for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
                    Position key = entry.getKey();
                    Piece value = entry.getValue();
                    if (new Position(j, i).stringValue().equals(key.stringValue())) {
                        foundPiece = true;
                        if (value.color == Color.WHITE) {
                            switch (value.getClass().getName()) {
                                case "Pawn" -> System.out.print(" P ");
                                case "King" -> System.out.print(" K ");
                                case "Rook" -> System.out.print(" R ");
                                case "Knight" -> System.out.print(" N ");
                                case "Bishop" -> System.out.print(" B ");
                                case "Queen" -> System.out.print(" Q ");
                            }
                        } else if (value.color == Color.BLACK) {
                            switch (value.getClass().getName()) {
                                case "Pawn" -> System.out.print(" p ");
                                case "King" -> System.out.print(" k ");
                                case "Rook" -> System.out.print(" r ");
                                case "Knight" -> System.out.print(" n ");
                                case "Bishop" -> System.out.print(" b ");
                                case "Queen" -> System.out.print(" q ");
                            }
                        }
                    }
                }
                if (!foundPiece) {
                    System.out.print(" □ ");
                }
            }
            System.out.println();
        }
    }

    public Position getKeyViaPiece(Piece piece) {
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            Position key = entry.getKey();
            Piece value = entry.getValue();
            if (value.equals(piece)) {
                return key;
            }
        }
        return null;
    }

    public Piece getPieceTypeViaPosition(String piecePosition) {
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            Position key = entry.getKey();
            Piece value = entry.getValue();
            if (key.stringValue().equalsIgnoreCase(piecePosition)) {
                return value;
            }
        }
        return null;
    }

    public Piece createNewPieceViaString(String pieceName, Position position, String color) {
        Color colorValue;
        if (color.equals("B")) {
            colorValue = Color.BLACK;
        } else {
            colorValue = Color.WHITE;
        }
        switch (pieceName) {
            case "Pawn" -> {
                return new Pawn(position, colorValue);
            }
            case "Rook" -> {
                return new Rook(position, colorValue);
            }
            case "King" -> {
                return new King(position, colorValue);
            }
            case "Queen" -> {
                return new Queen(position, colorValue);
            }
            case "Bishop" -> {
                return new Bishop(position, colorValue);
            }
            case "Knight" -> {
                return new Knight(position, colorValue);
            }
        }
        return null;
    }
}