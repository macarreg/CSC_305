import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StrategyAsTravel implements Strategy {

    public int[] algorithm(ArrayList<Node> nodes ) {
        Set<Integer> visited = new HashSet<>();
        int[] order = new int[nodes.size()];
        int size = nodes.size();
        int currentIndex = 0;
        while (visited.size() < nodes.size()) {
            order[visited.size()] = currentIndex;
            Node currentNode = nodes.get(currentIndex);
            double minDistance = Double.POSITIVE_INFINITY;
            int minDistanceIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visited.contains(j)) {
                    double distance = Math.hypot( Math.abs(nodes.get(j).getX() - currentNode.getX()),
                            Math.abs(nodes.get(j).getY() - currentNode.getY()));
                    if (distance < minDistance) {
                        minDistanceIndex = j;
                        minDistance = distance;
                    }
                }
            }
            visited.add(currentIndex);
            currentIndex = minDistanceIndex;
        }
        return order;
    }

}