package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

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
