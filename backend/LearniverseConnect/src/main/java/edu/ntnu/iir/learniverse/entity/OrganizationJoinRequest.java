package edu.ntnu.iir.learniverse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

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
  @JsonIgnore
  @JoinColumn(name = "requested_by")
  private User requestedBy;
}
