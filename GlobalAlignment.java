import java.text.DecimalFormat;

public class GlobalAlignment {
	
	public String res;
	public String s;
	public String t;
	private String str1;
	private String str2;
	private String strMatch;
	private String strSub;
	private String strIndle;
	public double gam[][]; 
	public GlobalAlignment(String s1, String s2, String match,String sub,String indle) {
		this.str1=s1;
		this.str2=s2;
		this.strMatch=match;
		this.strSub=sub;
		this.strIndle=indle;
		this.gam = new double[getStr2().length()+1][getStr1().length()+1]; 
		this.s="";
		this.t="";
		this.res="";
	}
	
	public String getRes() {
		return this.res;
	}
	public String getS() {
		return this.s;
	}
	public String getT() {
		return this.t;
	}
	public String getIndle() {
		return this.strIndle;
	}
	public String getMatch() {
		return this.strMatch;
	}
	public String getSub() {
		return this.strSub;
	}
	public String getStr1() {
		return this.str1;
	}
	public String getStr2() {
		return this.str2;
	}
	
	
	public void MakeMatrix() {
		int m = getStr1().length()+1;
		int n = getStr2().length()+1;
		double indle = Double.parseDouble(getIndle());
		double num=0;
		for (int i=0 ; i<n ; i++) {
			this.gam[i][0]=num;
			num=num+indle;
		}
		num=0;
		for (int i=0 ; i<m ; i++) {
			this.gam[0][i]=num;
			num=num+indle;
		}
	}
	
	public void FullMatrix(){
		int m = getStr1().length()+1;
		int n = getStr2().length()+1;
		double indle = Double.parseDouble(getIndle());
		double match = Double.parseDouble(getMatch());
		double mismatch = Double.parseDouble(getSub());
		for (int i=1;i<n;i++) {
			for (int j=1;j<m;j++) {
				String s1=this.str1.substring(j-1,j);
				String s2=this.str2.substring(i-1,i);
				if (s1.equals(s2)) {
					if (( this.gam[i-1][j-1] + match >= this.gam[i-1][j]+indle )&&
						( this.gam[i-1][j-1] + match >= this.gam[i][j-1]+indle )) {
						this.gam[i][j]=this.gam[i-1][j-1] + match;
					}
					else if (( this.gam[i-1][j]+indle >= this.gam[i-1][j-1] + match )&&
						( this.gam[i-1][j]+indle >= this.gam[i][j-1]+indle )) {
						this.gam[i][j]=this.gam[i-1][j]+indle;
					}
					else if (( this.gam[i][j-1]+indle >= this.gam[i-1][j-1] + match )&&
						( this.gam[i][j-1]+indle >= this.gam[i-1][j]+indle )) {
						this.gam[i][j]=this.gam[i][j-1]+indle;
					}
					
				}
				else {
					if (( this.gam[i-1][j-1] + mismatch >= this.gam[i-1][j]+indle )&&
						( this.gam[i-1][j-1] + mismatch >= this.gam[i][j-1]+indle )) {
						this.gam[i][j]=this.gam[i-1][j-1] + mismatch;
					}
					else if (( this.gam[i-1][j]+indle >= this.gam[i-1][j-1] + mismatch )&&
						( this.gam[i-1][j]+indle >= this.gam[i][j-1]+indle )) {
						this.gam[i][j]=this.gam[i-1][j]+indle;
					}
					else if (( this.gam[i][j-1]+indle >= this.gam[i-1][j-1] + mismatch )&&
						( this.gam[i][j-1]+indle >= this.gam[i-1][j]+indle )) {
						this.gam[i][j]=this.gam[i][j-1]+indle;
					}
				}
				
				
			}
		}
	}
	
