import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;
                int cntLines = 0;
                List<LogEntry> logEntries = new ArrayList<>();
                Statistics statistics = new Statistics();
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length > 1024) {
                        throw new RuntimeException("Строка длиннее 1024 символа. Длина строки = " + length);
                    }
                    LogEntry logEntry = new LogEntry(line);
                    statistics.addEntry(logEntry);
                    logEntries.add(logEntry);
                    cntLines++;
                }
                System.out.println("Общее кол-во строк в файле = " + cntLines);
                System.out.println("Общий объем трафика:" + statistics.getTotalTraffic());
                System.out.println("Средний объем трафика в час:" + statistics.getTrafficRate());
                System.out.println("Минимальное время:"+ statistics.getMinTime());
                System.out.println("Максимальное время:" + statistics.getMaxTime());
                System.out.println("Всего часов:" + Duration.between(statistics.getMinTime(), statistics.getMaxTime())
                        .getSeconds() / 3600);
                System.out.println("logEntries.get(10000):"+logEntries.get(10000));
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