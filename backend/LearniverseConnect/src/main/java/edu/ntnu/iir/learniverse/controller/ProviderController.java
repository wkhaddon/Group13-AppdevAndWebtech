package edu.ntnu.iir.learniverse.controller;

import edu.ntnu.iir.learniverse.entity.Provider;
import edu.ntnu.iir.learniverse.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {
  @Autowired
  private ProviderRepository providerRepository;

  @GetMapping
  public List<Provider> getAllProviders() {
    return providerRepository.findAll();
  }
}
