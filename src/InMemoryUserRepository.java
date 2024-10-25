import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {

  private final Map<String, User> users = new ConcurrentHashMap<>();

  public InMemoryUserRepository() {
    // Пример инициализации
    users.put("88005553535", new User("Vasya", "Ivanov"));
  }

  @Override
  public User findByMsisdn(String msisdn) {
    return users.get(msisdn);
  }

  @Override
  public void updateUserByMsisdn(String msisdn, User user) {
    users.put(msisdn, user);
  }
}