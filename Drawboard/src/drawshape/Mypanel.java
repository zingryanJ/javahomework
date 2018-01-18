package drawshape;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Mypanel extends JPanel
{
	private ArrayList<Myshape> shapearray = new ArrayList<Myshape>();
	Drawboard db = new Drawboard();
	
	public void paint(Graphics g) {
		super.paint(g);
		for(Myshape l : shapearray) {
			l.draw();
		}
	}

}
