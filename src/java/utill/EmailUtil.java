package utill;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(String to, String subject, String body) throws MessagingException {
        // Sender's email address
        String from = "anhmayday1412@gmail.com";

        // Sender's credentials
        String username = "anhmayday1412@gmail.com";
        String password = "yxvj aoda jwqi lqth";

        // Mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create a message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        // Send the message
        Transport.send(message);
    }

    public static void main(String[] args) {
        try {
            // Sample email details
            String to = "thientm01@gmail.com";
            String subject = "Test Email";
            String body = "This is a test email sent using JavaMail API.";

            // Send the email
            EmailUtil.sendEmail(to, subject, body);

            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
