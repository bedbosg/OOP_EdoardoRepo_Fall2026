package assignment06.tasks;

public class TaskManager {
    public static void main(String[] args) {
        Workpackage w1 = new Workpackage("Requirement definition");
        Workpackage w2 = new Workpackage("Implementation");

        // Add tasks in deliberately mixed priority order.
        w1.add(new Requirement(w1, "Capture user roles", "Identify user permissions", 3, "Roles approved by stakeholders"));
        w1.add(new Todo(w1, "Review draft", "Proofread the specification", 2, 2));
        w1.add(new Todo(w1, "Interview stakeholders", "Meet customer representatives", 5, 3));
        w1.add(new Requirement(w1, "Define acceptance criteria", "Specify completion rules", 1, "All criteria reviewed"));

        w2.add(new Requirement(w2, "Persist tasks", "Store tasks between runs", 4, "Tasks survive an application restart"));
        w2.add(new Todo(w2, "Review draft", "Review the API documentation", 5, 1));
        w2.add(new Todo(w2, "Set up database", "Create the initial schema", 2, 4));
        w2.add(new Requirement(w2, "Track task status", "Report task completion", 3, "Status visible for every task"));

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
