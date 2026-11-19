public class UniversityProcessing {
    public static void genException() {
        int[] scores = { 10, 20, 30, 40, 50 };
        int[] divisors = { 2, 0, 5 };

        for (int i = 0; i < scores.length; i++) {
            try {
                System.out.println(scores[i] + " / " + divisors[i] + " = " + scores[i] / divisors[i]);
            } catch (ArithmeticException exc) {
                System.out.println("Ділення на нуль неможливо!");
            } catch (ArrayIndexOutOfBoundsException exc) {
                System.out.println("Елемент не знайдено в масиві!");
                throw exc;
            }
        }
    }

    public static void main(String[] args) {
        try {
            genException();
        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println("Невиправна помилка - програма завершена.");
        }
    }
}