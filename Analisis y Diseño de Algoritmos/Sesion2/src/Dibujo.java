import java.awt.Graphics;

public class Dibujo {
	
	public static void main(String[] args) {
		Graphics g = new Graphics();
		dibujar();
	}
	
	public static void dibujar(Graphics g, int x1, int y1, double angle, int depth) {
        if (depth == 0) 
        	return;
		 int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 5.0);
	        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 5.0);
	        g.drawLine(x1, y1, x2, y2);
	        dibujar(g, x2, y2, angle - 20, depth - 1);
	        dibujar(g, x2, y2, angle + 20, depth - 1);
	  }

}
