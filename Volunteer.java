public class Volunteer {

    private int volunteerId;
    private String name;
    private String taskAssigned;
    private String priorityLevel;
    private boolean assigned;

    // Constructor
    public Volunteer(int volunteerId, String name, String priorityLevel) {
        this.volunteerId = volunteerId;
        this.name = name;
        this.priorityLevel = priorityLevel.toUpperCase();
        this.taskAssigned = "None";
        this.assigned = false;
    }

    // Getters
    public int getVolunteerId() {
        return volunteerId;
    }

    public String getName() {
        return name;
    }

    public String getTaskAssigned() {
        return taskAssigned;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public boolean isAssigned() {
        return assigned;
    }

    // Setters
    public void setTaskAssigned(String taskAssigned) {
        this.taskAssigned = taskAssigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    // Display full volunteer details
    @Override
    public String toString() {
        return "ID: " + volunteerId +
                " | Name: " + name +
                " | Priority: " + priorityLevel +
                " | Task: " + taskAssigned +
                " | Assigned: " + assigned;
    }
}