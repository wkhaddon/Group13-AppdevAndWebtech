package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Entity class representing a membership of a user in an organization.
 */
@Getter
@Entity
@Table(name = "user_organization_memberships")
@IdClass(UserOrganizationKey.class)
public class UserOrganizationMembership {
  @Id
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Id
  @ManyToOne
  @JoinColumn(name = "organization_id")
  private ProviderOrganization organization;

  @Enumerated(EnumType.STRING)
  @Column(name = "org_role")
  private OrgRole role;

  @Column(name = "joined_at")
  private LocalDateTime joinedAt;
}
