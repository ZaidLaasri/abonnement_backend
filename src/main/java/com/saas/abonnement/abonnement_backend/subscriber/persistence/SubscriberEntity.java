package com.saas.abonnement.abonnement_backend.subscriber.persistence;

import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscribers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_app_id", nullable = false)
    private ClientAppEntity clientApp;

    @Column(nullable = false)
    private String externalUserId; // identifiant côté app cliente

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriberStatus status;

    private LocalDateTime createdAt;
}
