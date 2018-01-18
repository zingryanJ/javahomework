package drawshape;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Mytxt extends Myshape{
	
	private Graphics g;
	private int x1,y1,x2,y2;
	private String s;
	private Color c;
	
	public Mytxt(Graphics g,int x,int y,String s,Color c) {
		this.g=g;
		this.x1=x;
		this.y1=y;
		this.s=s;
		this.c=c;
	}
	
	@Override
	public void draw() {
		g.setColor(c);
		g.setFont(new Font("宋体",Font.PLAIN,40));
		g.drawString(s, x1, y1);
	}
}
