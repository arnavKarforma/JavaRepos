
public class ReverseString {

	
	public static void main(String[] args) {
		String str1 = "Arnava";
		String str2 = "avanra";
		//Better way is using charAt
		char [] str2Char = str2.toCharArray();
int i =0;
		while(i < (str2.length())/2){
			char ch  = str2Char[i];
			str2Char[i] = str2Char[str2.length()-i-1];
			str2Char[str2.length()-i-1] = ch;
			i++;
		}
			
		System.out.println(String.valueOf(str2Char));
	}

}
