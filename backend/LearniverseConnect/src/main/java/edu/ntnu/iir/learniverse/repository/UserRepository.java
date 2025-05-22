package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  /**
   * Find a user by their email address, ignoring case.
   *
   * @param email the email address of the user
   * @return an Optional containing the user if found, or empty if not
   */
  Optional<User> findByEmailIgnoreCase(String email);

  /**
   * Find a user by their username, ignoring case.
   *
   * @param username the username of the user
   * @return an Optional containing the user if found, or empty if not
   */
  Optional<User> findByUsernameIgnoreCase(String username);
}
