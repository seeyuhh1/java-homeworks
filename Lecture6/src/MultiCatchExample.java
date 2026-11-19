public class MultiCatchExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
            char[] chars = {'A', 'B', 'C'};
            System.out.println(chars[5]);
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Перехоплено виключення: " + e);
        } finally {
            System.out.println("Завершення роботи програми.");
        }
    }
}