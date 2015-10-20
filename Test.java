import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.Math;
import java.util.*;
import java.lang.*;
public class Test extends JFrame 
{       //int vn;
        //int pn;
        FgTextField[] variableText;
        FgTextField[] parameterText;
        FgTextField[] equationText;
        FgTextField[] pValueText;
        JLabel[] variableLabel;
        JLabel[] parameterLabel;
        JLabel[] equationLabel;
        JLabel[] pValueLabel;
        String v[];
        String p[];
        String e[];
        String x[];
        String pValue[];
        String confirmedP;
        FgTextField VnumberText;
        FgTextField PnumberText;
        JButton VnumberButton;
        JButton PnumberButton;
        JPanel Vpan1;
        JPanel Vpan2,Vpan3;
        JPanel Ppan1;
        JPanel Ppan2,Ppan3;
        JPanel Switchpan;
        JPanel PValuePan; 
        JPanel equationsPan;
        JPanel Vswitch,Pswitch;
        JPanel pan21,pan22;
        JPanel pan211,pan212,pan213,pan214;
        LinePanel draw;
        JComboBox Vname,Pname;
        int Vindex;
        double rangex,rangey;
        JTextField  defineX,defineY;

    public Test(String title)
    {   
        super(title);
        setSize(1000,600);
        Container c=getContentPane();
        c.setLayout(new GridLayout(2,1));
        JPanel Pan1=new JPanel();
        JPanel Pan2=new JPanel();
        //JPanel Pan3=new JPanel();

        Vswitch=new JPanel();
        //Vswitch.setLayout(new BoxLayout(Vswitch,BoxLayout.Y_AXIS));
        Pswitch = new JPanel();

        add(Pan1);
        add(Pan2);
        //add(Pan3);

        Pan1.setLayout(new GridLayout(2,2));

        JPanel Vpan=new JPanel(); 
        JPanel Ppan=new JPanel(); 
        PValuePan=new JPanel();
        Switchpan=new JPanel();
        Switchpan.setLayout(new GridLayout(1,2));
        Switchpan.add(Vswitch);
        Switchpan.add(Pswitch); 
        Vpan1=new JPanel();
        Vpan2=new JPanel();
        Vpan3=new JPanel();
        Ppan1=new JPanel();
        Ppan2=new JPanel();
        Ppan3=new JPanel();


        equationsPan=new JPanel();
        //Vpan.setLayout(new BoxLayout(Vpan,BoxLayout.Y_AXIS));

        JLabel text1=new JLabel("Please input the number of variables");
        //text1.setSize(10,200);
        JLabel text2=new JLabel("Please input the number of parameters");

        VnumberText=new FgTextField("","vnum",0, 5);
        VnumberButton=new JButton("Comfirm");

        PnumberText=new FgTextField("","pnum",0,5);
        PnumberButton=new JButton("Comfirm");

        
        
        PnumberButton.addActionListener(new JButtonHandler());
        VnumberButton.addActionListener(new JButtonHandler());

        //VnumberText.setBounds(0,0,100,100);
        Vpan1.add(text1);
        Vpan1.add(VnumberText);
        Ppan1.add(text2);
        Ppan1.add(PnumberText);
        Vpan3.add(VnumberButton);
        Ppan3.add(PnumberButton);

        //System.out.println(VnumberText.getBounds());
        //Pan1 setup
        Ppan.setLayout(new BoxLayout(Ppan,BoxLayout.Y_AXIS));
        Vpan.setLayout(new BoxLayout(Vpan,BoxLayout.Y_AXIS));
        Vpan.add(Vpan1);
        Vpan.add(Vpan2);
        Vpan.add(Vpan3);
        Ppan.add(Ppan1);
        Ppan.add(Ppan2);
        Ppan.add(Ppan3);
        Pan1.add(Vpan);
        Pan1.add(Ppan);
        Pan1.add(equationsPan);
        Pan1.add(Switchpan);
        Ppan3.setVisible(false);
        Vpan3.setVisible(false);
        
        //Pan2 setup
        //initialize
        Pan2.setLayout(new GridLayout(1,2));
        pan21 = new JPanel();
        pan22 = new JPanel();
        pan21.setLayout(new GridLayout(4,1));
        pan211 = new JPanel();
        pan212 = new JPanel();
        pan213 = new JPanel();
        pan214 = new JPanel();

        //set up pan212
        JButton aaa=new JButton("Plot");
        aaa.addActionListener(new JButtonHandler2());
        pan211.add(aaa);
        defineX = new JTextField(15);
        defineY = new JTextField(15);
        pan213.add(new JLabel("Range of x-axis: "));
        pan213.add(defineX);
        pan214.add(new JLabel("Range of y-axis: "));
        pan214.add(defineY);

        pan21.add(pan211);
        pan21.add(pan212);
        pan21.add(pan213);
        pan21.add(pan214);

        //set up pan22
        draw = new LinePanel();
        //pan22.setBackground(Color.BLACK);
        pan22.add(draw);
        pan22.setLayout(new BoxLayout(pan22,BoxLayout.Y_AXIS));


        Pan2.add(pan21);
        Pan2.add(pan22);

        
        //Pan2.add("Center",Pswitch);
        //Pan2.add("West",Vswitch);
        //Pan2.setBackground(Color.BLUE);
        
/////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
        

//**********************************************************************************
//**********************************************************************************
       //pan22 setup
        
//**********************************************************************************
//**********************************************************************************
//////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////




        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }   

