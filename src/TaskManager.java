import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class TaskManager {
    private static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = loadTaskFromFile();

        System.out.println("Welcome to Task manager :))");

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark task as completed");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:
                    System.out.println("Enter a task to add: ");
                    String task = scanner.nextLine();
                    tasks.add(new Task(task));
                    saveTasksToFile(tasks);
                    System.out.println("Task added!");
                    break;

                case 2:
                    System.out.println("\nYour tasks:");
                    for (Task t : tasks) {
                        System.out.println("- " + t);
                    }
                    break;

                case 3:
                    deleteTask(tasks);
                    break;

                case 4:
                    System.out.println("Enter task number to mark as completed: ");
                    int taskNumber = scanner.nextInt();
                    scanner.nextLine();

                    if(taskNumber < 1 || taskNumber > tasks.size()){
                        System.out.println("Invalid task number. Try again.");

                    }else{
                        tasks.get(taskNumber -1).markCompleted();
                        saveTasksToFile(tasks);
                        System.out.println("Task marked as completed! Great Job :)))");
                    }
                    break;

                case 5:
                    System.out.println("Goodbye. Sad to see you go :(((");
                    scanner.close();
                    return; // Exit the program

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static ArrayList<Task> loadTaskFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String task;

            while ((task = reader.readLine()) != null) {
                tasks.add(Task.fromString(task));

            }

        } catch (IOException e) { //handle errors while reading file

        }
        return tasks;
    }

    private static void saveTasksToFile(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println();
        }


    }

    private static void deleteTask(ArrayList<Task> tasks) {
        Scanner scanner = new Scanner(System.in);
        if(tasks.isEmpty()){
            System.out.println("There are no tasks to delete. ");
            return;
        }

        System.out.println("Select the number of task you want to delete");
        for(int i =0; i < tasks.size(); i++){
            System.out.println((i +1 ) + ". " + tasks.get(i));
        }

        System.out.println("Enter number of the task you want to remove: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine();

        if(taskNumber <1 || taskNumber > tasks.size()){
            System.out.println("Invalid task number. Try again.");

        }
        else{
            Task removedTask = tasks.remove(taskNumber -1);
            saveTasksToFile(tasks);
            System.out.println("Deleted task: " + removedTask);
        }


    }
}
