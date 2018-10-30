package game01;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyButton extends JFrame implements ActionListener {
	
	JButton b1 = new JButton("开始游戏");
	JButton b2 = new JButton("再来一次");
	JFrame  frame = new JFrame();
	public MyButton() {
	
		frame.add(b1);
		b1.addActionListener(this);
		frame.setVisible(true);
		frame.setSize(100, 100);
		frame.setLocation(100, 100);
	}
	
	public  MyButton(int i ) {
		
		frame.add(b2);
		b2.addActionListener(this);
		frame.setVisible(true);
		frame.setSize(100, 100);
		frame.setLocation(300, 300);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		new MyGameFrame().launchFrame();
		frame.dispose();
	}

	

}
