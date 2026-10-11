package no.vejmon.kunstner.config;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@Aspect
@Configuration
@ConditionalOnExpression(
        "T(org.springframework.util.StringUtils).hasText('${spring.mail.username:}')"
)
public class EmailConfig {

    @Value("${spring.mail.username}")
    private String mail;
    private Integer counter = 0;
    private final JavaMailSender sender;

    public EmailConfig(JavaMailSender sender) {
        this.sender = sender;
    }

    private void sendThrownEmail(Throwable ex){
        if (counter > 15) return;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail);
        message.setTo(mail);
        message.setSubject("Ny melding fra kontaktskjema");
        message.setText(buildEmailBody(ex));
        log.info("Sending email notification for exception to {}", mail);
        sender.send(message);
        log.info("email sendt successfully");

        counter++;
    }

    private String buildEmailBody(Throwable ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));

        return """
               
               Dommern har støtt på en feil.

               Message:
               %s

               Stack trace:
               %s
               """.formatted(ex.getMessage(), sw.toString());
    }

}