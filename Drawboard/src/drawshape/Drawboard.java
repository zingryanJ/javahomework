package drawshape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Drawboard extends JFrame{
	private List<Myshape> shapeArray = new ArrayList<Myshape>();
	
	public static void main(String[] args) {
		Drawboard dr = new Drawboard();
		dr.showUI();
	}
	
	private DrawListener dl;
	
	public void addarr(Myshape s) {
		shapeArray.add(s);
	}
	public List<Myshape> getarr(){
		return shapeArray;
	}
	
	public void showUI(){

		dl = new DrawListener();
		
		JFrame frame = new JFrame();
		JPanel rightpan=new JPanel();
		JPanel centerpan=new JPanel(new FlowLayout(FlowLayout.LEFT,4,4));
		Mypanel drawpan=new Mypanel();
		JPanel colorpan=new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("DrawBoard");
		frame.setSize(900,600);
		rightpan.setPreferredSize(new Dimension(150,600));
		centerpan.setBackground(Color.GRAY);
		drawpan.setPreferredSize(new Dimension(742,592));
		rightpan.setBackground(Color.GRAY);
		drawpan.setBackground(Color.WHITE);
		centerpan.add(drawpan);

		
		Color[] color_array = {Color.BLACK,Color.BLUE,Color.CYAN,Color.darkGray,Color.GRAY,Color.GREEN,Color.LIGHT_GRAY,Color.PINK,Color.ORANGE,Color.RED,Color.WHITE,Color.yellow};
		for(int i = 0; i< color_array.length;i++) {
			JButton button = new JButton();
			button.setBackground(color_array[i]);
			button.setPreferredSize(new Dimension(30, 30));
			button.addActionListener(dl);
			colorpan.add(button);
		}
		String[] array= {"直线","圆圈","矩形","文字"};
		for(int i=0;i < array.length ; i++) {
			JButton button = new JButton(array[i]);
			button.addActionListener(dl);
			rightpan.add(button);
		}
		rightpan.add(colorpan);
		drawpan.setBorder(BorderFactory.createLineBorder(Color.black,1));
		rightpan.setLayout(new GridLayout(5,1,0,3));
		frame.add(rightpan,BorderLayout.EAST);
		frame.add(centerpan, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setVisible(true);

		dl.setG(drawpan.getGraphics());
		drawpan.addMouseListener(dl);
		drawpan.addMouseMotionListener(dl);		
		
	}
	
	
	
}
