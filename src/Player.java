import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity{

    public Player(){
        super();
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, width, height);
    }

    public void update(boolean u, boolean d, boolean l, boolean r){
        
        if(u) y -= speed;
        if(d) y += speed;
        if(l) x -= speed;
        if(r) x += speed;

    }
}
