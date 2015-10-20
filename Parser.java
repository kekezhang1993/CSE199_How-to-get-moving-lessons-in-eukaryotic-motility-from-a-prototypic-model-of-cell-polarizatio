import java.io.*;
import java.lang.Math;
import java.util.*;
import java.lang.*;

public class Parser{
	
	public boolean equalsign = false;
	public String[] variable;
	public String[] parameter;
	public String[] parts; // split the equations into parts
	public String[] coes;
	public int[] flags;// flags of whether constant 

	//public String [][] coeMatrix = new String[variable.length][variable.length]; // the matrix of the coefficients
	//public String [] signs= [+,-,=];
	//public int sign_size = signs.size();

	public Parser (){
		//empty constructor
	}
	/*create a contructor for parser*/
	public Parser (String in, String[] v_array, String[] p_array){

		variable = v_array;
		parameter = p_array;

		/* set the proirity of the variables */
		String temp;
		for (int i = 0; i < variable.length-1; i ++){
			for(int j = 0; j<variable.length-1-i;j++){
				if (variable[j].length()<variable[j+1].length()){
					temp = variable[j+1];
					variable[j+1] = variable[j];
					variable[j] = temp;
				}
			}
		}

		parts = split(in);
		get_coe(parts);
		//coeMatrix = get_coe(parts);
	}
	/* tester constructor*/
	public Parser(String in){

		String temp;
		for (int i = 0; i < variable.length-1; i ++){
			for(int j = 0; j<variable.length-1-i;j++){
				if (variable[j].length()<variable[j+1].length()){
					temp = variable[j+1];
					variable[j+1] = variable[j];
					variable[j] = temp;
				}
			}
		}
		System.out.println("original array: "+ Arrays.toString(variable));

		parts = split(in);
		get_coe(parts);
	}

	public String[] split (String ori){
		String temp;
		String divide_by_equal[];
		divide_by_equal = ori.split("\\=");

		//deal with the lhs
		String[] lhs = divide_by_equal[0].split("\\-");
		//if(divide_by_equal[0].charAt(0) == '-')
			//lhs[1] = "-" +lhs[1];
		for(int i = 1; i < lhs.length; i++){
			lhs[i] = "-" + lhs[i];
		}
		String[] temp_minus;
		String[] parts;
		int strLen1, strLen2;

		parts = lhs[0].split("\\+");
		
		for (int i = 1; i < lhs.length; i ++){
			temp_minus = lhs[i].split("\\+");
			strLen1=parts.length;
			strLen2=temp_minus.length;
			
			parts= Arrays.copyOf(parts,strLen1+ strLen2);//扩容
			System.arraycopy(temp_minus, 0, parts, strLen1,strLen2 );//将第二个数组与第一个数组合并
		}

				
		int j; 
		//deal with the rhs
		String[] rhs = divide_by_equal[1].split("\\+");
		if(divide_by_equal[1].charAt(0) == '-')
			j=1;
		else
			j=0;
		for(; j < rhs.length; j++){
			rhs[j] = "+" + rhs[j];
		}
		String[] r_parts = rhs[0].split("\\-");
		
		for (int i = 1; i < rhs.length; i ++){
			temp_minus = rhs[i].split("\\-");
			strLen1=r_parts.length;
			strLen2=temp_minus.length;
			r_parts= Arrays.copyOf(r_parts,strLen1+ strLen2);//扩容
			System.arraycopy(temp_minus, 0, r_parts, strLen1,strLen2 );//将第二个数组与第一个数组合并
		}
		for(int i = 0; i < r_parts.length; i++){
			r_parts[i] = r_parts[i].replace('+','-');
		}

		//combine lhs and rhs
		strLen1=parts.length;
		strLen2=r_parts.length;
		parts= Arrays.copyOf(parts,strLen1+ strLen2);//扩容
		System.arraycopy(r_parts, 0, parts, strLen1,strLen2 );//将第二个数组与第一个数组合并

		System.out.println(Arrays.toString(parts));//输出数组

		return parts;

	}

	public void get_coe(String[] part){
		String temp_coe;
		coes = new String[variable.length+1];
		int min_length;
		flags = new int[parts.length];
			for(int z=0;z<variable.length+1;z++){

				coes[z]="";
			}
		/* find the coefficients of the ith variable*/
		for(int i = 0; i < variable.length; i++){
			/*compare jth char of the viarable name */
			for(int j = 0; j < variable[i].length(); j++){
				/*compare with the kth part of the equation*/
				for (int k = 0; k < part.length ; k++) {

					/*start to compare with the mth char of the kth part*/
					for (int m = 0; m <part[k].length() ;m ++ ) {
						/* check whether this part has been dealt with */
						if(flags[k] == 1){
							//System.out.println(k);
							break;
						}
						if (variable[i].charAt(j) == part[k].charAt(m)){
							/* set the bound for the for_loop */
							min_length = Math.min(variable[i].length()-j,part[k].length()-m);
							/* compare the rest of the variable name */
							for (int l = 0; l < min_length; l++){
								/* fail to find */
								if (variable[i].charAt(j+l) != part[k].charAt(m+l))
									break;
								/* successfully find the variable */
								else if(l==variable[i].length()-1){
									if(part[k].charAt(0) == '-')
										coes[i]=coes[i]+part[k].substring(0,m)+part[k].substring(m+l+1); // combine
									else
										coes[i]=coes[i]+"+"+part[k].substring(0,m)+part[k].substring(m+l+1);
									flags[k] = 1;//set this part is coes
								}

							}
						}
					}
				}
				
			}
		}
		/* combine all the constants */
		for(int i = 0; i< flags.length; i++){
			if(flags[i]!=1 && part[i].length() >0){
				if(part[i].charAt(0) == '-')
					coes[variable.length]+= "+"+part[i].substring(1); // combine
				else
					coes[variable.length]+="-"+part[i];
			}
			
		}
		/* go through the array of coefficients to get rid of two signs */
		for (int i = 0; i < coes.length; i++){
			for(int j = 0; j < coes[i].length(); j++){
				if ((coes[i].charAt(j) == '+'||coes[i].charAt(j) == '-') && j == coes[i].length()-1){
					coes[i] += "1";
				}
				else if( (coes[i].charAt(j) == '+' && coes[i].charAt(j+1) == '+')
					||(coes[i].charAt(j) == '-' && coes[i].charAt(j+1) == '-')
					||(coes[i].charAt(j) == '+' && coes[i].charAt(j+1) == '-')
					||(coes[i].charAt(j) == '-' && coes[i].charAt(j+1) == '+')){
					coes[i] = coes[i].substring(0,j+1) + "1" + coes[i].substring(j+1);
				}
			}
		}
		System.out.println("coes: "+ Arrays.toString(coes));//输出数组
	}










}
