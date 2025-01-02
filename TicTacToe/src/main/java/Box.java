import java.awt.*;

class Box {
    private final int x, y, size;
    private String symbol = "";
    private boolean filled = false;

    public Box(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);

        if (!symbol.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 60));
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(symbol);
            int textHeight = fm.getAscent();

            // Center the symbol in the box
            int xCentered = x + (size - textWidth) / 2;
            int yCentered = y + (size + textHeight) / 2 - fm.getDescent();
            g.drawString(symbol, xCentered, yCentered);
        }
    }

    public boolean contains(int px, int py) {
        return px >= x && px <= x + size && py >= y && py <= y + size;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
        this.filled = true;
    }

    public String getSymbol() {
        return symbol;
    }
}
