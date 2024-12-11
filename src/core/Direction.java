package core;

//a group of contants that describe direction of movement
public enum Direction {
    S(0),
    SW(0),
    W(3),
    NW(1),
    N(1),
    NE(1),
    E(2),
    SE(0);

    private int animationRow;

    Direction(int animationRow) {
        this.animationRow = animationRow;
    }

    public static Direction fromMotion(Motion motion) {
        double x = motion.getVector().getX();
        double y = motion.getVector().getY();

        if(x == 0 && y > 0) return S;
        if(x < 0 && y == 0) return W;
        if(x == 0 && y < 0) return N;
        if(x > 0 && y == 0) return E;
        if(x < 0 && y > 0) return SW;
        if(x < 0 && y < 0) return NW;
        if(x > 0 && y < 0) return NE;
        if(x > 0 && y > 0) return SE;

        return S;
    }

    public int getAnimationRow() {
        return animationRow;
    }
}
