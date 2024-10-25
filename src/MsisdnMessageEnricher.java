import java.util.Map;

public class MsisdnMessageEnricher implements MessageEnricher {

  private final UserRepository userRepository;

  public MsisdnMessageEnricher(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Map<String, String> enrich(Map<String, String> content) {
    if (content.containsKey("msisdn")) {
      String msisdn = content.get("msisdn");
      User user = userRepository.findByMsisdn(msisdn);
      if (user != null) {
        content.put("firstName", user.getFirstName());
        content.put("lastName", user.getLastName());
      }
    }
    return content;
  }
}