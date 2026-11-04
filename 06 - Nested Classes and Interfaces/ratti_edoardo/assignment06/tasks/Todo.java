package assignment06.tasks;

public class Todo implements Task {
    private final Workpackage workpackage;
    private final String id;
    private final String title;
    private final String description;
    private final int priority;
    private final int estimatedHours;
    private boolean done;

    public Todo(Workpackage workpackage, String title, String description, int priority, int estimatedHours) {
        this.workpackage = workpackage;
        this.id = workpackage.nextTaskId("TODO");
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimatedHours = estimatedHours;
    }

    @Override
    public Workpackage getWorkpackage() {
        return workpackage;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void complete() {
        done = true;
    }

    @Override
    public String describe() {
        return id + " | Todo | " + title
                + " | priority=" + priority
                + " | " + (done ? "DONE" : "TO DO")
                + " | " + description
                + " | estimate=" + estimatedHours + "h"
                + " | workpackage=" + workpackage.getName();
    }
}
