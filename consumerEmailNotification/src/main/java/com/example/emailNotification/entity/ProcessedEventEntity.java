package com.example.emailNotification.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "processed-events")
@Setter @Getter @NoArgsConstructor
public class ProcessedEventEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String messageId;
    @Column(nullable = false)
    private String productId;

    public ProcessedEventEntity(String messageId, String productId) {
        this.messageId = messageId;
        this.productId = productId;
    }
}
