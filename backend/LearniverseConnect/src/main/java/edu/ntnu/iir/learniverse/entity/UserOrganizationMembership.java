package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

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
