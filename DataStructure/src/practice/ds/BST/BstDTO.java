package practice.ds.BST;

public class BstDTO {

	private BstDTO rightNode;
	private BstDTO leftNode;
	private Integer data;

	
	
	public BstDTO(Integer data) {
		super();
		this.data = data;
	}

	public BstDTO getRightNode() {
		return rightNode;
	}

	public void setRightNode(BstDTO rightNode) {
		this.rightNode = rightNode;
	}

	public BstDTO getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(BstDTO leftNode) {
		this.leftNode = leftNode;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

}
