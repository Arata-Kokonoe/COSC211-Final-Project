package entities.obstacle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class SquareObstacle extends Obstacle{

    public SquareObstacle(int obsX, int obsY, int obsWidth, int obsHeight){
        super(obsX, obsY, obsWidth, obsHeight);
        collision = true;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 30, 30);
    }
    
}
