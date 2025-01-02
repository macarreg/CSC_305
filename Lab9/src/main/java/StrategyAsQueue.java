import java.util.ArrayList;

public class StrategyAsQueue implements Strategy {

    public int[] algorithm(ArrayList<Node> nodes ) {
        int[] order = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            order[i] = i;
        }
        return order;
    }

}
