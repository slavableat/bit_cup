package thread_tasks.storage_task;

import java.util.List;

public class StorageTask {
    public String getStorage() {
        return storage.toString();
    }

    private StringBuffer storage = new StringBuffer();

    public void setStorage(String storage) {
        this.storage = new StringBuffer(storage);
    }
}
