package core;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    public int intX() {
        return (int) Math.round(x);
    }

    public int intY() {
        return (int) Math.round(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double newX) {
        x = newX;
    }

    public void setY(double newY) {
        y = newY;
    }

    public void apply(Motion movement) {
        Vector2D vector = movement.getVector();
        x += vector.getX();
        y += vector.getY();
    }

    public void apply(Vector2D newVector) {
        Vector2D vector = newVector;
        x += vector.getX();
        y += vector.getY();
    }

    public void applyX(int xVel) {
        this.x += xVel;
    }

    public void applyY(int yVel) {
        this.y += yVel;
    }
}
