package AVLTree;

public class test {
	public static int fragment5(int n) {
		int sum = 0;
		for(int i = 0; i<n; i++) {
			for (int j=0; j<n*n; j++) {
				sum++;
			}
		}
		return sum;
	}
	public static int fragment6(int n) {
		int sum = 0;
		for(int i=0; i<n; i++) {
			for(int j = 0; j<i; j++) {
				sum++;
			}
		}
		return sum;
	}
	public static int fragment7(int n) {
		int sum = 0;
		for(int i=0; i<n; i++) {
			for(int j = 0; j<n*n; j++) {
				for(int k = 0; k<j; k++) {
					sum++;
				}
			}
		}
		return sum;
	}
	public static int fivePointTwoOne(int n) {
		int sum = 0;
		for(int i=1; i<=n; i++) {
			for(int j = 1; j<=i*i; j++) {
				if(j%i==0) {
					for(int k = 0; k<j; k++) {
						sum++;
					}
				}
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(fivePointTwoOne(10));
	 
	}
}
