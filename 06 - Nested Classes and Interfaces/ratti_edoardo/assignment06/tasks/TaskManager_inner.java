package assignment06.tasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskManager_inner {
    public interface Task {
        String getTitle();

        int getPriority();

        boolean isDone();

        void complete();

        Workpackage getWorkpackage();

        String describe();
    }
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
    public class TaskPriorityComparator implements Comparator<Task> {

        @Override
        public int compare(Task task1, Task task2) {
            return Integer.compare(task2.getPriority(), task1.getPriority());
        }
    }
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
    public static void main(String[] args) {
        TaskManager_inner manager = new TaskManager_inner();
        Workpackage w1 = manager.new Workpackage("Requirement definition");
        Workpackage w2 = manager.new Workpackage("Implementation");

        // Add tasks in deliberately mixed priority order.
        w1.add(manager.new Requirement(w1, "Capture user roles", "Identify user permissions", 3, "Roles approved by stakeholders"));
        w1.add(manager.new Todo(w1, "Review draft", "Proofread the specification", 2, 2));
        w1.add(manager.new Todo(w1, "Interview stakeholders", "Meet customer representatives", 5, 3));
        w1.add(manager.new Requirement(w1, "Define acceptance criteria", "Specify completion rules", 1, "All criteria reviewed"));

        w2.add(manager.new Requirement(w2, "Persist tasks", "Store tasks between runs", 4, "Tasks survive an application restart"));
        w2.add(manager.new Todo(w2, "Review draft", "Review the API documentation", 5, 1));
        w2.add(manager.new Todo(w2, "Set up database", "Create the initial schema", 2, 4));
        w2.add(manager.new Requirement(w2, "Track task status", "Report task completion", 3, "Status visible for every task"));

        // Each workpackage should be sorted independently (highest priority first).
        // IDs start at 1 for each workpackage.
        System.out.println("\n=== Initial task lists ===");
        w1.listTasks();
        w2.listTasks();

        // Complete only the w1 task. The task with the same title in w2 stays TO DO.
        // Searching for task titles is case-insensitive.
        System.out.println("\n=== Completion tests ===");
        w1.completeTask("review DRAFT");
        w1.completeTask("Review draft");       // Already completed.
        w1.completeTask("Unknown task");       // Does not exist.
        w2.completeTask("Persist tasks");      // Task in the second workpackage.

        System.out.println("\n=== Task lists after completion ===");
        w1.listTasks();
        w2.listTasks();
    }
}
