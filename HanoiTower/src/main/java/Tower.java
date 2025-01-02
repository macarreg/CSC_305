import java.util.Stack;

public class Tower {
    private final Stack<Integer> disks = new Stack<>();

    public Tower() {}

    public Tower(Tower other) {
        this.disks.addAll(other.disks);
    }

    public void initialize(int numDisks) {
        for (int i = numDisks; i >= 1; i--) {
            disks.push(i);
        }
    }

    public boolean moveDiskTo(Tower target) {
        if (canMoveTo(target)) {
            target.push(disks.pop());
            return true;
        }
        return false;
    }

    public boolean canMoveTo(Tower target) {
        return !disks.isEmpty() && (target.isEmpty() || disks.peek() < target.peek());
    }

    public boolean isEmpty() {
        return disks.isEmpty();
    }

    public int peek() {
        return disks.peek();
    }

    public void push(int disk) {
        disks.push(disk);
    }

    public int pop() {
        return disks.pop();
    }

    public int size() {
        return disks.size();
    }

    @Override
    public String toString() {
        return disks.toString();
    }
}
