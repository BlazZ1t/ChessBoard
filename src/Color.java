public enum Color {
    BLACK ("B"),
    WHITE ("W");
    Color(String b) {
        this.color = b;
    }
    private String color;

    public String getColor(){
        return color;
    }
}
