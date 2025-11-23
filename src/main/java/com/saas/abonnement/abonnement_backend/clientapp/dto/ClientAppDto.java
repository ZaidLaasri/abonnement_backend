package com.saas.abonnement.abonnement_backend.clientapp.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientAppDto {

    Long id;

    @NotBlank
    String name;

    String apiKey;

    @Email
    @NotBlank
    String contactEmail;

    @NotBlank
    String status;
}
