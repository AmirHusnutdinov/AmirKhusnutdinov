package com.example.javaHomeworkSecondTerm.actuator;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/custom")
public class CustomActuatorEndpoint {

  @GetMapping("/random-uuid")
  public String getRandomUuid() {
    return UUID.randomUUID().toString();
  }
}
