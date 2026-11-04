package assignment06.tasks;

public interface Task {
    String getTitle();

    int getPriority();

    boolean isDone();

    void complete();

    Workpackage getWorkpackage();

    String describe();
}
