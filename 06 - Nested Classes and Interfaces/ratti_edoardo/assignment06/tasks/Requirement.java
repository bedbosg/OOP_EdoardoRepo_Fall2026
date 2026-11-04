package assignment06.tasks;

public class Requirement implements Task {
    private final Workpackage workpackage;
    private final String id;
    private final String title;
    private final String description;
    private final int priority;
    private final String acceptanceCriterion;
    private boolean done;

    public Requirement(Workpackage workpackage, String title, String description, int priority, String acceptanceCriterion) {
        this.workpackage = workpackage;
        this.id = workpackage.nextTaskId("REQ");
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.acceptanceCriterion = acceptanceCriterion;
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
        return id + " | Requirement | " + title
                + " | priority=" + priority
                + " | " + (done ? "DONE" : "TO DO")
                + " | " + description
                + " | acceptance=" + acceptanceCriterion
                + " | workpackage=" + workpackage.getName();
    }
}
