

//TODO:
// •	Пользователь с клавиатуры вводит путь к файлу, содержащему набор чисел.
// •	После чего запускаются две потока.
// •	Первый поток создает новый файл, в который запишет только четные элементы массива.

import java.io.*;
import java.util.Scanner;

class EvenNumbersWriter extends Thread {

    private String inputFile;
    private String outputFile;

    public EvenNumbersWriter(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void run() {
        int number;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter((new FileWriter(outputFile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                number = Integer.parseInt(line);
                if (number % 2 == 0) {
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

class oddNumbersWriter extends Thread {

    private String inputFile;
    private String outputFile;

    public oddNumbersWriter(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void run() {
        int number;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter((new FileWriter(outputFile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                number = Integer.parseInt(line);
                if (number % 2 != 0) {
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


public class Task3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите путь к файлу: ");
        String inpurFile = sc.nextLine();

        String outputEvenFile = "task3Files/outputEvenFile.txt";
        String outputOddFile = "task3Files/outputOddFile.txt";

        EvenNumbersWriter evenNumbersWriter = new EvenNumbersWriter(inpurFile, outputEvenFile);
        evenNumbersWriter.start();
        oddNumbersWriter oddNumbersWriter = new oddNumbersWriter(inpurFile, outputOddFile);
        oddNumbersWriter.start();
        System.out.println();

        try {
            evenNumbersWriter.join();
            oddNumbersWriter.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        sc.close();
    }
}
