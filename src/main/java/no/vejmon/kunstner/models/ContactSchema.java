package no.vejmon.kunstner.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactSchema {
    @NotBlank(message = "Et navn er påkrevd")
    @Size(message = "Beklager, Navnet ditt må ha færre enn 55 tegn")
    private String name;

    @Email(message = "Vennligst oppgi en gyldig e-post")
    @NotBlank(message = "En gyldig epost er påkrevd")
    private String email;

    @NotBlank(message = "En melding er påkrevd")
    private String message;

    /* TODO: add phone support, should be marked as prefered communication and msg should automagicaly sendt from server.
    private String phone;
    */
}
