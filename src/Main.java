import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
        //Получить из консоли первое число
        int fistNumber = new Scanner(System.in).nextInt();

        System.out.println("Введите второе число:");
        //Получить из консоли второе число
        int secondNumber = new Scanner(System.in).nextInt();

        //Сумма чисел
        int sum = fistNumber + secondNumber;
        //Разность чисел
        int dif = fistNumber - secondNumber;
        //Произведение чисел
        int multiplication = fistNumber * secondNumber;
        //Частное
        double div = (double) fistNumber / (double) secondNumber;

        //Вывод результатов в консоль
        System.out.println("Сумма чисел: " + sum);
        System.out.println("Разность чисел: " + dif);
        System.out.println("Произведение чисел: " + multiplication);
        System.out.println("Частное: " + div);
    }
}