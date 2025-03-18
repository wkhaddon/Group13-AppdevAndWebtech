package edu.ntnu.iir.learniverse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provider {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String currency;

  @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
  private List<CourseProvider> courseProviders;
}
