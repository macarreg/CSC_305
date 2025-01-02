package javiergs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


/**
 * This class is a simple JPanel that will be used to display the Bars and Ball.
 *
 * @author javiergs
 * @version 1.0
 */
public class PongPanel extends JPanel implements ActionListener,MouseMotionListener {
	
	public PongPanel() {
		addMouseMotionListener(this);
		setBackground(new Color(172, 248, 199));
		Timer timer = new Timer(1000/30, this);
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawCenterLine(g);
		drawBall(g);
		drawClientPlayer(g);
		drawServerPlayer(g);

	
	}
	
	private void drawCenterLine(Graphics g) {
		g.setColor(Color.WHITE);
		// make dashed line
		Graphics2D g2d = (Graphics2D) g;
		float[] dash1 = {10.0f};
		BasicStroke dashed = new BasicStroke(
			1.0f,
			BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER,
			10.0f,
			dash1,
			0.0f);
		g2d.setStroke(dashed);
		g.drawLine(400, 0, 400, 600);
		g2d.setStroke(new BasicStroke());
	}
	
	private void drawClientPlayer(Graphics g) {
		int y = PongBrain.getInstance().getClientPlayerY();
		if (PongBrain.getInstance().getWhoAmI() == PongBrain.CLIENT)
			g.setColor(Color.DARK_GRAY);
		else
			g.setColor(Color.WHITE);
		g.fillRect(780, y, 10, 50);
	}
	
	private void drawServerPlayer(Graphics g) {

		int y = PongBrain.getInstance().getServerPlayerY();
		if (PongBrain.getInstance().getWhoAmI() == PongBrain.SERVER)
		 g.setColor(Color.DARK_GRAY);
		else
			g.setColor(Color.WHITE);
			g.fillRect(10, y, 10, 50);
	}
	
	private void drawBall(Graphics g) {
		int x = PongBrain.getInstance().getBallX();
		int y = PongBrain.getInstance().getBallY();
		if (x == 400)
			g.setColor(new Color(172, 248, 199));
		else if (PongBrain.getInstance().getWhoAmI() == PongBrain.SERVER && x <= 400 ||
			       PongBrain.getInstance().getWhoAmI() == PongBrain.CLIENT && x >= 400)
			g.setColor(Color.DARK_GRAY);
		else
			g.setColor(Color.WHITE);
		g.fillRect (x - 5, y - 5, 10, 10);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (PongBrain.getInstance().getWhoAmI() == PongBrain.CLIENT)
			PongBrain.getInstance().setClientPlayerY(e.getY());
		else
			PongBrain.getInstance().setServerPlayerY(e.getY());
		repaint();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		PongBrain.getInstance().moveBall();
		repaint();
	}
}
