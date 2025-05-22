package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing orders.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  /**
   * Find all orders for a specific user.
   *
   * @param userId the ID of the user
   * @return a list of orders for the user
   */
  List<Order> findByUserId(Long userId);
}
