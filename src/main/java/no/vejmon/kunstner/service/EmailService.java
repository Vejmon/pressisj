package no.vejmon.kunstner.service;

import jakarta.annotation.PostConstruct;
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
    @Value("${spring.mail.enabled:true}")
    private boolean enabled;


    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    @PostConstruct
    public void init() {
        log.info("Email Service enabled: {}", (enabled && mail != null));
    }

    public void sendEmailFromContactSchema(ContactSchema contactSchema){
        if (!enabled) {
            log.warn("Email disabled, not sent to, [name: {}, email: {}, msg: {}]",
                    contactSchema.getName(), contactSchema.getEmail(), contactSchema.getMessage());
            return;
        }
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