import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class ScannerTests {
    @Test
    public void whenReadFileWithScanner_thenCorrect() throws IOException {
        Scanner scanner = new Scanner(new File("src/test/resources/test.txt"));

        assertTrue(scanner.hasNext());
        assertEquals("Hello", scanner.next());
        assertEquals("world", scanner.next());

        assertFalse(scanner.hasNext());
        scanner.close();
    }

    @Test
    public void whenConvertInputStreamToString_thenConverted()
            throws IOException {
        String expectedValue = "Hello world";
        FileInputStream inputStream
                = new FileInputStream("src/test/resources/test.txt");

        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("A");

        String result = scanner.next();
        assertEquals(expectedValue, result);

        scanner.close();
    }

    @Test
    public void whenReadUsingBufferedReader_thenCorrect()
            throws IOException {
        String firstLine = "Hello world";
        String secondLine = "Hi, John";
        BufferedReader reader
                = new BufferedReader(new FileReader("src/test/resources/testlines.txt"));

        String result = reader.readLine();
        assertEquals(firstLine, result);

        result = reader.readLine();
        assertEquals(secondLine, result);

        reader.close();
    }

    @Test
    public void whenReadUsingScanner_thenCorrect()
            throws IOException {
        String firstLine = "Hello world";
        FileInputStream inputStream
                = new FileInputStream("src/test/resources/testlines.txt");
        Scanner scanner = new Scanner(inputStream);

        String result = scanner.nextLine();
        assertEquals(firstLine, result);

        scanner.useDelimiter(", ");
        assertEquals("Hi", scanner.next());
        assertEquals("John", scanner.next());

        scanner.close();
    }

    @Test
    public void whenReadingInputFromConsole_thenCorrect() {
        String input = "Hello";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);

        String result = scanner.next();
        assertEquals(input, result);

        System.setIn(stdin);
        scanner.close();
    }
}

