
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	private static final int PORT = 8888;
	
	private final ObjectOutputStream outputStream;
	private final ObjectInputStream inputStream;
	private boolean isReady;
	
	
	public Server() throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		Socket clientSocket = serverSocket.accept();
		outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
		inputStream = new ObjectInputStream(clientSocket.getInputStream());
		isReady = true;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 / 30);
				send();
				receive();
			} catch (Exception e) {
				// throw new RuntimeException(e);
			}
		}
	}
	
	private void send() throws IOException {
		PongData pongData = PongBrain.getInstance().getPongData().clone();
		outputStream.writeObject(pongData);
		outputStream.flush();
	}
	
	private void receive() {
		try {
			PongData pongData = (PongData) inputStream.readObject();
			PongBrain.getInstance().setClientPlayerY(pongData.getClientPlayerY());
			// create an engine for the pong game, i,e, the ball position logic
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean isReady() {
		return isReady;
	}
	
}