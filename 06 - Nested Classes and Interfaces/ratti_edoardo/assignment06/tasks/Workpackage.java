package assignment06.tasks;

import java.util.ArrayList;
import java.util.List;

public class Workpackage {
    private final String name;
    private int nextNumber = 1;
    private final List<Task> tasks = new ArrayList<>();

    public Workpackage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Allocate an ID *within this workpackage*, not globally.
    public String nextTaskId(String prefix) {
        return prefix + "-" + nextNumber++;
    }

    public void add(Task task) {
        if (task.getWorkpackage() != this) {
            throw new IllegalArgumentException("Task belongs to a different workpackage");
        }
        tasks.add(task);
        tasks.sort(new TaskPriorityComparator());
    }

    public void completeTask(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                if (task.isDone()) {
                    System.out.printf("Task '%s is already DONE in '%s'.%n", title, name);
                } else {
                    task.complete();
                    System.out.printf("Task '%s marked DONE in '%s'.%n", title, name);
                }
                return;
            }
        }
        System.out.printf("Task '%s not found in '%s'.%n", title, name);
    }

    public void listTasks() {
        System.out.printf("%nWorkpackage '%s'%n", name);
        for (Task task : tasks) {
            System.out.println("  " + task.describe());
        }
    }
}
