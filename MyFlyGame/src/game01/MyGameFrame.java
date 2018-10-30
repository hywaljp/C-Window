package game01;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyGameFrame extends JFrame{

	Image bg = GameUtil.getImage("images/bg.jpg");
	Image planeImg = GameUtil.getImage("images/plane.png");
	
	Plane plane = new Plane(planeImg,250,250);
	shell[] Shell = new shell[50];
	Explode bao;
	Date startTime = new Date();
	Date endTime;
	int period;
	String a ;
	JButton b2 = new JButton("再来一次");
	JFrame frame1 = new JFrame();
	 AudioClip ac;
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g);
		
		for(int i = 0 ; i<50;i++) {
			Shell[i].draw(g);
			boolean  peng = ( Shell[i]).getRect().intersects(plane.getRect());
		   if(peng) {
			   plane.live = false;
			   if(bao == null) {
				   bao = new Explode(plane.x, plane.y); 
			    
						   endTime = new Date();
				   period = (int)((endTime.getTime()-startTime.getTime())/1000);
			   }
			   bao.draw(g); 
		   }
		   
		   if(!plane.live) {
			   g.setColor(Color.RED);
			   Font f = new Font("宋体", Font.BOLD,50);
			   g.setFont(f);
			   g.drawString("时间："+period+"秒",100,100);
			   ac.stop();
			  
		   }
		
	} g.setColor(c);
		}
	
	class PaintThread extends Thread{
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
				
	
	}
	
	class KeyMonitor extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
	}
	
  public void launchFrame() {
	  URL url = null; 

	  File f1 = new File("F:/zl.au");  //在F盘下面有一个.au的音乐文件
	  try {
	   url= f1.toURL();
	} catch (MalformedURLException e) {      
	e.printStackTrace();
	} 
	  ac= Applet.newAudioClip(url);
	  ac.loop();
	  
	  
	  this.setTitle("小作品");
	  this.setVisible(true);
	  this.setSize(500, 500);
	  this.setLocation(100, 100);
	  this.addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent e) {
			  System.exit(0);
		  }
	  });
	  
	  new PaintThread().start();
	  addKeyListener(new KeyMonitor());
	  
	  for(int i = 0 ;i<50;i++) {
		  Shell[i] = new shell();
	  }
  }
  
  public static void main(String[] args) {
	  
    new MyButton();
    
  }
  
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(500,500);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}
}
