package edu.ntnu.iir.learniverse.repository;

import edu.ntnu.iir.learniverse.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Category entities.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
