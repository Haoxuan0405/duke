package TasksList;

import Task.*;
import UI.Ui;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TasksList {
    public ArrayList<Task> tasksList;

    public TasksList(){
        this.tasksList = new ArrayList<>();
    }

    /**
     * To add all tasks from user input into tasksList
     * @param task a Task object
     */
    public void taskAdd(Task task){
        tasksList.add(task);
        Ui.Echo("Got it. I've added this task: \n  " + task +
                "\n Now you have " + tasksList.size() + " tasks in the list.");
    }
    /**
     * Mark task from taskList
     * @param index , mark a specified task number
     */
    public void mark(int index){
        Task task = tasksList.get(index);
        task.Mark();
        Ui.Echo("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * unMark task from taskList
     * @param index , unmark a specified task number
     */
    public void unmark(int index){
        Task task = tasksList.get(index);
        task.unMark();
        Ui.Echo("OK, I've marked this task as not done yet:\n  " + task);
    }

    /**
     * delete task from taskList
     * @param index , delete a specified task number
     */
    public void deleteTask(int index){
        Task task = tasksList.get(index);
        tasksList.remove(index);
        Ui.Echo("Noted. I've removed this task:\n  " + task +
                "\n Now you have " + tasksList.size() + " tasks in the list.");
    }

    /**
     * All tasks in the tasks list have been keyed in by user converted to file save pattern, and saved in disk.
     * @return String, as a parameter to writeToFile method
     */
    public String tasksAddInFile(){
        StringBuilder content = new StringBuilder();
        for(Task tasks : tasksList){
            content.append(tasks.contentToFile());
        }
        return content.toString();
    }

    /**
     * Parse all tasks(String) from disk file to Task object
     *
     * @param nextLine each line of tasks saved in disk file
     * @return a Task object
     */
    public static Task taskByLine(String nextLine) {
        String[] body = nextLine.split(" \\| ");
        Task task = null;
        switch (body[0]) {
            case "T":
                task = new Todo(body[2]);
                if (body[1].equals("1")) {
                    task.Mark();
                }
                break;
            case "D":
                task = new Deadline(body[2],
                        LocalDateTime.parse(body[3].replace("T"," "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                if (body[1].equals("1")) {
                    task.Mark();
                }
                break;
            case "E":
                task = new Event(body[2],
                        LocalDateTime.parse(body[3].replace("T"," "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                if (body[1].equals("1")) {
                    task.Mark();
                }
                break;
        }
        return task;
    }

    /**
     * To load all tasks from disk file and add into tasksList
     *
     * @param task a Task object
     */
    public void loadTask(Task task){
        tasksList.add(task);
    }

    /**
     * Print and show all tasks in the list to User
     */
    public void showToUser() {
        String space = " ";
        Ui.showLine();
        System.out.println(space + "Here are the tasks in your list:");
        int taskNum = 0;
        for (Task tasks : tasksList) {
            taskNum++;
            System.out.println(space + taskNum + "." + tasks.toString());
        }
        Ui.showLine();
    }
}

