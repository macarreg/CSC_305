import java.awt.event.*;

public class GameController implements ActionListener {
    private final GameData gameData;

    public GameController(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("hint")) {
            String hint = gameData.getHint();
            System.out.println(hint);
            gameData.notifyObservers();
        }
    }
    public boolean moveDisk(int from, int to) {
        if (gameData.makeMove(from, to)) {
            gameData.checkWinCondition();
            return true;
        }
        return false;
    }
}
