package com.gym.identity.model


import com.devskiller.friendly_id.FriendlyId
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(id: String? = null) : Serializable {
    @Id
    @Column(name = "id")
    var id: @Size(max = 200) String?
        protected set

    @Column(name = "created_at", nullable = false, updatable = false)
    lateinit var createdAt: LocalDateTime

    @Column(name = "updated_at", nullable = false)
    lateinit var updatedAt: LocalDateTime

    @PrePersist
    fun prePersist() {
        createdAt = LocalDateTime.now()
        updatedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }

    init {
        this.id = id ?: FriendlyId.createFriendlyId()
    }
}




