package practice.ds.BST;

public class BstUtil {

	private static BstDTO rootNode;
	double amount = 0;

	private void insert(int data) {
		System.out.print("[input: " + data + "]");
		if (rootNode == null) {
			rootNode = new BstDTO(data);
			System.out.println();
			return;
		}
		binaryTree(data, rootNode);
		System.out.print(" -> inserted: " + data);
		System.out.println();
	}


	
	private BstDTO testBinaryTree() {
		rootNode = (new BstDTO(3));
				rootNode.setLeftNode(new BstDTO(6));
				rootNode.setRightNode(new BstDTO(8));
				rootNode.getRightNode().setRightNode(new BstDTO(13));
				rootNode.getRightNode().getRightNode().setLeftNode(new BstDTO(7));
				rootNode.getLeftNode().setLeftNode(new BstDTO(2));
				rootNode.getLeftNode().setRightNode(new BstDTO(11));
				rootNode.getLeftNode().getRightNode().setRightNode(new BstDTO(5));
				rootNode.getLeftNode().getRightNode().setLeftNode(new BstDTO(9));
		return rootNode;
	}
	private void binaryTree(Integer data, BstDTO node) {
		int random = (int) (Math.random() * 20 + 1);
		if (random % 2 == 0) {
			System.out.print(" [L]");
			if (node.getLeftNode() == null) {
				node.setLeftNode(new BstDTO(data));
				System.out.print(" node->" + node.getData());
				return;
			} else {
				System.out.print(" traversing->" + node.getData());
				binaryTree(data, node.getLeftNode());
			}
		}

		else {
			if (node.getRightNode() == null) {
				System.out.print(" [R]");
				node.setRightNode(new BstDTO(data));
				System.out.print(" node->" + node.getData());
				return;
			} else
				System.out.print(" traversing->" + node.getData());
				binaryTree(data, node.getRightNode());
		}
	}
	int i= 0;
	
	private Integer lowestAncestor(BstDTO node, Integer left, Integer right) {
		i++;
		if(node==null){
			System.out.println("node -> "+ null +" "+"i-> "+ i );
			return null;
		}
		System.out.println("node -> "+ node.getData() +" "+"i-> "+ i );
		if(node.getData() == left || node.getData() == right){
			System.out.println("node matched -> "+ node.getData() +" "+"i-> "+ i );
		return 	node.getData();
		}
		Integer leftData = lowestAncestor(node.getLeftNode(), left, right); 
		Integer rightData = lowestAncestor(node.getRightNode(), left, right);
		
		if (leftData != null &&  rightData !=null){
			System.out.println(leftData+" "+rightData+" "+node.getData()+" "+"i-> "+i);
			return node.getData();
		}
		if(leftData == null &&  rightData ==null)
			return null;
         return leftData == null ?rightData : leftData;
		

	}

	private void insertInTree(Integer data, BstDTO node) {

		if (node.getData() >= data) {
			System.out.print(" [L]");
			if (node.getLeftNode() == null) {
				node.setLeftNode(new BstDTO(data));
				System.out.print(" node->" + node.getData());
				return;
			} else {
				System.out.print(" traversing->" + node.getData());
				insertInTree(data, node.getLeftNode());
			}
		} else if (node.getData() <= data) {
			System.out.print(" [R]");
			if (node.getRightNode() == null) {
				node.setRightNode(new BstDTO(data));
				System.out.print(" node->" + node.getData());
				return;
			} else {
				System.out.print(" traversing->" + node.getData());
				insertInTree(data, node.getRightNode());
			}
		}
	}

	private boolean contains(Integer data) {
		return contains(data, rootNode);
	}

	private boolean contains(Integer data, BstDTO bstDto) {
		boolean found = false;
		while (bstDto != null) {
			if (bstDto.getData() == data) {
				found = true;
				break;
			}
			if (bstDto.getData() > data) {
				bstDto = bstDto.getLeftNode();
			} else
				bstDto = bstDto.getRightNode();
		}
		return found;
	}

	private void delete(Integer data) {
		deleteNode(data, rootNode);
	}

	private BstDTO deleteNode(Integer data, BstDTO bstDto) {
		if (bstDto == null) {
			return null;
		}

		if (bstDto.getData() < data) {
			bstDto.setRightNode(deleteNode(data, bstDto.getRightNode()));
		} else if (bstDto.getData() > data) {
			bstDto.setLeftNode(deleteNode(data, bstDto.getLeftNode()));
		} else {
			if (bstDto.getLeftNode() == null && bstDto.getRightNode() == null) {
				return null;
			}
			if (bstDto.getLeftNode() == null) {
				return bstDto.getRightNode();
			} else if (bstDto.getRightNode() == null) {
				return bstDto.getLeftNode();
			} else if (bstDto.getLeftNode() != null && bstDto.getRightNode() != null) {
				BstDTO tempNode = bstDto.getRightNode();
				Integer tempData = tempNode.getData();
				while (tempNode.getLeftNode() != null) {
					tempNode = tempNode.getLeftNode();
					tempData = tempNode.getData();
				}
				bstDto.setData(tempData);
				bstDto.setRightNode(deleteNode(tempData, bstDto.getRightNode()));
			}

		}

		return bstDto;

	}

	private void executeTreeTraversal(BstDTO bsdto) {
		if (bsdto == null)
			return;
		executeTreeTraversal(bsdto.getLeftNode());
		System.out.print(bsdto.getData() + " ");
		executeTreeTraversal(bsdto.getRightNode());
	}

	private void treeTraversal() {
		executeTreeTraversal(this.rootNode);
	}

	public boolean isBST(BstDTO n) {
		return isBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST(BstDTO n, int min, int max) {
		if (n == null)
			return true;
		if (n.getData() < min || n.getData() > max)
			return false;
		System.out.println("________________________________________");
		System.out.println("data-> " + n.getData() + " min -> " + min + " max -> " + max);

		return isBST(n.getLeftNode(), min, n.getData()) && isBST(n.getRightNode(), n.getData(), max);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BstUtil bst = new BstUtil();
		/*bst.insert(8);
		bst.insert(3);
		bst.insert(10);
		bst.insert(14);
		bst.insert(6);
		bst.insert(1);
		bst.insert(4);
		bst.insert(7);
		bst.insert(13);
		bst.treeTraversal();

		System.out.println();
		bst.delete(1);
		bst.treeTraversal();

		System.out.println();

		System.out.println(bst.isBST(rootNode));
*/		bst.testBinaryTree();
		System.out.println(bst.lowestAncestor(rootNode, 2,5));
	}

}
     