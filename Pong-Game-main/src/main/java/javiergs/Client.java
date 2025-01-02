package javiergs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {
	
	private static final String IP = "localhost";
	private static final int PORT = 8888;
	
	private final ObjectOutputStream outputStream;
	private final ObjectInputStream inputStream;
	private boolean isReady;
	
	public Client() throws IOException {
		Socket socket = new Socket(IP, PORT);
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(socket.getInputStream());
		isReady = true;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 / 30);
				receive();
				send();
			} catch (Exception e) {
				//throw new RuntimeException(e);
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
			PongBrain.getInstance().setServerPlayerY(pongData.getServerPlayerY());
			// create an engine for the pong game, i,e, the ball position logic
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean isReady() {
		return isReady;
	}
	
}