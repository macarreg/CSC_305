import java.awt.*;

class Box {
    private final int x, y, size;
    private String mark = "";  // Either "X", "O", or "" (empty)
    private boolean filled = false;  // Track if the box is already marked

    public Box(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, size, size);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);

        // Draw "X" or "O"
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString(mark, x + size / 4, y + 3 * size / 4);
    }

    public boolean contains(int px, int py) {
        return px >= x && px <= x + size && py >= y && py <= y + size;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setMark(String mark) {
        this.mark = mark;
        this.filled = true;
    }

    public String getMark() {
        return mark;
    }
}
