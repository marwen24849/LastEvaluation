package fsb.ucar.Microservice.User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendMail(String to, String[] cc, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(fromEmail);
            message.setTo(to);
            message.setCc(cc);
            message.setSubject(subject);
            message.setText(body);

            javaMailSender.send(message);

            return "Mail sent successfully";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
