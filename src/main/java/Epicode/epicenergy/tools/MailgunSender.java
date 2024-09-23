package Epicode.epicenergy.tools;

import Epicode.epicenergy.entities.Utente;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailgunSender {
    private String apikey;
    private String domainKey;

    public MailgunSender(@Value("${mailgun.key}") String apikey, @Value("${mailgun.domain}") String domainKey) {
        this.apikey = apikey;
        this.domainKey = domainKey;
    }

    public void sendRegistrationEmail(Utente ricevente) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainKey + "/messages")
                .basicAuth("api", this.apikey)
                .queryString("from", "${email}")
                .queryString("to", ricevente.getMail())
                .queryString("subject", "Registrazione completata")
                .queryString("text", "Ciao, grazie per esserti registrato")
                .asJson();
        System.out.println(response.getBody());
    }
}
