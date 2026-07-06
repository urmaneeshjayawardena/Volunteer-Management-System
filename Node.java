public class Node {

    Volunteer volunteer;  // volunteer data
    Node next;            // link to next node

    // For undo stack
    String actionType;
    Volunteer backup;

    public Node(Volunteer volunteer) {
        this.volunteer = volunteer;
        this.next = null;
        this.actionType = null;
        this.backup = null;
    }

    public Node(String actionType, Volunteer backup) {
        this.volunteer = null;
        this.next = null;
        this.actionType = actionType;
        this.backup = backup;
    }
}