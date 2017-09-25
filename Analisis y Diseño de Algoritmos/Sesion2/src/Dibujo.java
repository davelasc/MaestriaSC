import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Dibujo extends JFrame {
     
	private static final long serialVersionUID = 1L;
    private static JPanel jPanel1;
    public Dibujo() {
        super("Examen parte 5");
        
        
        jPanel1 = new JPanel(new GridBagLayout()){
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
        		super.paintComponent(g);
        		Dibujo.dibujar(g,800/2, 600, -90, 9);
        	}
        };
        jPanel1.setPreferredSize(new Dimension(1000,1000) );
        add(jPanel1);
        pack();
        setLocationRelativeTo(null);
        
        
    }
    
    public static void dibujar(Graphics g2, int x1, int y1, double angle, int depth) {
    	if (depth == 0) 
    		return;
    	int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 5.0);
    	int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 5.0);
    	g2.drawLine(x1, y1, x2, y2);
    	dibujar(g2, x2, y2, angle - 20, depth - 1);
    	dibujar(g2, x2, y2, angle + 20, depth - 1);
    }
    
    public static void dibujarIte(Graphics g2, int x1, int y1, double angle, int depth) {
    	LinkedList<Integer> myList = new LinkedList<Integer>();
    	LinkedList<Double> angleList = new LinkedList<Double>();
    	myList.add(x1);
    	myList.add(y1);
    	myList.add(depth);
    	angleList.add(angle);
    	
    	
    	while(!myList.isEmpty()) {
    		
    		x1 = myList.pop();
    		y1 = myList.pop();
    		depth = myList.pop();
    		angle = angleList.pop();
    		
    		if(depth == 0)
    			continue;
    		
    		int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 5.0);
        	int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 5.0);
        	
        	g2.drawLine(x1, y1, x2, y2);
        	
        	myList.add(x2);
        	myList.add(y2);
        	myList.add(depth-1);
        	angleList.add(angle + 20);
        	
        	myList.add(x2);
        	myList.add(y2);
        	myList.add(depth-1);
        	angleList.add(angle - 20);
        	
    	}
    	
    }

     
    public static void main(String[] args) {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Dibujo().setVisible(true);
            }
        });
    }
}