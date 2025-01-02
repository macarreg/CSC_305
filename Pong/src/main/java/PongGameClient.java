
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Main class for the Pong Game Client.
 * It creates the GUI, starts the client and connect to server.
 *
 * @author javiergs
 * @version 1.0
 */
public class PongGameClient extends JFrame {
	
	public PongGameClient() {
		setLayout(new GridLayout(1, 1));
		PongPanel gamePanel = new PongPanel();
		add(gamePanel);
	}
	
	public static void main(String[] args) {
		PongGameClient pongGame = new PongGameClient();
		// look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Connect to the server
		message("Client starting ....", JOptionPane.INFORMATION_MESSAGE);
		try {
			pongGame.connect();
		} catch (IOException e) {
			message("Client cannot connected.", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		message ("Client connected.", JOptionPane.INFORMATION_MESSAGE);
		// Create the GUI
		pongGame.setTitle("Pong Game Client | An Example of TCP Sockets in Java");
		pongGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pongGame.setSize(800, 600);
		pongGame.setResizable(false);
		pongGame.setLocation(800, 0);
		pongGame.setVisible(true);
	}
	
	protected void connect() throws IOException {
		PongBrain.getInstance().setWhoAmI(PongBrain.CLIENT);
		Client client = new Client();
		if (client.isReady()) {
			Thread thread = new Thread(client);
			thread.start();
		}
	}
	
	private static void message (String text, int icon) {
		JOptionPane.showMessageDialog(null, text, "Client", icon);
	}
	
}
