package thread_tasks.taskController;

import thread_tasks.calculateTask.CalculateTask;
import thread_tasks.readTask.ReadTask;
import thread_tasks.storage_task.StorageTask;

import java.util.Map;
import java.util.Objects;

public class TaskController {
    private StorageTask storageTask;
    private ReadTask readTask;
    private CalculateTask calculateTask;
    private Boolean isWithoutFlags = true;


    private Boolean SFlag = false;
    private final String S_FLAG_NAME = "-s";

    private Boolean DFlag = false;
    private final String D_FLAG_NAME = "-d";

    private Boolean TraceFlag = false;
    private final String TRACE_FLAG_NAME = "-trace";
    private String fileName;

    public TaskController() {
    }

    public TaskController(Map<String, Boolean> flagStatuses, String fileName) {
        if (Objects.nonNull(flagStatuses.get(S_FLAG_NAME))) {
            SFlag = flagStatuses.get(S_FLAG_NAME);
        }
        if (Objects.nonNull(flagStatuses.get(D_FLAG_NAME))) {
            DFlag = flagStatuses.get(D_FLAG_NAME);
        }
        if (Objects.nonNull(flagStatuses.get(TRACE_FLAG_NAME))) {
            TraceFlag = flagStatuses.get(TRACE_FLAG_NAME);
        }
        this.fileName = fileName;
    }

    public void run() {
        storageTask = new StorageTask();

        readTask = new ReadTask(storageTask, fileName);
        readTask.run();
        calculateTask = new CalculateTask(storageTask);
        calculateTask.run();
    }
}
