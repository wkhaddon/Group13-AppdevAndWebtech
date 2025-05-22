package edu.ntnu.iir.learniverse.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Composite key class for UserOrganizationMembership entity.
 */
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserOrganizationKey implements Serializable {
  private Long user;
  private Long organization;
}
