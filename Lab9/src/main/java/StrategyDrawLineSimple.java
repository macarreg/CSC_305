import java.awt.*;

public class StrategyDrawLineSimple implements StrategyDraw {

    public void drawConnect(Node node, Node b, Graphics g) {
        g.drawLine(node.center().x, node.center().y, b.center().x, b.center().y);
    }
}
