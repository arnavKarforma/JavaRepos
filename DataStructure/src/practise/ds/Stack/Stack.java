package practise.ds.Stack;
import java.util.ArrayList;

public class Stack<X> {

	private ArrayList<X> arr = new ArrayList<>();
    private int index = 0;
    
	public void push( X item) {
     arr.add(index++,item);
	}

	public X pop() {
		if(index ==0){
			throw new IllegalStateException("No any elemnt left");
		}
		return arr.get(--index);
	}

	public X search(X item) {
		while(index >0){
		X currElement = pop();
			if ((currElement+"").equals(item+"")){
				return currElement;
			}
		}
		throw new IllegalStateException("Elemnt was not found");
	}

	public boolean contains(X item) {
		for (X element :arr ){
			if (element.equals(item+"")){
				return true;
			}
		}
		return false;
	}
	
	public int size(){
		return index;
	}

}
