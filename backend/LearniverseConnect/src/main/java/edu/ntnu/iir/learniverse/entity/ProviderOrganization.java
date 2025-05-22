package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

/**
 * Entity class representing a provider organization in the system.
 */
@Getter
@Entity
@Table(name = "provider_organizations")
public class ProviderOrganization {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String currency;
  private boolean approved;

  @OneToMany(mappedBy = "organization")
  private List<UserOrganizationMembership> memberships;

  @OneToMany(mappedBy = "provider")
  private List<Course> courses;
}
