package javiergs;

import java.io.Serializable;

public class PongData implements Serializable {
	
	private int ballX;
	private int ballY;
	private int clientPlayerY;
	private int serverPlayerY;
	private int direction;
	private int whoIAm;
	
	public PongData(int ballX, int ballY,
									int clientPlayerY, int serverPlayerY,
									int direction, int whoIAm) {
		this.ballX = ballX;
		this.ballY = ballY;
		this.clientPlayerY = clientPlayerY;
		this.serverPlayerY = serverPlayerY;
		this.direction = direction;
		this.whoIAm = whoIAm;
	}
	
	public PongData() {
		this(0, 0, 0, 0, 0, 0);
	}
	
	public String toString() {
		return "Ball: (" + ballX + ", " + ballY + ") " +
			"Client: " + clientPlayerY + " " +
			"Server: " + serverPlayerY + " " +
			"Direction: " + direction + " " +
			"WhoAmI: " + whoIAm;
	}
	
	public int getBallX() {
		return ballX;
	}
	
	public int getBallY() {
		return ballY;
	}
	
	public int getClientPlayerY() {
		return clientPlayerY;
	}
	
	public int getServerPlayerY() {return serverPlayerY;}
	
	public int getWhoAmI() {
		return whoIAm;
	}
	
	public void setClientPlayerY(int y) {
		clientPlayerY = y;
	}
	
	public void setServerPlayerY(int y) {
		serverPlayerY = y;
	}
	
	public void setWhoAmI(int w) {
		whoIAm = w;
	}
	
	public void setBallX(int ballX) {this.ballX = ballX;}
	
	public void setBallY(int ballY) {this.ballY = ballY;}
	
	public void setDirection(int direction) {this.direction = direction;}
	
	public void setWhoIAm(int whoIAm) {this.whoIAm = whoIAm;}
	
	public int getDirection() {return direction;}
	
	public PongData clone() {
		return new PongData(ballX, ballY, clientPlayerY, serverPlayerY, direction, whoIAm);
	}
	
}