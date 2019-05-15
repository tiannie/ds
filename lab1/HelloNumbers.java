public class HelloNumbers {
	public static void main(String[] args) {
		int max = 9;
		int sum = 0;
		for (int pos = 0; pos <= 9; pos+=1) {
			sum += pos;
			System.out.println(sum);
		}
	}
}