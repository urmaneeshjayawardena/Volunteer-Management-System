import java.util.Scanner;

public class VolunteerSystem {

    // Linked List
    private Node head;

    // Queue
    private Node front;
    private Node rear;

    // Stack
    private Node top;

    // ---------------------------------
    // LINKED LIST METHODS
    // ---------------------------------

    public boolean volunteerExists(int id) {
        Node current = head;

        while (current != null) {
            if (current.volunteer.getVolunteerId() == id) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public Volunteer findVolunteerById(int id) {
        Node current = head;

        while (current != null) {
            if (current.volunteer.getVolunteerId() == id) {
                return current.volunteer;
            }
            current = current.next;
        }

        return null;
    }

    public Volunteer findVolunteerByName(String name) {
        Node current = head;

        while (current != null) {
            if (current.volunteer.getName().equalsIgnoreCase(name)) {
                return current.volunteer;
            }
            current = current.next;
        }

        return null;
    }

    public void addVolunteerToList(Volunteer volunteer) {
        Node newNode = new Node(volunteer);  // create node for new volunteer

        if (head == null) {                  // if list is empty
            head = newNode;                  // new volunteer becomes first node
            return;
        }

        Node current = head;                 // start traversal from head

        while (current.next != null) {       // move until last node
            current = current.next;
        }

        current.next = newNode;              // attach new node at the end of list
    }

    public Volunteer removeVolunteerFromList(int id) {
        if (head == null) {
            return null;
        }

        if (head.volunteer.getVolunteerId() == id) {
            Volunteer removed = head.volunteer;
            head = head.next;
            return removed;
        }

        Node prev = head;
        Node current = head.next;

        while (current != null) {
            if (current.volunteer.getVolunteerId() == id) {
                prev.next = current.next;
                return current.volunteer;
            }

            prev = current;
            current = current.next;
        }

        return null;
    }

    public void displayAllVolunteers() {
        if (head == null) {
            System.out.println("No volunteers found.");
            return;
        }

        System.out.println("=== All Volunteers ===");

        Node current = head;
        while (current != null) {
            System.out.println(current.volunteer);
            current = current.next;
        }
    }

    public void searchVolunteerById(int id) {
        Volunteer volunteer = findVolunteerById(id);

        if (volunteer == null) {
            System.out.println("Volunteer not found.");
        } else {
            System.out.println("Volunteer found:");
            System.out.println(volunteer);
        }
    }

    public void searchVolunteerByName(String name) {
        Volunteer volunteer = findVolunteerByName(name);

        if (volunteer == null) {
            System.out.println("Volunteer not found.");
        } else {
            System.out.println("Volunteer found:");
            System.out.println(volunteer);
        }
    }

    // ---------------------------------
    // QUEUE METHODS
    // ---------------------------------

    public void enqueue(Volunteer volunteer) {
        Node newNode = new Node(volunteer);    // create queue node

        if (rear == null) {                    // if queue is empty
            front = rear = newNode;            // first volunteer in queue
            return;
        }

        rear.next = newNode;                   // insert at rear
        rear = newNode;                        // update rear pointer
    }

    public void removeFromQueueById(int id) {
        if (front == null) {
            return;
        }

        while (front != null && front.volunteer.getVolunteerId() == id) {
            front = front.next;
        }

        if (front == null) {
            rear = null;
            return;
        }

        Node prev = front;
        Node current = front.next;

        while (current != null) {
            if (current.volunteer.getVolunteerId() == id) {
                prev.next = current.next;

                if (current == rear) {
                    rear = prev;
                }

                current = prev.next;
            } else {
                prev = current;
                current = current.next;
            }
        }
    }

    public void displayQueue() {
        if (front == null) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("=== Waiting Volunteers Queue ===");

        Node current = front;
        while (current != null) {
            System.out.println(current.volunteer);
            current = current.next;
        }
    }

    // ---------------------------------
    // STACK METHODS
    // ---------------------------------

    public void pushAction(String type, Volunteer volunteer) {
        Volunteer copy = new Volunteer(                   // create copy of volunteer
                volunteer.getVolunteerId(),
                volunteer.getName(),
                volunteer.getPriorityLevel()
        );

        copy.setAssigned(volunteer.isAssigned());         // save assigned state
        copy.setTaskAssigned(volunteer.getTaskAssigned());// save task

        Node newNode = new Node(type, copy);              // create stack node
        newNode.next = top;                               // link previous top
        top = newNode;                                    // update top pointer
    }

    public Node popAction() {
        if (top == null) {
            return null;
        }

        Node temp = top;
        top = top.next;
        return temp;
    }

    // ---------------------------------
    // VALIDATION METHODS
    // ---------------------------------

    public boolean isValidPriority(String priority) {
        return priority.equalsIgnoreCase("LOW")
                || priority.equalsIgnoreCase("MEDIUM")
                || priority.equalsIgnoreCase("HIGH");
    }

    public int getPriorityValue(String priority) {
        if (priority.equalsIgnoreCase("HIGH")) {
            return 3;
        } else if (priority.equalsIgnoreCase("MEDIUM")) {
            return 2;
        } else {
            return 1;
        }
    }

    public int readInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public String readNonEmptyString(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    public String readPriority(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim().toUpperCase();

            if (isValidPriority(input)) {
                return input;
            }

            System.out.println("Invalid priority. Enter LOW, MEDIUM, or HIGH.");
        }
    }

    // ---------------------------------
    // SYSTEM METHODS
    // ---------------------------------

    public void registerVolunteer(int id, String name, String priority) {
        if (volunteerExists(id)) {
            System.out.println("Volunteer ID already exists.");
            return;
        }

        if (!isValidPriority(priority)) {
            System.out.println("Invalid priority. Volunteer not registered.");
            return;
        }

        Volunteer volunteer = new Volunteer(id, name, priority);
        addVolunteerToList(volunteer);
        enqueue(volunteer);

        System.out.println("Volunteer registered successfully.");
        System.out.println(volunteer);
    }

    public void assignTask(String task) {
        if (front == null) {
            System.out.println("No volunteers available.");
            return;
        }

        Node current = front;                 // start from front of queue
        Volunteer best = null;                // store best volunteer

        while (current != null) {             // check waiting volunteers
            Volunteer volunteer = current.volunteer;

            if (!volunteer.isAssigned()) {    // check availability
                if (best == null ||
                        getPriorityValue(volunteer.getPriorityLevel()) >
                                getPriorityValue(best.getPriorityLevel())) {
                    best = volunteer;         // chose higher priority
                }
            }

            current = current.next;           // move to next volunteer
        }

        if (best == null) {
            System.out.println("No unassigned volunteers available.");
            return;
        }

        pushAction("ASSIGN", best);

        best.setAssigned(true);
        best.setTaskAssigned(task);

        removeFromQueueById(best.getVolunteerId());

        System.out.println("Task assigned successfully:");
        System.out.println(best);
    }

    public void removeVolunteer(int id) {
        Volunteer volunteer = findVolunteerById(id);

        if (volunteer == null) {
            System.out.println("Volunteer not found.");
            return;
        }

        pushAction("REMOVE", volunteer);

        Volunteer removed = removeVolunteerFromList(id);
        removeFromQueueById(id);

        System.out.println("Volunteer removed successfully:");
        System.out.println(removed);
    }

    public void undoLastAction() {
        Node action = popAction();          // get most recent action

        if (action == null) {               // stack empty
            System.out.println("Nothing to undo.");
            return;
        }

        Volunteer backup = action.backup;   // saved previous state

        if (action.actionType.equals("ASSIGN")) {
            Volunteer volunteer = findVolunteerById(backup.getVolunteerId());

            if (volunteer != null) {
                volunteer.setAssigned(backup.isAssigned());           // restore assigned state
                volunteer.setTaskAssigned(backup.getTaskAssigned());  // restore task

                if (!volunteer.isAssigned()) {
                    enqueue(volunteer);
                }
            }

            System.out.println("Last assignment undone.");
        } else if (action.actionType.equals("REMOVE")) {
            Volunteer restored = new Volunteer(
                    backup.getVolunteerId(),
                    backup.getName(),
                    backup.getPriorityLevel()
            );

            restored.setAssigned(backup.isAssigned());
            restored.setTaskAssigned(backup.getTaskAssigned());

            addVolunteerToList(restored);

            if (!restored.isAssigned()) {
                enqueue(restored);
            }

            System.out.println("Last removal undone.");
            System.out.println(restored);
        }
    }

    // ---------------------------------
    // MENU
    // ---------------------------------

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== Volunteer System ====");
            System.out.println("1 Register Volunteer");
            System.out.println("2 Assign Task");
            System.out.println("3 Remove Volunteer");
            System.out.println("4 View All Volunteers");
            System.out.println("5 View Queue");
            System.out.println("6 Search Volunteer by ID");
            System.out.println("7 Search Volunteer by Name");
            System.out.println("8 Undo Last Action");
            System.out.println("9 Exit");

            choice = readInt(sc, "Choice: ");

            switch (choice) {
                case 1:
                    int id = readInt(sc, "ID: ");
                    String name = readNonEmptyString(sc, "Name: ");
                    String priority = readPriority(sc, "Priority (LOW/MEDIUM/HIGH): ");
                    registerVolunteer(id, name, priority);
                    break;

                case 2:
                    String task = readNonEmptyString(sc, "Task: ");
                    assignTask(task);
                    break;

                case 3:
                    int removeId = readInt(sc, "Volunteer ID: ");
                    removeVolunteer(removeId);
                    break;

                case 4:
                    displayAllVolunteers();
                    break;

                case 5:
                    displayQueue();
                    break;

                case 6:
                    int searchId = readInt(sc, "Enter Volunteer ID: ");
                    searchVolunteerById(searchId);
                    break;

                case 7:
                    String searchName = readNonEmptyString(sc, "Enter Volunteer Name: ");
                    searchVolunteerByName(searchName);
                    break;

                case 8:
                    undoLastAction();
                    break;

                case 9:
                    System.out.println("Exiting the System");
                    break;

                default:
                    System.out.println("Invalid option. Please choose from the menu.");
            }

        } while (choice != 9);
    }
}