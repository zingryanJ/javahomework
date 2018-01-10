package pingip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class pinglistener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("确定")) {
			ping("www.baidu.com");
		}
	}
	
	private void ping(String i) {
		Runtime runtime = Runtime.getRuntime(); // 获取当前程序的运行进对象
		Process process = null; // 声明处理类对象
		String line = null; // 返回行信息
		InputStream is = null; // 输入流
		InputStreamReader isr = null; // 字节流
		BufferedReader br = null;
		String ip = i;
		boolean res = false;// 结果
		try {
			process = runtime.exec("ping " + ip + " -c 1"); // PING
			is = process.getInputStream(); // 实例化输入流
			isr = new InputStreamReader(is);// 把输入流转换成字节流
			br = new BufferedReader(isr);// 从字节中读取文本
			while ((line = br.readLine()) != null) {
				if (line.contains("ttl")) {
					res = true;
					break;
				}
			}
			is.close();
			isr.close();
			br.close();
			if (res) {
				System.out.println("ping 通  ...");
			} else {
				System.out.println("ping 不通...");
			}
		} catch (IOException e) {
			System.out.println(e);
			runtime.exit(1);
		}
	}
}
