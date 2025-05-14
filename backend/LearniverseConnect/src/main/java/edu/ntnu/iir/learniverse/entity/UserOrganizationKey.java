package edu.ntnu.iir.learniverse.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserOrganizationKey implements Serializable {
  private Long user;
  private Long organization;
}
