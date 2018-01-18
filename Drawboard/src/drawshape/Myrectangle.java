package drawshape;

import java.awt.Color;
import java.awt.Graphics;

public class Myrectangle extends Myshape{

	private Graphics g;
	private int x,y,width,height;
	private Color c;
	
	public Myrectangle(Graphics g,int x,int y,int width,int height,Color c) {
		this.g=g;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.c=c;
	}
	
	@Override
	public void draw() {
		g.setColor(c);
		g.drawRect(x, y, width, height);
		
	}
}
