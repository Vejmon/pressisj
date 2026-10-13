package no.vejmon.kunstner.view;

import jakarta.validation.Valid;
import no.vejmon.kunstner.models.ContactSchema;
import no.vejmon.kunstner.service.EmailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ConditionalOnExpression(
        "T(org.springframework.util.StringUtils).hasText('${spring.mail.username:}')"
)
@RequestMapping("/open")
public class EmailControlla {
    private final EmailService emailService;

    public EmailControlla(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/email")
    public @ResponseBody EmailResponse email(@RequestBody @Valid ContactSchema contactSchema) {
        emailService.sendEmailFromContactSchema(contactSchema);
        return new EmailResponse(EmailResponse.EmailStatus.OK);
    }

    @GetMapping(value = "/email")
    public @ResponseBody EmailResponse email() {
        return new EmailResponse(EmailResponse.EmailStatus.UP);
    }

    public record EmailResponse(EmailStatus status) {
        public enum EmailStatus {
                OK, UP
            }
    }


}