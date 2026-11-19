import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadChars {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, System.console() != null ? System.console().charset() : StandardCharsets.UTF_8));
        char c;
        System.out.println("Вводите символи; для виходу введіть крапку.");
        do {
            c = (char) br.read();
            System.out.println(c);
        } while (c != '.');
    }
}