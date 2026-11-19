import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadLines {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, System.console() != null ? System.console().charset() : StandardCharsets.UTF_8));
        String str;
        System.out.println("Вводите строки текста.");
        System.out.println("Для завершення введіть 'stop'.");
        do {
            str = br.readLine();
            System.out.println(str);
        } while (!str.equals("stop"));
    }
}