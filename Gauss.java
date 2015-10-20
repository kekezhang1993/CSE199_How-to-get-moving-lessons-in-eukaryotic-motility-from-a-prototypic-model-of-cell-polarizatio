import java.util.*;
import java.lang.*;

public class Gauss {

    /**
     * @列主元高斯消去法
     */
    public static String a[][];
    public static String b[];
    public static String x[];
    public static String v[]; // names of variables
    public static String p[] = {"a","b","c","d","e"}; // names of parameters
    public static String exp_x; // the expression for the only x
    public static int pn; // number of parameters
    public static int n;
    public static int n2; //记录换行的次数
    //public static double[] ;
    public static String[] e;//arrays of equations
    public static Parser[] parsers;

    //constructor
    public Gauss(String[] var, String[] param, String[] equas){
        this.p = param;
        this.v = var;
        this.e = equas;

        n=v.length;
        //initialize
        a=new String[n+1][n+1]; //matrix
        b=new String[n+1]; //constant
        x=new String[n+1]; //expression

        pn=p.length;

        parsers = new Parser[n];
        for (int i = 0; i < n; i++) {

            parsers[i] = new Parser(e[i],v, p);
            for (int j = 1; j < n+1 ; j++) {
                a[i+1][j] = parsers[i].coes[j-1];
                //***********************************************
            if (a[i+1][j].isEmpty()==true)
                a[i+1][j]="0";
            //***********************************************
            
            }
            b[i+1] = parsers[i].coes[n];

            System.out.println(b[i+1]);
            System.out.println(Arrays.toString(a[i+1]));//输出数组
            
        }
        Elimination();
        Back();
        Print();

    } 
    public String get_exp(String v){

        String temp_v = v;
        int index;

        for (index = 0; index < n; index++ ) {
            if(temp_v.equals(parsers[0].variable[index])){
                break;
            }
        }
        return exp_x = x[index];

    }
    public static String finalize_exp(String[] pv){ //ensure p is correct
        String temp_s;// the p we want to replace
        for (int i = 0; i < pv.length; i++){
            if (pv[i] != null){
                temp_s = p[i];
                exp_x = exp_x.replaceAll(temp_s, pv[i]);
            }
        }
        return exp_x;
    }
    public static void Elimination(){  //消元
        for(int k=1;k<=n-1;k++)
        {
            //Wrap(k);
            for(int i=k+1;i<=n;i++)
            {
                String l="("+a[i][k]+")"+ "/" + "("+a[k][k]+")";
                a[i][k]="0";
                for(int j=k+1;j<=n;j++)
                    a[i][j]= "("+a[i][j]+")" + "-" + l+ "*" +"("+a[k][j]+")";
                b[i]="("+b[i]+")"+ "-"+l+"*"+"("+b[k]+")";
            }
            //System.out.println("第"+k+"次消元");
            PrintA();
        }
                
    }
    public static void Back()//回代
    {
        x[n]="("+b[n]+")"+"/"+"("+a[n][n]+")";
        for(int i=n-1;i>=1;i--)
            x[i]="("+b[i]+"-"+"("+jisuan(i)+")"+")/"+"("+a[i][i]+")";
    }
    public static String jisuan(int i){
        String he="0";
        for(int j=i+1;j<=n;j++)
            he=he+"+"+x[j]+"*"+"("+a[i][j]+")";
        return he;
    }

    public static void PrintA(){//输出增广矩阵
        System.out.println("增广矩阵为：");
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
                System.out.print(a[i][j]+"    ");
            System.out.print(b[i]+"    ");
            System.out.print("\n");
        }
    }
    public static void Print(){//输出方程的根
        System.out.println("Solutions of equations");
        for(int i=1;i<=n;i++)
            System.out.println("x"+i+" = "+x[i]);
    }
    /*public static void main(String[] args) {
        
        //test
        //this.p = {"a","b","c","d","e"};
        //exp_x = "((+e)-(+c)/(+b)*(+a))/((+d)-(+c)/(+b)*(+a))";   
       // String[] temp = {"1","2","3",null,"5"};
        //String res = finalize_exp(temp);
        //System.out.println(res);

        //Parser pas = new Parser("-ax1+bx2+c+bx1+my+mx3+x4-x=x+b");  
        
        Scanner as=new Scanner(System.in);
        System.out.println("Please input the number of variables: "); // 输入变量数
        //n=as.nextInt();
        // return nextline
        //as.nextLine();
        String aaa=as.nextLine();
        n=Integer.parseInt(aaa);
        //initialize
        System.out.println(aaa);
        a=new String[n+1][n+1]; //matrix
        b=new String[n+1]; //constant
        x=new String[n+1]; 
        //pass variable names 
        v = new String[n];
        System.out.println("Please input the name of variables: ");
        for (int i = 0; i < n; i++) {
            v[i] = as.nextLine();
        }
        //set the number of parameters 
        System.out.println("Please input the number of parameters: "); // 输入变量数
        //pn=as.nextInt();
        // return nextline
        //as.nextLine();
        String bbb=as.nextLine();
        pn=Integer.parseInt(bbb);
        //pass parameter names 
        p = new String[pn];
        System.out.println("Please input the name of parameters: ");
        for (int i = 0; i < pn; i++) {
            p[i] = as.nextLine();
        }
        // pass equations into array
        e = new String[n];
        parsers = new Parser[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Input the equations: ");
            e[i] = as.nextLine();
            System.out.println(e[i]);
            // handle the empty case 
            while (e[i].equals("")){
                System.out.println("The input equation is empty!!!");
                e[i] = as.nextLine();
            }
            parsers[i] = new Parser(e[i],v, p);
            for (int j = 1; j < n+1 ; j++) {
                a[i+1][j] = parsers[i].coes[j-1];
            }
            b[i+1] = parsers[i].coes[n];
            System.out.println(b[i+1]);
            System.out.println(Arrays.toString(a[i+1]));//输出数组
            
        }
        Elimination();
        Back();
        //which variable we want to explore
        System.out.println("which variable we want to plot: "); //find the y axies
        String temp_v = as.nextLine();
        //System.out.println(temp_v);
        int index;
        for (index = 0; index < n; index++ ) {
            if(temp_v.equals(parsers[0].variable[index])){
                break;
            }
        }
        exp_x = x[index+1];

        System.out.println(exp_x);

        //input the value of parameters that is not in consider
        System.out.println("which parameters we want to explre: "); //find the x axies
        String temp_p = as.nextLine();
        for (index = 0; index < pn; index++){
            if(temp_p.equals(parsers[0].parameter[index])){
                break;
            }
        }

        String[] change={"first","second","third","fourth","fifth"};

        for (int z = 0 ; z < pn ; z++ )
        {
            if (z != index)
            {
                exp_x = exp_x.replaceAll(parsers[0].parameter[z],change[z]);
            }

        }

        System.out.println(exp_x);

        Print();
        //Determinant();
        //Parser pas = new Parser("-ax1+bx2+c+bx1+my+mx3+x4-x=x+b");

    }*/
}
