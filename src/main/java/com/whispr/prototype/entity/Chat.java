package com.whispr.prototype.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "chats")
public class Chat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "type", nullable = false)
    private Short type;

    @Column(name = "title", length = 128)
    private String title;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "deleted_at")
    private Instant deletedAt;

}