    public static void main(String args[])
    {
         Test frm=new Test("A test");
         frm.setVisible(true);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class JComboBoxHandeler implements ItemListener
    {
        public void itemStateChanged(ItemEvent e){
            
            if(e.getSource()==Pname)
            	{
            		addTextfield(pan212,"pValue",Integer.parseInt(PnumberText.getText()));
            		confirmedP = (String)Pname.getSelectedItem();
            	}
            if(e.getSource()==Vname)
            	{	

            		String temp=(String)Vname.getSelectedItem();
            		for(int i = 0; i < v.length; i++)
            			{ 
            				if(v[i].equals(temp)==true)
            				 {
            				 	
            				 	Vindex=i;
            				 	
            				 }
            			}	

            	}

        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class JButtonHandler2 implements ActionListener
    {
        public void actionPerformed(ActionEvent ee)
        {   
            if(defineX.getText().isEmpty() || defineX.getText() == "0")
                rangex = 10;
            else
                rangex = Double.parseDouble(defineX.getText());
            if(defineY.getText().isEmpty() || defineY.getText() == "0")
                rangey = 100;
            else
                rangey = Double.parseDouble(defineY.getText());

            for(int a = 0 ; a<variableText.length ; a++ )
                v[a]=variableText[a].getText();
            for(int a = 0 ; a<variableText.length ; a++ )
                e[a]=equationText[a].getText();
            for(int a = 0 ; a<parameterText.length ; a++ )
                p[a]=parameterText[a].getText();
            for(int a = 0 ; a<parameterText.length ; a++ )
                pValue[a]=pValueText[a].getText();
            


            System.out.println(Arrays.toString(v));
            System.out.println(Arrays.toString(p));
            System.out.println(Arrays.toString(e));
            System.out.println(Arrays.toString(pValue));

            Gauss example=new Gauss(v,p,e);
            
            x=new String[v.length+1];
            //put the result into the x array
            transform(example.x,x);
          //  System.out.println(x[1]);

            
            //**********************************************************************************
            //**********************************************************************************
            //**********************************************************************************
             Expr function;  // The user's function.
            draw.changeRangex(rangex);
            draw.changeRangey(rangey);
            draw.removeAll();
            draw.clearFunction();
          
            try {
              	  
                  String def = x[Vindex+1];//test
                  //System.out.println();
                  function = new Expr(def);
                  draw.setFunction(function);
                  //message.setText(" Enter a function and press return.");
              }
              catch (IllegalArgumentException e) {
                  draw.clearFunction();
                  //message.setText(e.getMessage());
              }


/*
            draw.removeAll();
            draw = new LinePanel(0,0,100,100);
  */        
            pan22.setVisible(false);
            pan22.setVisible(true);
            //**********************************************************************************
            //**********************************************************************************
            //**********************************************************************************
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    void transform(String[] x1,String[] x2)
    {   
        String[] temp=new String[x.length];
        for(int i=1;i<x.length;i++)
        {
            temp[i]=x1[i];
            for(int j=0;j<p.length;j++)
                {   
                    if(!p[j].isEmpty()){
                        if(p[j].equals(confirmedP)==true){
                            temp[i]=temp[i].replaceAll(p[j],"x");    
                        }
                        else
                            temp[i]=temp[i].replaceAll(p[j],pValue[j]);    
                    }                 
                }   
            x2[i]=temp[i];    

        }

    }

    //control the function of confirm button

    public class JButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent ee)
        {           
            if (ee.getSource()==PnumberButton)
                    {   
                        JLabel Pinputmessage = new JLabel("Please select the parameter: ");
                        Pswitch.removeAll();
                        Pname = new JComboBox();
                        Pname.addItem("Select");
                        for(int a = 0 ; a<parameterText.length ; a++ ){
                            p[a]=parameterText[a].getText();
                            Pname.addItem(p[a]);
                        }
                        Pname.addItemListener(new JComboBoxHandeler());
                        Pswitch.add(Pinputmessage);
                        Pswitch.add(Pname);
                        Pswitch.setVisible(false);
                        Pswitch.setVisible(true);

                    }
            if (ee.getSource()==VnumberButton)
                    {  
                        JLabel Vinputmessage = new JLabel("Please select the variable: ");
                        Vswitch.removeAll();
                        Vname = new JComboBox();
                        Vname.addItem("select");
                        for(int a = 0 ; a<variableText.length ; a++ ){
                            v[a]=variableText[a].getText();
                            Vname.addItem(v[a]);
                        }
                        Vname.addItemListener(new JComboBoxHandeler());
                        Vswitch.add(Vinputmessage);
                        Vswitch.add(Vname);
                        Vswitch.setVisible(false);
                        Vswitch.setVisible(true);

                    }   
            
            
        }
    }

        void addEquationsTextField(int num)
        {
            String temp;
            equationsPan.removeAll();
            equationText=new FgTextField[num];
            equationLabel=new JLabel[num];
            
            equationsPan.setLayout(new GridLayout(num,2));
            for(int i = 0 ; i < num ; i++)
            {   
                equationText[i]=new FgTextField("","equation",i,15);
                temp="equation"+i+"=";
                equationLabel[i]=new JLabel(temp);
                equationsPan.add(equationLabel[i]);
                equationsPan.add(equationText[i]);
                
            }
            equationsPan.setVisible(false);
            equationsPan.setVisible(true);


        }




        void addTextfield(JPanel JPanelType,String Typename,int num)
        {   String temp;
            JPanelType.removeAll();
            
            //System.out.println(num);
            FgTextField[] t=new FgTextField[num];
            JLabel[] t2=new JLabel[num];

            
            JPanelType.setLayout(new GridLayout(num/3,6));
            for(int i = 0 ; i < num ; i++)
            {   
                t[i]=new FgTextField("",Typename,i,3);
                temp=Typename+i+"=";
                if(Typename.equals("pValue")==true){
                    temp=p[i]+" = ";
                    if(p[i].equals(confirmedP) == true){
                        continue;
                    }
                }
                t2[i]=new JLabel(temp);
                JPanelType.add(t2[i]);
                JPanelType.add(t[i]);
            }


            if (Typename.equals("variable")==true)
                {
                    variableText=t;
                    variableLabel=t2;
                }
            if (Typename.equals("parameter")==true)
                {
                    parameterText=t;
                    parameterLabel=t2;





                }
            if (Typename.equals("pValue")==true)
                {
                    pValueText=t;
                    pValueLabel=t2;
                }


            JPanelType.setVisible(false);
            JPanelType.setVisible(true);



        }
 


    public class FgTextField extends JTextField
    {
        String mark;
        int i;
        FgTextField(String sText,String mark1,int i1,int columns)
        {
            super(sText,columns);
            mark=mark1;
            i=i1;

            addKeyListener(new KeyAdapter()
            {
                public void keyTyped(KeyEvent ee)
                {
                    char c=ee.getKeyChar();
                    if (c==KeyEvent.VK_ENTER)
                        {
                            System.out.println(getText());
                            if (mark.equals("variable")==true)
                                v[i]=getText();
                            if (mark.equals("parameter")==true)
                                p[i]=getText();
                            if (mark.equals("equation")==true)
                                e[i]=getText();
                            if (mark.equals("pValue")==true)
                                pValue[i]=getText();
                            if(mark.equals("vnum")==true){
                               //vn=Integer.parseInt(VnumberText.getText());
                                v=new String[Integer.parseInt(VnumberText.getText())];
                                e=new String[Integer.parseInt(VnumberText.getText())];
                                addTextfield(Vpan2,"variable",Integer.parseInt(VnumberText.getText()));
                                addEquationsTextField(Integer.parseInt(VnumberText.getText()));
                                Vpan3.setVisible(true);

                            }
                            if(mark.equals("pnum")==true)
                            {   //pn=Integer.parseInt(PnumberText.getText())
                                p=new String[Integer.parseInt(PnumberText.getText())];
                                pValue=new String[Integer.parseInt(PnumberText.getText())];
                                addTextfield(Ppan2,"parameter",Integer.parseInt(PnumberText.getText()));
                                
                                Ppan3.setVisible(true);

                            }
                   
                            

                        }

                }
            }


                            );

        }


    }

    
}

class LinePanel extends JPanel 
{   
    int x1;
    int y1;
    int x2; 
    int y2;
    Graphics2D g2;
    Expr func;
    double rangex, rangey;  // Difference between the x-values of consecutive points
                      //    on the graph.
    public LinePanel()
    {
    	this.rangex = 10;
        this.rangey = 20;

    }
    public LinePanel(Expr exp )
    {   
        func = exp;
        repaint();
        

            
    }
    public void changeRangex(double x){
        this.rangex = x;
    }
    public void changeRangey(double y){
        this.rangey = y;
    }
    public void clearFunction() {
             // Set the canvas to draw no graph at all.
          func = null;
          repaint();
    }
    public void paint(Graphics g) {
             // Draw the graph of the function or, if func is null, 
             // display a message that there is no function to be graphed.
          if (func == null) {
             g.drawString("No function is available.", 20, 30);
          }
          else {
             //g.drawString("y = " + func.getDefinition(), 5, 15);
             g.drawString("y = " + func.getDefinition(), 190, 10);
            
             drawAxes(g);
             drawFunction(g);
             //drawFunction(g);
          }
    }
   public void setFunction(Expr exp) 
   {
     // Set the canvas to graph the function whose definition is
     // given by the function exp.
        func = exp;
        repaint();
    }

    void drawAxes(Graphics g) {
             // Draw horizontal and vertical axes in the middle of the
             // canvas.  A 5-pixel border is left at the ends of the axes.
          int width = getSize().width;
          int height = getSize().height;
          g.setColor(Color.blue);
          //g.drawLine(5, height/2, width-5, height/2);
          g.drawLine(12, height-12, width-12, height-12);
          //g.drawLine(width/2, 5, width/2, height-5);
          g.drawLine(12, height-12, 12, 12);
          g.drawString("0", 5, height);
          g.drawString(Double.toString(rangey), 1, 10);
          g.drawString(Double.toString(rangex),width-25, height-1);
    }
    void drawFunction(Graphics g) {
            // Draw the graph of the function defined by the instance variable func.
            // Just plot 301 points with lines between them. 
            
          double x, y;          // A point on the graph.  y is f(x).
          double prevx, prevy;  // The previous point on the graph.
          
          
          
          double dx  = rangex / 300;
          System.out.println("dx: "+dx);
          
          g.setColor(Color.red);
          
          /* Compute the first point. */
          
          x = 0;
          y = func.value(x);
          //y = rangey;
          /* Compute each of the other 300 points, and draw a line segment
             between each consecutive pair of points.  Note that if the function
             is undefined at one of the points in a pair, then the line 
             segment is not drawn.  */
          
          for (int i = 1; i <= 300; i++) {
          	 // if(y >= rangey){
          	 // 	rangey = y; 
          	 // }
             if(y > rangey)
                break;

             prevx = x;           // Save the coords of the previous point.
             prevy = y;
             
             x += dx;            // Get the coords of the next point.
             y = func.value(x);
             
             if ( (! Double.isNaN(y)) && (! Double.isNaN(prevy)) ) {
                   // Draw a line segment between the two points.
                putLine(g, prevx, prevy, x, y);
             }
                
          }  // end for
          
       }  // end drawFunction()
    
       
       void putLine(Graphics g, double x1, double y1, double x2, double y2) {
             // Draw a line segment from the point (x1,y1) to (x2,y2).  These
             // real values must be scaled to get the integer coordinates of
             // the corresponding pixels.
             
          int a1, b1;   // Pixel coordinates corresponding to (x1,y1).
          int a2, b2;   // Pixel coordinates corresponding to (x2,y2).
          
          int width = getSize().width;     // Width of the canvas.
          int height = getSize().height;   // Height of the canvas. 
          /*
          a1 = (int)( (x1 + 5) / 10 * width );
          b1 = (int)( (5 - y1) / 10 * height );
          a2 = (int)( (x2 + 5) / 10 * width );
          b2 = (int)( (5 - y2) / 10 * height );*/
          
          a1 = (int)( (x1) / rangex * width )+12;
          b1 = (int)( (rangey - y1) / rangey * height )-12;
          a2 = (int)( (x2) / rangex * width )+12;
          b2 = (int)( (rangey - y2) / rangey * height )-12;
          
          g.drawLine(a1,b1,a2,b2);
          
       }
    /*   
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g2 = (Graphics2D)g;
        //g2.draw(line);
        g2.drawLine(x1,y1,x2,y2);
    }*/



}