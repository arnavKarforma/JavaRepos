import java.util.Scanner;

public class CheckAnagrams {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first String");
		String s1 = sc.nextLine();
		System.out.println("Enter second String");
		String s2 = sc.nextLine();
		System.out.println(isAnagram(s1,s2)); 
		

	}
	public static boolean isAnagram(String s1, String s2){
		if(s1.length() != s2.length()) return false;
		int letters [] = new int[256];
		for (char c: s1.toLowerCase().toCharArray()){
			letters[c] ++;
		}
		for (char c: s2.toLowerCase().toCharArray()){
			letters[c] --;
		}
		for (int l: letters){
			if(l != 0)
				return false;
		}
		return true;
	}

}
