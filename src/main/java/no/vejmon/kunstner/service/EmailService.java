package no.vejmon.kunstner.service;

import lombok.extern.slf4j.Slf4j;
import no.vejmon.kunstner.models.ContactSchema;import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@ConditionalOnExpression(
        "T(org.springframework.util.StringUtils).hasText('${spring.mail.username:}')"
)
public class EmailService {

    @Value("${spring.mail.inbox}")
    private String inbox;
    @Value("${spring.mail.username}")
    private String mail;
    @Value("${spring.mail.signatur}")
    private String signatur;
    @Value("${spring.mail.message}")
    private String message;
    private final JavaMailSender sender;

    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendEmailFromContactSchema(ContactSchema contactSchema){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail);
        message.setTo(contactSchema.getEmail());
        message.setBcc(inbox);
        message.setSubject(contactSchema.getName() + " har sendt ny melding");
        message.setText(buildEmailBody(contactSchema));
        sender.send(message);
    }

    private String buildEmailBody(ContactSchema contactSchema) {
        return """
               Hei %s,
               %s

               din melding:
               %s
               
               
               %s
               
               """.formatted(contactSchema.getName(), message, contactSchema.getMessage(), signatur);
    }

}