package practise.singlepage;

public class CommonIntegerInArray {
	private static int[] input1 = { 2, 7, 17, 19, 20, 34, 56, 159, 239 };
	private static int[] input2 = { 7, 12, 15, 19, 22, 34, 55, 150, 159 };

	public static void main(String[] args) {
		int len1 = input1.length;
		int len2 = input2.length;
		int index1 = 0;
		int index2 = 0;
		while (true) {
			if (index1 + 1 > len1 || index2 + 1 > len1) {
				break;
			}
			if (input1[index1] == input2[index2]) {
				System.out.println(input2[index2]);
				index1++;
				index2++;
			} else if (input1[index1] < input2[index2]) {
				index1++;
			} else {
				index2++;
			}

		}

	}
}
