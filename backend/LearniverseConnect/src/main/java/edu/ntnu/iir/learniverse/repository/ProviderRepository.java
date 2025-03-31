package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
