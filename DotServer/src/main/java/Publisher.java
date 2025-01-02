import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.swing.*;
import java.awt.*;

public class Publisher extends JFrame {

    private final static String BROKER = "tcp://test.mosquitto.org:1883";
    private final static String TOPIC = "cal-poly/csc/305";
    private final static String CLIENT_ID = "jgs-publisher";
    private MqttClient client;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Publisher::new);
    }
    public Publisher() {
        setupUI();
        setupMQTTClient();
    }
    private void setupUI() {
        setTitle("Publisher");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        DrawArea drawArea = new DrawArea();
        add(drawArea);
        Repository.getInstance().addPropertyChangeListener(drawArea);

        drawArea.addMouseListener(new DrawAreaListener(this));
        setVisible(true);
    }

    private void setupMQTTClient() {
        try {
            client = new MqttClient(BROKER, CLIENT_ID);
            client.connect();
            System.out.println("Connected to broker: " + BROKER);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void publishPoint(Point point) {
        if (client != null && client.isConnected()) {
            try {
                String messageContent = CLIENT_ID + "," + point.x + "," + point.y;
                MqttMessage message = new MqttMessage(messageContent.getBytes());
                message.setQos(2);
                client.publish(TOPIC, message);
                System.out.println("Published: " + messageContent);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
}