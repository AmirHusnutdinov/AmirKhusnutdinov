import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EnrichmentServiceTest {

  @Test
  public void testEnrichment() {
    UserRepository userRepository = new InMemoryUserRepository();
    MessageEnricher msisdnEnricher = new MsisdnMessageEnricher(userRepository);
    Map<Message.EnrichmentType, MessageEnricher> enrichers = new HashMap<>();
    enrichers.put(Message.EnrichmentType.MSISDN, msisdnEnricher);

    EnrichmentService service = new EnrichmentService(enrichers);

    Map<String, String> content = new HashMap<>();
    content.put("action", "button_click");
    content.put("page", "book_card");
    content.put("msisdn", "88005553535");

    Message message = new Message(content, Message.EnrichmentType.MSISDN);

    Message enrichedMessage = service.enrich(message);

    assertEquals("Vasya", enrichedMessage.getContent().get("firstName"));
    assertEquals("Ivanov", enrichedMessage.getContent().get("lastName"));
  }
}