public class Task {
    private String description;
    private boolean completed;

    //Constructor
    public Task(String description) {
        this.description = description;
        this.completed = false; // default status for not completed
    }
    //Getter for description
    public String getDescription() {
        return description;
    }

    //Getter for status
    public boolean isCompleted() {
        return completed;
    }

    //Setter for status
    public void markCompleted(){
        this.completed= true;
    }

    //Converting Task to String
    public String toString(){
        return description + " | " + (completed ? "Done" : "In progress");
    }

    public static Task fromString(String taskString) {
        String[] parts = taskString.split(" \\| "); // Split by " | "

        if (parts.length < 2) { // Ensure the array has at least 2 parts
//            System.out.println("Skipping invalid task format: " + taskString);
            return new Task(taskString); // Load as an incomplete task
        }

        Task task = new Task(parts[0]); // First part is description

        if (parts[1].trim().equalsIgnoreCase("Done")) { // Trim whitespace & ignore case
            task.markCompleted();
        }

        return task;
    }



}
