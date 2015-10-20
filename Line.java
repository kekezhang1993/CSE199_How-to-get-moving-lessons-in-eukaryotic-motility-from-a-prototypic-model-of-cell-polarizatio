import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.geom.Line2D;

public class Line 
{
	public static void main(String[] args)
	 {
	LineFrame frame = new LineFrame();
	frame.setDefaultCloseOperation(3);
	frame.show();
	}
}

class LineFrame extends JFrame
{
	public LineFrame()
	{
		setTitle("Line");
		setResizable(false);
		setSize(800,600);
		LinePanel panel = new LinePanel(0,0,500,500);
		Container contentPane = getContentPane();
		contentPane.add(panel);
	}
}

class LinePanel extends JPanel 
{	
	int x1;
	int y1;
	int x2;	
	int y2;
	Graphics2D g2;
	public LinePanel(int a,int b,int c,int d)
	{	super();
		x1=a;
		y1=b;
		x2=c;
		y2=d;
			
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g2 = (Graphics2D)g;
		//g2.draw(line);
		g2.drawLine(x1,y1,x2,y2);
	}



}