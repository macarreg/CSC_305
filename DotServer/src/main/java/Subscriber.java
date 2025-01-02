import org.eclipse.paho.client.mqttv3.*;
import javax.swing.*;
import java.awt.*;
public class Subscriber extends JFrame implements MqttCallback {
    private final static String BROKER = "tcp://test.mosquitto.org:1883";
    private final static String TOPIC = "cal-poly/csc/305";
    private final static String CLIENT_ID = "jgs-subscriber";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Subscriber::new);
    }
    public Subscriber() {
        setupUI();
        setupMQTTClient();
    }
    private void setupUI() {
        setTitle("Subscriber");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        DrawArea drawArea = new DrawArea();
        add(drawArea);
        Repository.getInstance().addPropertyChangeListener(drawArea);

        setVisible(true);
    }

    private void setupMQTTClient() {
        try {
            MqttClient client = new MqttClient(BROKER, CLIENT_ID);
            client.setCallback(this);
            client.connect();
            client.subscribe(TOPIC);
            System.out.println("Subscribed to topic: " + TOPIC);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        if (TOPIC.equals(topic)) {
            String payload = new String(mqttMessage.getPayload());
            String[] data = payload.split(",");
            if (data.length == 3) {
                try {
                    int x = Integer.parseInt(data[1]);
                    int y = Integer.parseInt(data[2]);
                    Repository.getInstance().addPoint(new Point(x, y));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost: " + throwable.getMessage());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
    }
}