package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Entity class representing a request to join an organization.
 */
@Getter
@Entity
@Table(name = "organization_join_requests")
public class OrganizationJoinRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "requested_org_name")
  private String requestedOrgName;

  private String message;

  @Enumerated(EnumType.STRING)
  private RequestStatus status;

  @Column(name = "submitted_at")
  private LocalDateTime submittedAt;

  @ManyToOne
  @JoinColumn(name = "requested_by")
  private User requestedBy;
}
