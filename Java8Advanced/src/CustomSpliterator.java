import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomSpliterator {

	public static void main(String[] args) {

		Path path = Paths.get("Person.txt");

		try (Stream<String> lines = Files.lines(path)) {
			Spliterator<String> lineSpliterator = lines.spliterator();
			Spliterator<PersonDTO> personSpliterator = new PersonSpliterator(lineSpliterator);
			Stream<PersonDTO> personStream = StreamSupport.stream(personSpliterator, false);
			personStream.forEach(System.out::println);
		} catch (IOException e) {

		}
List<String> ar = Arrays.asList("one","two","three");

List<String> ar2 = new ArrayList<>();

Consumer<String> c1 = System.out::println;
Consumer<String> c2 = ar2::add;

ar.forEach(c1.andThen(c2));
ar2.forEach(c1);

	}

}
