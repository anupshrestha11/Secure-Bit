package Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailVerification {
    public void mail(int key, String email) {
        final String user = "secure.every.bit@gmail.com";
        final String password = "mbkbiyqxpwdmpdyb";

        String to = email;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }

                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Sign-Up::::::::: Secure Bit");
            message.setContent("<h3>Key For Sign-Up:</h3><br><b>Code : </b>" + key, "text/html; charset=UTF-8"
            );

            Transport.send(message);

            System.out.println("message sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
