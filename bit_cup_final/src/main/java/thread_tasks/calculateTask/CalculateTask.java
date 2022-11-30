package thread_tasks.calculateTask;

import thread_tasks.storage_task.StorageTask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculateTask extends Thread {
    StorageTask storageTask;
    public CalculateTask(StorageTask storageTask){
        this.storageTask = storageTask;
    }
    @Override
    public void run() {
        List<String> list = List.of(storageTask.getStorage().split("\n"));
        list.parallelStream()
                // разбиваем строки на слова
                .flatMap(line -> Stream.of(line.split("\\s+")))
                // выкидываем небуквенные символы
                // и приводим к нижнему регистру
                .map(word -> word.replaceAll("[^А-Яа-яЁё]+", "").toLowerCase())
                // слова длиной 4 и больше букв
                .filter(word -> word.length() > 3)
                // собираем в Map<String, Integer>
                // и считаем количество вхождений
                .collect(Collectors.toMap(key -> key, val -> 1, Integer::sum))
                .entrySet().stream()
                // отсортированы
                .sorted((e1, e2) -> {
                    // в порядке убывания их кол-ва упоминаний
                    int val = e1.getValue().compareTo(e2.getValue()) * -1;
                    if (val == 0) {
                        // потом уже в алфавитном порядке
                        val = e1.getKey().compareTo(e2.getKey());
                        // слова на английском идут после русских
                        // тоже в алфавитном порядке
                        if (e1.getKey().charAt(0) <= 'z'
                                && e2.getKey().charAt(0) > 'z'
                                || e1.getKey().charAt(0) > 'z'
                                && e2.getKey().charAt(0) <= 'z') {
                            val *= -1;
                        }
                    }
                    return val;
                })
                // вывод
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }

}
