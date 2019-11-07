package advancedApplicationProgramming.BusinessLogicImpl.blackboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import blackboard.BinaryNode;
import blackboard.BinaryNodeInterface;

public class WriteToBST<T> {
	private static Scanner stdin = new Scanner(System.in);
	private BinaryNodeInterface<T> rootHere;
	static BinaryNode<String> root;
	String[] inputString = new String[10];

	@SuppressWarnings("unchecked")
	private static BinaryNode<String> privateSetTree() {
		BinaryNode<String> root;
		BinaryNode<String> child;

		final String ROOT_QUESTION = "Are you a mammal?";
		final String LEFT_QUESTION = "Are you bigger than a cat?";
		final String RIGHT_QUESTION = "Do you live underwater?";
		final String ANIMAL1 = "Kangaroo";
		final String ANIMAL2 = "Mouse";
		final String ANIMAL3 = "Trout";
		final String ANIMAL4 = "Robin";

		// Create the root node with the question ?Are you a mammal??
		root = new BinaryNode<String>(ROOT_QUESTION, null, null);

		// Create and attach the left subtree.
		child = new BinaryNode<String>(LEFT_QUESTION, null, null);
		child.setLeftChild(new BinaryNode<String>(ANIMAL1, null, null));
		child.setRightChild(new BinaryNode<String>(ANIMAL2, null, null));
		root.setLeftChild(child);

		// Create and attach the right subtree.
		child = new BinaryNode<String>(RIGHT_QUESTION, null, null);
		child.setLeftChild(new BinaryNode<String>(ANIMAL3, null, null));
		child.setRightChild(new BinaryNode<String>(ANIMAL4, null, null));
		root.setRightChild(child);

		return root;

	}

	public static void instruct() {
		System.out.println("Please think of an animal.");
		System.out.println("I will ask some yes/no questions to try to figure");
		System.out.println("out what you are.");
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		instruct();
		root = privateSetTree();
		do
			play(root);
		while (query("Shall we play again?"));
		WriteToBST<String> ws = new WriteToBST<>();
		PrintStream output = new PrintStream(new File("bstInput.txt"));
		ws.write(output);
		ws.readFromFile("bstInput.txt");
		System.out.println("Thanks for teaching me a thing or two.");

	}

	public static boolean query(String prompt) {
		String answer;

		System.out.print(prompt + " [Y or N]: ");
		answer = stdin.nextLine().toUpperCase();
		while (!answer.startsWith("Y") && !answer.startsWith("N")) {
			System.out.print("Invalid response. Please type Y or N: ");
			answer = stdin.nextLine().toUpperCase();
		}

		return answer.startsWith("Y");
	}

	public static void play(BinaryNode<String> current) {
		while (!current.isLeaf()) {
			if (query(current.getData()))
				current = (BinaryNode<String>) current.getLeftChild();
			else
				current = (BinaryNode<String>) current.getRightChild();
		}

		System.out.print("My guess is " + current.getData() + ". ");
		if (!query("Am I right?"))
			learn(current);
		else
			System.out.println("I knew it all along!");
	}

	public static void learn(BinaryNode<String> current)
	// Precondition: current is a reference to a leaf in a taxonomy tree. This
	// leaf contains a wrong guess that was just made.
	// Postcondition: Information has been elicited from the user, and the tree
	// has been improved.
	{
		String guessAnimal; // The animal that was just guessed
		String correctAnimal; // The animal that the user was thinking of
		String newQuestion; // A question to distinguish the two animals

		// Set Strings for the guessed animal, correct animal and a new
		// question.
		guessAnimal = current.getData();
		System.out.println("I give up. What are you? ");
		correctAnimal = stdin.nextLine();
		System.out.println("Please type a yes/no question that will distinguish a");
		System.out.println(correctAnimal + " from a " + guessAnimal + ".");
		newQuestion = stdin.nextLine();

		// Put the new question in the current node, and add two new children.
		current.setData(newQuestion);
		System.out.println("As a " + correctAnimal + ", " + newQuestion);
		if (query("Please answer")) {
			current.setLeftChild(new BinaryNode<String>(correctAnimal, null, null));
			current.setRightChild(new BinaryNode<String>(guessAnimal, null, null));
		} else {
			current.setLeftChild(new BinaryNode<String>(guessAnimal, null, null));
			current.setRightChild(new BinaryNode<String>(correctAnimal, null, null));
		}
	}

	public void write(PrintStream output) {
		printPreorder((BinaryNodeInterface<T>) root, output);
	}

	void printPreorder(BinaryNodeInterface<T> node, PrintStream output) {
		if (node == null)
			return;

		/* first print data of node */
		output.println(node.getData());

		/* then recur on left sutree */
		printPreorder(node.getLeftChild(), output);

		/* now recur on right subtree */
		printPreorder(node.getRightChild(), output);
	}

	private void readFromFile(String Path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(Path));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			int i = 0;
			while (line != null) {
				inputString[i] = line;
				i++;
				line = br.readLine();
			}
		} finally {
			br.close();
		}

	}

}
