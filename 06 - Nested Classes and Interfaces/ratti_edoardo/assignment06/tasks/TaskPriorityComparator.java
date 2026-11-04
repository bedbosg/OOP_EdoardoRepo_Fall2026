package assignment06.tasks;

import java.util.Comparator;

// Higher numbers represent higher priority; sort them first.
public class TaskPriorityComparator implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        return Integer.compare(task2.getPriority(), task1.getPriority());
    }
}
