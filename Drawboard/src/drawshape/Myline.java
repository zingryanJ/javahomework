package drawshape;

import java.awt.Color;
import java.awt.Graphics;

public class Myline extends Myshape{

	private Graphics g;
	private int x1,y1,x2,y2;
	private Color c;
	
	public Myline(Graphics g,int x1,int y1,int x2,int y2,Color c) {
		this.g=g;
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.c=c;
	}
	
	@Override
	public void draw() {
		g.setColor(c);
		g.drawLine(x1, y1, x2, y2);
		
	}
}
