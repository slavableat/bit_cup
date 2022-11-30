import thread_tasks.readTask.ReadTask;
import thread_tasks.taskController.TaskController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        processConcoleArgsAndExecuteTasks(args);
    }

    private static void processConcoleArgsAndExecuteTasks(String[] args) {
        String fileName = "";
        Map<String, Boolean> flags = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String parameter = args[i];
            if ("-trace".equals(parameter)) {
                flags.put("-trace", true);
                System.out.println("trace");
            } else if ("-s".startsWith(parameter)) {
                Integer nextArg = getNextArgument(args, i++);
                if (Objects.nonNull(nextArg)) System.out.println("-s " + nextArg);
                flags.put("-s", true);
            } else if ("-d".startsWith(parameter)) {
                Integer nextArg = getNextArgument(args, i++);
                if (Objects.nonNull(nextArg)) System.out.println("-d " + nextArg);
                flags.put("-d", true);
            } else {
                fileName = parameter;
            }
        }
        if(!fileName.equals("")){
            TaskController taskController = new TaskController(flags, fileName);
            taskController.run();
        }
    }

    private static Integer getNextArgument(String[] args, int i) {
        String secondParameterString = args[i + 1];
        try {
            Integer scndParameter = Integer.parseInt(secondParameterString);
            return scndParameter;
        } catch (NumberFormatException ex) {
            System.out.println(ex.getStackTrace());
            return null;
        }
    }
}
