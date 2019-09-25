
public class LocalAlignment {
	public String res;
	public String s;
	public String t;
	private String str1;
	private String str2;
	private String strMatch;
	private String strSub;
	private String strIndle;
	public double lam[][]; 
	public LocalAlignment(String s1, String s2, String match,String sub,String indle) {
		this.str1=s1;
		this.str2=s2;
		this.strMatch=match;
		this.strSub=sub;
		this.strIndle=indle;
		this.lam = new double[getStr2().length()+1][getStr1().length()+1]; 
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
		for (int i=0 ; i<n ; i++) {
			this.lam[i][0]=0;
		}
		for (int i=0 ; i<m ; i++) {
			this.lam[0][i]=0;
		}
	}

		String str1="",str2="";
		int m = getStr1().length();
		int n = getStr2().length();
		//find max value in the matrix
		double max=0;
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=m;j++) {
				if (lam[i][j]>max)
					max=lam[i][j];
			}
		}
		//find row,col with the long path
		int row=0;
		int col=0;
		int longPath=0;
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=m;j++) {
				if (lam[i][j]==max) {
					int x=i,y=j,lp=0;
					lp=0;
					while(lam[x][y]!=0) {
						lp++;
						x--;
						y--;
					}
					if (lp>longPath) {
						longPath=lp;
						row=i;
						col=j;
					}
				}
				
			}
		}
		while (lam[row][col]!=0) {
			String s1=Character.toString(this.str1.charAt(col-1));
			String s2=Character.toString(this.str2.charAt(row-1));
			str1=str1+s1;
			str2=str2+s2;
			col--;
			row--;
		}
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
	
	public double calcScore() {
		double score=0;
		double match=Double.parseDouble(getMatch());
		double mismatch=Double.parseDouble(getSub());
		double indle=Double.parseDouble(getIndle());
		int size=res.length();
		double now=0;
		for (int i=0 ;i<size;i++) {
			if ((Character.toString(res.charAt(i))).equals("|")) {
				now=now+match;
				if (now>score)
					score=now;
			}
			else if (((Character.toString(res.charAt(i))).equals(" "))&&(now>0)) {
				now=now+indle;
				if (now<0)
					now=0;
			}
			else if (((Character.toString(res.charAt(i))).equals("*"))&&(now>0)) {
				now=now+mismatch;
				if (now<0)
					now=0;
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
	
	
	
	public void printMatrix() {
		int m = getStr1().length()+1;
		int n = getStr2().length()+1;
		System.out.println("The matrix of local alignment");
		for (int i=0; i<n;i++) {
			for (int j=0; j<m;j++) {
				System.out.print(lam[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("\n");
	}

}
