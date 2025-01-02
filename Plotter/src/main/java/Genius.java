/**
 * It generates random values and adds them to the board.
 *
 * @author javiergs
 * @version 1.0
 */
public class Genius implements Runnable {

    public void run() {
        while (true) {
            Board.getInstance().addValue((int) (Math.random() * 180) + 10);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}