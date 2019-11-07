
public class IsBst {
 
	private static Node value ;

	
	private static boolean isBst(){
		return isBst(Integer.MIN_VALUE, value, Integer.MAX_VALUE);
	}
	private static boolean isBst(int min, Node value, int max){
		if(value == null)return true;
		if(value.data < min || value.data > max)return false;
		return isBst(min,value.left,value.data)&& isBst(value.data+1,value.right,max);
	}
	
	public static void main(String[] args) {
		    value = new Node(4); 
	        value.left = new Node(2); 
	        value.right = new Node(5); 
	        value.left.left= new Node(1); 
	        value.left.right = new Node(3); 
	        if (isBst()) 
	            System.out.println("IS BST"); 
	        else
	            System.out.println("Not a BST"); 
	  
	}
}
