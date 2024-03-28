import java.util.Arrays;

//TODO:
// •	Создайте массив целых чисел.
// •	Разделите его на несколько частей.
// •	Создайте несколько потоков для вычисления суммы элементов в каждой части массива.
// •	Суммируйте результаты вычислений каждого потока и верните общую сумму.

class Calc implements Runnable {

    private final int[] numbers;
    private int sum;


    public Calc(int[] numbers) {
        this.numbers = numbers;
        this.sum = 0;
    }

    @Override
    public void run() {
        for (int num : numbers) {
            sum += num;
        }
    }

    public int getSum() {
        return sum;
    }

}


public class Task2 {
    public static void main(String[] args) throws InterruptedException {

        int[] numbers = {1, 14, 15, 22, 72, 44, 26, 63, 62, 52};
        int[] firstSub = Arrays.copyOfRange(numbers, 0, 5);
        int[] secondSub = Arrays.copyOfRange(numbers, 5, 10);

        Calc calculator1 = new Calc(firstSub);
        Calc calculator2 = new Calc(secondSub);

        Thread firstThreadSub = new Thread(calculator1);
        Thread secondThreadSub = new Thread(calculator2);

        firstThreadSub.start();
        secondThreadSub.start();

        firstThreadSub.join();
        secondThreadSub.join();

        int sumSub1 = calculator1.getSum();
        int sumSub2 = calculator2.getSum();

        System.out.println(sumSub1);
        System.out.println(sumSub2);
    }
}
