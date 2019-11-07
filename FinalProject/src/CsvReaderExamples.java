import java.io.File;
import java.util.Scanner;

public class CsvReaderExamples {

	public static String readAllExample() throws Exception {
		// Reader reader = Files.newBufferedReader(Paths.get(
		// ClassLoader.getSystemResource("csv/jacob_Sentiments.CSV").toURI()));
		Scanner scanner = new Scanner(new File(new File("").getAbsolutePath()+"\\src\\csv\\jacob_Sentiments.CSV"));
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		CsvReaderExamples.readAllExample();
	}
}
