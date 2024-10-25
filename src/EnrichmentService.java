import java.util.Map;

public class EnrichmentService {

  private final Map<Message.EnrichmentType, MessageEnricher> enrichers;

  public EnrichmentService(Map<Message.EnrichmentType, MessageEnricher> enrichers) {
    this.enrichers = enrichers;
  }

  public Message enrich(Message message) {
    MessageEnricher enricher = enrichers.get(message.getEnrichmentType());
    if (enricher != null) {
      message.setContent(enricher.enrich(message.getContent()));
    }
    return message;
  }
}