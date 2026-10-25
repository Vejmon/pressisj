package no.vejmon.kunstner.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

//            "Authorization": "Basic {}".format( // # Grabbed from # https://github.com/domeneshop/python-domeneshop
//                base64.b64encode("{}:{}".format(token, secret).encode()).decode()

@Getter
@Setter
@Configuration
public class ClientConfig {

    private final ClientProperties clientProperties;

    public ClientConfig(ClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

    @Bean
    public WebClient domeneshopClient(){
        ClientProperties.Client domeneshopClient = clientProperties.getDomeneshop();
        return WebClient.builder()
                .baseUrl(domeneshopClient.getUrl())
                .defaultHeaders(headers ->
                    headers.setBasicAuth(domeneshopClient.getToken(), domeneshopClient.getSecret())
                )
                .build();

    }
}
