import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class EnrichmentServiceConcurrentTest {

  @Test
  public void testConcurrentEnrichment() throws InterruptedException {
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

    List<Message> enrichmentResults = new CopyOnWriteArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    CountDownLatch latch = new CountDownLatch(5);

    for (int i = 0; i < 5; i++) {
      executorService.submit(() -> {
        enrichmentResults.add(service.enrich(message));
        latch.countDown();
      });
    }

    latch.await();

    for (Message enrichedMessage : enrichmentResults) {
      assertEquals("Vasya", enrichedMessage.getContent().get("firstName"));
      assertEquals("Ivanov", enrichedMessage.getContent().get("lastName"));
    }
  }
}