import java.util.ArrayList;

public abstract class Piece {
    protected Color color;
    protected boolean justDoubleMoved = false;
    protected boolean hasMoved;
    protected boolean canCastle = false;
    protected Position position;

    Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }


    public abstract boolean isMovePossible(String targetCoordinate, Board board);

    public void setJustDoubleMoved(boolean justDoubleMoved) {
        this.justDoubleMoved = justDoubleMoved;
    }

    public boolean checkCastlePossibility(Board board, String castleType, Movement movement){
        return false;
    }

    public abstract boolean doesAttackKing(Board board);

    public abstract ArrayList<String> possibleMovesSet(Movement movement, Board board);


    protected Position getPosition(){
       return this.position;
    }
    protected void setPosition(Position position){
        this.position = position;
    }
    protected void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }
    protected void setCanCastle(boolean canCastle){this.canCastle = hasMoved;}
}

