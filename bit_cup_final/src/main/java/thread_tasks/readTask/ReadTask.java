package thread_tasks.readTask;

import thread_tasks.storage_task.StorageTask;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadTask extends Thread {
    private StorageTask storageTask;
    private String fileName;

    public ReadTask(StorageTask storageTask, String fileName) {
        this.storageTask = storageTask;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        StringBuffer stringBuffer = new StringBuffer();
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);

            Scanner scan = new Scanner(fr);
            int i = 1;

            while (scan.hasNextLine()) {
                stringBuffer.append(scan.nextLine()).append("\n");
                i++;
            }
            fr.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        storageTask.setStorage(stringBuffer.toString());
    }
}
