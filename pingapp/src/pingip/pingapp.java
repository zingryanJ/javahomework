package pingip;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class pingapp{
	private JTextArea jt;
	private JFrame frame;
	private ArrayList<myip> iplist = new ArrayList<myip>();
	
	
	public static void main(String[] args) {
		pingapp ping = new pingapp();
		ping.showUI();
	}
	
	
	public void showUI(){
		Font bigFont = new Font("sanserif",Font.BOLD,15);
//		声明组件
		frame = new JFrame();
		
		try {
			String src = "/img/Pingcon.jpg";
			Image image=ImageIO.read(this.getClass().getResource(src));
			frame.setIconImage(image);   
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		JPanel westpan = new JPanel();
		JPanel centpan = new JPanel();
		JPanel eastpan = new JPanel();
		westpan.setPreferredSize(new Dimension(220,600));
		westpan.setBorder(BorderFactory.createEtchedBorder());
		westpan.setLayout(new GridLayout(1, 1));
		centpan.setPreferredSize(new Dimension(600,600));
		centpan.setBorder(BorderFactory.createTitledBorder("输入IP"));
		centpan.setLayout(new GridLayout(1, 1));
		eastpan.setPreferredSize(new Dimension(180,600));
		eastpan.setLayout(new GridLayout(3,1,5,5));
		eastpan.setBorder(new EmptyBorder(10,10,10,10));
//		声明元件
		String helptxt = "1.在文本框中输入IP，注意每个一行一个IP\r\n\n"+"2.点击确定等待完成提示\r\n\n"+"3.点击导出完成保存";
		JTextArea ht = new JTextArea();
		ht.setLineWrap(true);
		ht.setEditable(false);
		ht.setBackground(new Color(238,238,238));
		ht.setText(helptxt);
		ht.setFont(new Font("sanserif",Font.BOLD,18));
		westpan.add(ht);
		jt = new JTextArea(26,40);
		jt.setLineWrap(true);
		jt.setWrapStyleWord(true);
		jt.setFont(bigFont);
		jt.setBorder(BorderFactory.createEtchedBorder());
		
		JScrollPane jsjt = new JScrollPane(jt);
		jsjt.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsjt.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		String[] tbti = {"IP","状态"};
		
		
		JTable jtb = new JTable();
		
		JScrollPane jstb = new JScrollPane();
		jstb.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jstb.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton startbt = new JButton("确定");
		startbt.addActionListener(new startping());
		eastpan.add(startbt);
		JButton restbt = new JButton("重置");
		restbt.addActionListener(new restall());
		eastpan.add(restbt);
		JButton savebt = new JButton("导出");
		savebt.addActionListener(new savefile());
		eastpan.add(savebt);
		centpan.add(jsjt);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("监控部专用IP工具");
//		尺寸
		frame.setSize(1000, 600);
//		打开位置为中央
		frame.setLocationRelativeTo(null);
		
//		载入组件
		frame.add(westpan,BorderLayout.WEST);
		frame.add(centpan,BorderLayout.CENTER);
		frame.add(eastpan,BorderLayout.EAST);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	
	public class startping implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			iplist.clear();
			String statue = null;
			String zan = null;
			String[] allip = jt.getText().split("\n");
			for(String i : allip) {
				if(i.split("\\.")[3].equals("x")) {
					for(int z=1;z<=126;z++) {
						zan=i.substring(0,i.length()-1)+z;
						statue =ping(zan);
						myip mip = new myip(zan, statue); 
						iplist.add(mip);
					}
				}else{
					statue =ping(i);
					myip mip = new myip(i, statue); 
					iplist.add(mip);
				}
			}
			JOptionPane.showMessageDialog(null, "完成");
		}
	}
	
	
	public class restall implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			resta();
		}
	}
	
	
	public class savefile implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFileChooser fileSave = new JFileChooser();
			fileSave.setSelectedFile(new File("file.xlsx"));
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
		}
	}
	
	
	private String ping(String i) {
		Runtime runtime = Runtime.getRuntime(); // 获取当前程序的运行进对象
		Process process = null; // 声明处理类对象
		String line = null; // 返回行信息
		InputStream is = null; // 输入流
		InputStreamReader isr = null; // 字节流
		BufferedReader br = null;
		String ip = i;
		String result = null;
		boolean res = false;// 结果
		try {
			process = runtime.exec("ping " + ip + " -n 1 -w 100"); // PING
			is = process.getInputStream(); // 实例化输入流
			isr = new InputStreamReader(is);// 把输入流转换成字节流
			br = new BufferedReader(isr);// 从字节中读取文本
			while ((line = br.readLine()) != null) {
				if (line.contains("TTL")) {
					res = true;
					break;
				}
			}
			is.close();
			isr.close();
			br.close();
			if (res) {
				result = "通";
			} else {
				result = "不通";
			}
		} catch (IOException e) {
			System.out.println(e);
			runtime.exit(1);
		}
		return result;
	}
	
	
	private void resta() {
		jt.setText("");
		iplist.clear();
	}
	private void saveFile(File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			Iterator ipIterator = iplist.iterator();
			while(ipIterator.hasNext()) {
				myip mip = (myip)ipIterator.next();
				writer.write(mip.getip()+"\t");
				writer.write(mip.getstatus());
				writer.newLine();
			}
			writer.close();
		}catch(IOException ex) {
			System.out.println("错误");
			ex.printStackTrace();
		}
	}
}
