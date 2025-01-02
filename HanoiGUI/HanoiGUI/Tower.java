import java.util.*;

public class Tower {
    private final Stack<Integer> disks;

    public Tower() {
        this.disks = new Stack<>();
    }

    public void initialize(int numDisks) {
        for (int i = numDisks; i >= 1; i--) {
            disks.push(i);
        }
    }

    public boolean isEmpty() {
        return disks.isEmpty();
    }

    public int peek() {
        return disks.isEmpty() ? -1 : disks.peek();
    }

    public void moveDiskTo(Tower target) {
        if (!disks.isEmpty() && (target.isEmpty() || disks.peek() < target.peek())) {
            target.disks.push(disks.pop());
        }
    }

    public Stack<Integer> getDisks() {
        return disks;
    }
}
