import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String YANDEX_BOT = "YandexBot";
    public static final String GOOGLE_BOT = "GoogleBot";

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
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;
                int cntLines = 0;
                int cntYandexBot = 0;
                int cntGoogleBot = 0;
                List<Integer> cntLength = new ArrayList<>();
                List<Line> lines = new ArrayList<>();
                LineHelper lineHelper = new LineHelper();
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    cntLength.add(length);
                    if (length > 1024)
                        throw new RuntimeException("Строка длиннее 1024 символа. Длина строки = " + length);

                    Line stringLine = lineHelper.getLineFromString(line);
                    lines.add(stringLine);
                    cntLines++;
                    String bot = lineHelper.getBot(stringLine.getUserAgent());
                    if (bot.equalsIgnoreCase(YANDEX_BOT)) cntYandexBot++;
                    if (bot.equalsIgnoreCase(GOOGLE_BOT)) cntGoogleBot++;
                }
                System.out.println("Общее кол-во строк в файле = " + cntLines);
                System.out.println("Количество " + GOOGLE_BOT + ": " + cntGoogleBot);
                System.out.println("Количество " + YANDEX_BOT + ": " + cntYandexBot);
                System.out.println("Доля " + GOOGLE_BOT + " от общего числа строк: "
                        + lineHelper.getShereOtTotal(cntLines, cntGoogleBot) + "%");
                System.out.println("Доля " + YANDEX_BOT + " от общего числа строк: "
                        + lineHelper.getShereOtTotal(cntLines, cntYandexBot) + "%");
                System.out.println("===========");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getMaxCnt(List<Integer> list) {
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int tmp = list.get(i);
            max = tmp > max ? tmp : max;
        }
        return max;
    }

    public static int getMinCnt(List<Integer> list) {
        int min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int tmp = list.get(i);
            min = tmp < min ? tmp : min;
        }
        return min;
    }
}