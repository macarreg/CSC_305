package javiergs;

public class PongBrain {
	
	public static final int SERVER = 0;
	public static final int CLIENT = 1;
	public static final int RIGHT = 0;
	public static final int LEFT = 1;
	
	private static PongBrain instance;
	
	private PongData pongData;
	
	private PongBrain() {
		pongData = new PongData(400,300, 250, 250, RIGHT, SERVER);
	}
	
	public static PongBrain getInstance() {
		if (instance == null) {
			instance = new PongBrain();
		}
		return instance;
	}
	
	public int getBallX() {
		return pongData.getBallX();
	}
	
	public void setBallX(int ballX) {
		pongData.setBallX(ballX);
	}
	
	public int getBallY() {
		return pongData.getBallY();
	}
	
	public void setBallY(int ballY) {
		pongData.setBallY(ballY);
	}
	
	public int getClientPlayerY() {
		return pongData.getClientPlayerY();
	}
	
	public int getServerPlayerY() {
		return pongData.getServerPlayerY();
	}
	
	public int getWhoAmI() {
		return pongData.getWhoAmI();
	}
	
	public void setClientPlayerY(int y) {
		pongData.setClientPlayerY(y);
	}
	
	public void setServerPlayerY(int y) {
		pongData.setServerPlayerY(y);
	}
	
	public void moveBall() {
		if (pongData.getDirection() == RIGHT)
			pongData.setBallX(pongData.getBallX() + 10);
		else
			pongData.setBallX(pongData.getBallX() - 10);
		if (pongData.getBallX() >= 800 || pongData.getBallX() <= 0)
			pongData.setBallX(400);
		if (collision()) {
			pongData.setDirection((pongData.getDirection() == RIGHT) ? LEFT : RIGHT);
		}
	}
	
	private boolean collision() {
		if (pongData.getBallX() == 20 &&
			  pongData.getBallY() >= pongData.getServerPlayerY() &&
			  pongData.getBallY() <= pongData.getServerPlayerY() + 50)
			return true;
		if (pongData.getBallX() == 780 &&
			  pongData.getBallY() >= pongData.getClientPlayerY() &&
			  pongData.getBallY() <= pongData.getClientPlayerY() + 50)
			return true;
		return false;
	}
	
	public void setWhoAmI(int client) {
		pongData.setWhoAmI(client);
	}
	
	public PongData getPongData() {
		return pongData;
	}
	
}