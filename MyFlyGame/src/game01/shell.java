package game01;

import java.awt.Color;
import java.awt.Graphics;

public class shell extends GameObject {
   double degree;
   public shell() {
	   x=200;
	   y=200;
	   width = 10;
	   height = 10;
	   speed = 3;
	   degree = Math.random()*Math.PI*2;
   }
   
   public void draw(Graphics g) {
	   Color c = g.getColor();
	   g.setColor(Color.YELLOW);
	   g.fillOval((int)x,(int) y, width, height);
	   
	   x+=speed*Math.cos(degree);
	   y+=speed*Math.sin(degree);
	   
	   if(x<0||x>500-width) {
		   degree = Math.PI -degree;
	   }
	   if(y<30||y>500-height) {
		   degree = -degree;
	   }
	   g.setColor(c);
   }
}
