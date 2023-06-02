import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int cnt = 0;
        while (true) {
            System.out.println("Введите путь к файлу:");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("Файл не найден по указанному пути!!!");
            } else if (file.isDirectory()) {
                System.out.println("Указан путь к папке, а не файлу!!!");
            } else {
                System.out.println("Путь указан верно!");
                System.out.println("Количество верно указанных путей: " + ++cnt);
            }
        }
    }
}