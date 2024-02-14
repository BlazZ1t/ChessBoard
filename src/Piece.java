public abstract class Piece {
    protected Color color;
    protected boolean justDoubleMoved = false;
    protected boolean hasMoved;
    protected boolean canCastle = true;
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

    public boolean checkCastlePossibility(Board board){
        return false;
    }

    public abstract boolean doesAttackKing(Board board);


    protected Position getPosition(){
       return this.position;
    }
    protected void setPosition(Position position){
        this.position = position;
    }
    protected void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }
}

