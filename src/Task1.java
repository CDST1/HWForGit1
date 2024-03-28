import java.util.Arrays;
//TODO:
// •	Создайте массив целых чисел.
// •	Разделите его на две части.
// •	Создайте два потока для поиска максимального и минимального элементов в этих частях.
// •	Верните результаты поиска.

public class Task1 {
    public static void main(String[] args) {

        int[] numbers = {1, 14, 15, 22, 72, 44, 26, 63, 62, 52};
        int[] firstSub = Arrays.copyOfRange(numbers, 0, 5);
        int[] secondSub = Arrays.copyOfRange(numbers, 5, 10);

        Thread firstSubThread = new Thread(() -> {
            int firstMin = Arrays.stream(firstSub).min().orElse(Integer.MAX_VALUE);
            int firstMax = Arrays.stream(firstSub).max().orElse(Integer.MIN_VALUE);
            System.out.println("Минимальный элемент в первой части: " + firstMin);
            System.out.println("Максимальный элемент в первой части: " + firstMax);
        });

        Thread secondSubThread = new Thread(() -> {
            int secondMin = Arrays.stream(secondSub).min().orElse(Integer.MAX_VALUE);
            int secondMax = Arrays.stream(secondSub).max().orElse(Integer.MIN_VALUE);
            System.out.println("Минимальный элемент во второй части: " + secondMin);
            System.out.println("Максимальный элемент во второй части: " + secondMax);
        });

        firstSubThread.start();
        secondSubThread.start();
    }
}