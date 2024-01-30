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
}
