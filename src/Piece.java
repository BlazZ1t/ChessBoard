public abstract class Piece {
    protected Color color;
    protected boolean justDoubleMoved = false;
    protected boolean canCastle = true;

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
}
