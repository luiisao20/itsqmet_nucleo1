package com.itsqmet.entity;

import java.util.UUID;

import com.itsqmet.roles.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true)
  @Email(message = "El correo no pertenece un formato válido")
  private String email;

  @NotBlank(message = "El nombre es obligatorio")
  private String firstName;

  @NotBlank(message = "El apellido es obligatorio")
  private String lastName;

  @NotBlank(message = "La contraseña es obligatoria")
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;
}
