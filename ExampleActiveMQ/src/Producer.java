import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
public class Producer {

    /* URL of the JMS server. DEFAULT_BROKER_URL will just mean
    * that JMS server is on localhost */
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    /* Name of the queue we will be sending messages to */
    private static String subject = "TESTQUEUE";

    public static void main(String[] args) throws JMSException {

        /* Getting JMS connection from the server and starting it */
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        /* JMS messages are sent and received using a Session. We will
        * create here a non-transactional session object. If you want
        * to use transactions you should set the first parameter to 'true' */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        /* Destination represents here our queue 'TESTQUEUE' on the
        * JMS server. You don't have to do anything special on the
        * server to create it, it will be created automatically. */
        Destination destination = session.createQueue(subject);

        /* MessageProducer is used for sending messages (as opposed
        * to MessageConsumer which is used for receiving them) */
        MessageProducer producer = session.createProducer(destination);

        /* We will send a small text message saying 'Hello' in Japanese */
        TextMessage message = session.createTextMessage("こんにちは");

        /* Here we are sending the message! */
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");
        connection.close();

    }

}