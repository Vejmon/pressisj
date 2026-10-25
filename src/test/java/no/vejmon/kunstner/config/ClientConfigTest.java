package no.vejmon.kunstner.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ClientConfig.class, ClientProperties.class})
@EnableConfigurationProperties(ClientProperties.class)
@ExtendWith(MockitoExtension.class)
public class ClientConfigTest {

    @Mock
    private ExchangeFunction exchangeFunction;

    @Autowired
    private WebClient domeneshopClient;
    @Captor
    private ArgumentCaptor<ClientRequest> requestCaptor;
    private WebClient mockedClient;

    @BeforeEach
    public void setUp() {
        mockedClient = domeneshopClient.mutate().exchangeFunction(exchangeFunction).build();
    }

    @Test
    void domeneshopClient_isAvailableAndUsesBasicAuth() {
        assertNotNull(mockedClient);
        ClientResponse mockResponse = ClientResponse.create(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("{\"id\":1,\"name\":\"Alice\"}")
                .build();
        when(exchangeFunction.exchange(any(ClientRequest.class))).thenReturn(Mono.just(mockResponse));
        mockedClient.get()
                .exchangeToMono(
                        clientResponse -> clientResponse.bodyToMono(TestRequest.class))
                .block();

        verify(exchangeFunction).exchange(requestCaptor.capture());
        ClientRequest request = requestCaptor.getValue();
        assertEquals("testyurl", request.url().toString());
        assertEquals("Basic dGVzdHl0b2tlbjp0ZXN0eXNlY3JldA==", request.headers().get("Authorization").getFirst());
    }

    record TestRequest(String id, String name) {
    }

}