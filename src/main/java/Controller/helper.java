/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class helper {
    public static void sendEmail(String to, String from,
        String subject, String body, boolean bodyIsHTML) 
        throws MessagingException {
        // 1 - get a mail session
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.quitwait", "false");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        
        // 2 - create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }
        // 3 - address the message
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // 4 - send the message
        
        String username = System.getenv("MAIL_USERNAME");
        String password = System.getenv("MAIL_PASSWORD");

        System.out.println("MAIL_USERNAME = " + username);
        System.out.println("MAIL_PASSWORD exists = " + (password != null));

        Transport transport = session.getTransport("smtp");
        transport.connect(username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        
    
    }
}
