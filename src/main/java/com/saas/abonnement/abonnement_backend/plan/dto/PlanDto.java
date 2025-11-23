package com.saas.abonnement.abonnement_backend.plan.dto;



import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PlanDto {

    Long id;
    Long clientAppId;
    String code;
    String name;
    String description;
    BigDecimal price;
    String currency;
    Integer durationInDays;
    String status;
}
