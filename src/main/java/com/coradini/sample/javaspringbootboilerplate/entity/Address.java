package com.coradini.sample.javaspringbootboilerplate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "address", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cep"}, name = "uk_cep")
}, indexes = {
        @Index(columnList = "cep", name = "idx_cep")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends AbstractBaseEntity {
    @Column(name = "cep", nullable = false)
    private String cep; // Postal code

    @Column(name = "street", nullable = false)
    private String street; // Logradouro

    @Column(name = "complement", nullable = true)
    private String complement; // Complemento

    @Column(name = "unit", nullable = true)
    private String unit; // Unidade

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood; // Bairro

    @Column(name = "locality", nullable = false)
    private String locality; // Localidade

    @Column(name = "state_code", nullable = false)
    private String stateCode; // UF (State Code)

    @Column(name = "state", nullable = false)
    private String state; // Estado

    @Column(name = "region", nullable = true)
    private String region; // Região

    @Column(name = "ibge", nullable = false)
    private String ibge; // IBGE Code

    @Column(name = "gia", nullable = true)
    private String gia; // GIA Code

    @Column(name = "ddd", nullable = true)
    private String ddd; // DDD Code

    @Column(name = "siafi", nullable = true)
    private String siafi; // SIAFI Code

    @OneToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "fk_address_person"))
    private Person person; // Correctly reference the Person entity

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
