package sr.api.Util;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;


import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sercan on 10/02/16.
 */
public class MailUtil {
    private JavaMailSender mailSender;

    private String to;
    private String cc;
    private String subject;
    private String content;

    public void setMailSender(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void generateAndSendEmail(){


        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));
                mimeMessage.setSubject(subject);
                mimeMessage.setFrom(new InternetAddress("sparrowarr@gmail.com"));
                mimeMessage.setContent(content, "text/html");
            }
        };

        try {
            this.mailSender.send(preparator);
        }
        catch (MailException ex) {

        }

    }

    public void generateAndSendEmailMulti(List<String> recipients){

        if(recipients == null || recipients.isEmpty()) return;

        final List<InternetAddress> emails = new ArrayList<>();
        for(String email : recipients){
            try {
                emails.add(new InternetAddress(email));
            } catch (AddressException e) {
            }
        }
        if(emails.isEmpty())return;

        Address[] list = new Address[emails.size()];
        for(int i = 0; i<emails.size(); i++)
            list[i] = emails.get(i);

        final Address[] recipientList = list;

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipients(Message.RecipientType.TO,recipientList);
                mimeMessage.setSubject(subject);
                mimeMessage.setFrom(new InternetAddress("sparrowarr@gmail.com"));
                mimeMessage.setContent(content, "text/html");
            }
        };

        try {
            this.mailSender.send(preparator);
        }
        catch (MailException ex) {
        }

    }



    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
