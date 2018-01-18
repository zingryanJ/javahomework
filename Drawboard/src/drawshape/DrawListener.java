package drawshape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DrawListener extends MouseAdapter implements ActionListener,MouseListener,MouseMotionListener{
	private String type = "直线";
	private Color color=Color.black;
	private Graphics2D g;
	private int x1,x2,y1,y2;
	private int width,height;
	private String stxt;
	private JButton nowColor;
	private Myshape shape,selectshape;
	Drawboard db = new Drawboard();
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!e.getActionCommand().equals("")) {
			if(e.getActionCommand().equals("文字")) {
				stxt=JOptionPane.showInputDialog("请输入文本内容");
			}
			 type=e.getActionCommand();
		}
		else {
			JButton button = (JButton) e.getSource();
			color = button.getBackground();
		}
	}
	
	
	public void setG(Graphics g) {
		this.g=(Graphics2D)g;
	}
	public Graphics2D getG() {
		return this.g;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		g.setColor(color);
		x1 = e.getX();
		y1 = e.getY();
		for(Myshape s : db.getarr()) {
			if(s.isSelect) {
				selectshape = s;
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		if(type.equals("直线")) {
			shape=new Myline(g,x1,y1,x2,y2,color);
			shape.draw();
			db.addarr(shape);
		}
		else if(type.equals("圆圈")){
			width=Math.abs(x2-x1);
			height=Math.abs(y2-y1);
			if(x1<x2 && y1>y2) {
				shape=new Myoval(g,x1,y2,width,height,color);
			}
			else if(x1<x2 && y1<y2) {
				shape=new Myoval(g,x1,y1,width,height,color);
			}
			else if(x1>x2 && y1<y2) {
				shape=new Myoval(g,x2,y1,width,height,color);
			}
			else {
				shape=new Myoval(g,x2,y2,width,height,color);
			}
			shape.draw();
			db.addarr(shape);
		}
		else if(type.equals("矩形")) {
			width=Math.abs(x2-x1);
			height=Math.abs(y2-y1);
			if(x1<x2 && y1>y2) {
				shape=new Myrectangle(g,x1,y2,width,height,color);
			}
			else if(x1<x2 && y1<y2) {
				shape=new Myrectangle(g,x1,y1,width,height,color);
			}
			else if(x1>x2 && y1<y2) {
				shape=new Myrectangle(g,x2,y1,width,height,color);
			}
			else {
				shape=new Myrectangle(g,x2,y2,width,height,color);
			}
			
			shape.draw();
			db.addarr(shape);
		}
		else if(type.equals("文字")) {
			shape=new Mytxt(g,x1,y1,stxt,color);
			shape.draw();
			db.addarr(shape);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}
