package edu.ntnu.iir.learniverse.service;

import edu.ntnu.iir.learniverse.entity.Favorite;
import edu.ntnu.iir.learniverse.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
  private final FavoriteRepository favoriteRepository;

  public FavoriteService(FavoriteRepository favoriteRepository) {
    this.favoriteRepository = favoriteRepository;
  }

  public List<Favorite> getByUser(Long userId) {
    return favoriteRepository.findByUserId(userId);
  }

  public Favorite save(Favorite favorite) {
    return favoriteRepository.save(favorite);
  }

  public void delete(Long id) {
    favoriteRepository.deleteById(id);
  }

  public void deleteByUserAndCourse(Long userId, Long courseId) {
    favoriteRepository.findByUserIdAndCourseId(userId, courseId)
        .ifPresent(favoriteRepository::delete);
  }
}