	public void makePath() {
		String str1="",str2="";
		int m = getStr1().length();
		int n = getStr2().length();
		while ((n!=0)&&(m!=0)) {
			String s1=Character.toString(this.str1.charAt(m-1));
			String s2=Character.toString(this.str2.charAt(n-1));
			if (s1.equals(s2)) {
				if (gam[n][m]-gam[n-1][m-1]==Double.parseDouble(getMatch())) {
					str1=str1+s1;
					str2=str2+s2;
					m--;
					n--;
				}
				else if (gam[n][m]-gam[n][m-1]==Double.parseDouble(getIndle())) {
					str1=str1+s1;
					str2=str2+"-";
					m--;
				}
				else if (gam[n][m]-gam[n-1][m]==Double.parseDouble(getIndle())) {
					str1=str1+"-";
					str2=str2+s2;
					n--;
				}
				
			}
			else {
				if (gam[n][m]-gam[n-1][m-1]==Double.parseDouble(getSub())) {
					str1=str1+s1;
					str2=str2+s2;
					m--;
					n--;
				}
				else if (gam[n][m]-gam[n][m-1]==Double.parseDouble(getIndle())) {
					str1=str1+s1;
					str2=str2+"-";
					m--;
				}
				else if (gam[n][m]-gam[n-1][m]==Double.parseDouble(getIndle())) {
					str1=str1+"-";
					str2=str2+s2;
					n--;
				}
				
			}

		}
		
		if ((m==0)&&(n!=0)) {
			while (n>0) {
				String s2=Character.toString(this.str2.charAt(n-1));
				str1=str1+"-";
				str2=str2+s2;
				n--;
				
			}
		}
		
		if ((m!=0)&&(n==0)) {
			while (m>0) {
				String s1=Character.toString(this.str1.charAt(m-1));
				str1=str1+s1;
				str2=str2+"-";
				m--;
				
			}
		}
		
		if ((m==0)&&(n==0)) {
			str1=new StringBuilder(str1).reverse().toString();
			str2=new StringBuilder(str2).reverse().toString();
			this.s=str1;this.t=str2;
			String str3="";
			int size=str1.length();
			for (int i=0;i<size;i++) {
				if ((Character.toString(str1.charAt(i)).equals(Character.toString(str2.charAt(i)))&&
						!(Character.toString(str1.charAt(i)).equals("-"))&&
						!(Character.toString(str2.charAt(i)).equals("-")))){
					str3=str3+"|";
				}
				else if (!(Character.toString(str1.charAt(i)).equals(Character.toString(str2.charAt(i))))&&
						!(Character.toString(str1.charAt(i)).equals("-"))&&
						!(Character.toString(str2.charAt(i)).equals("-"))){
					str3=str3+"*";
				}
				else {
					str3=str3+" ";
				}
			}
			res=str3;
			 
		}
		
	}
	public double calcScore() {
		double score=0;
		double match=Double.parseDouble(getMatch());
		double mismatch=Double.parseDouble(getSub());
		double indle=Double.parseDouble(getIndle());
		int size=res.length();
		for (int i=0;i<size;i++) {
			if ((Character.toString(res.charAt(i))).equals("|")) {
				score=score+match;
			}
			else if (((Character.toString(res.charAt(i))).equals(" "))) {
				score=score+indle;
			}
			else if (((Character.toString(res.charAt(i))).equals("*"))) {
				score=score+mismatch;
			}
			
		}
		return score;
	}
	
	public int calcMatch() {
		int count=0;
		int size=res.length();
		for (int i=0;i<size;i++) {
			if ((Character.toString(res.charAt(i))).equals("|")) {
				count++;
			}
		}
		return count;
	}
	
	public int calcSub() {
		int count=0;
		int size=res.length();
		for (int i=0;i<size;i++) {
			if ((Character.toString(res.charAt(i))).equals("*")) {
				count++;
			}
		}
		return count;
	}
	
	public int calcIndle() {
		int count=0;
		int size=s.length();
		for (int i=0;i<size;i++) {
			if ((Character.toString(s.charAt(i))).equals("-")) {
				count++;
			}
		}
		size=t.length();
		for (int i=0;i<size;i++) {
			if ((Character.toString(t.charAt(i))).equals("-")) {
				count++;
			}
		}
		return count;
	}
	public int calcGap() {
		int count=0;
		int size=s.length();
		for (int i=0;i<size;i++) {
			if ((Character.toString(s.charAt(i))).equals("-")) {
				count++;
				if (i+1<size) {
					while((i<size)&&(Character.toString(s.charAt(i+1))).equals("-")){
						i++;
						if (i+1==size)
							break;
					}
				}
			}
		}
		size=t.length();
		for (int i=0;i<size;i++) {
			if ((Character.toString(t.charAt(i))).equals("-")) {
				count++;
				if (i+1<size) {
					while((Character.toString(t.charAt(i+1))).equals("-")&&(i<size)){
						i++;
						if (i+1==size)
							break;
					}
				}
				
			}
		}
		return count;
	}
	
	
	public void printMatrix() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		int m = getStr1().length()+1;
		int n = getStr2().length()+1;
		System.out.println("The matrix of Global Alignment");
		for (int i=0; i<n;i++) {
			for (int j=0; j<m;j++) {
				System.out.print(df.format(gam[i][j])+" ");
			}
			System.out.println("");
		}
		System.out.println("\n");
	}

}
