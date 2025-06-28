package com.whispr.messenger.entity;

import com.whispr.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;

    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @Column(name = "type")
    private Short type;

    @Column(name = "thumbnail_url", length = 256)
    private String thumbnailUrl;

    @Column(name = "media_url", length = 256)
    private String mediaUrl;

    @CreatedDate
    @Column(name = "sent_at", nullable = false)
    private Instant sentAt;

    @LastModifiedDate
    @Column(name = "edited_at")
    private Instant editedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MessageReceipt> messageReceipts = new LinkedHashSet<>();

}
