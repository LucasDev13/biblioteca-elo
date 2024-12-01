package br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity(name = "contact")
@Table(name = "tbl_contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "create_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @PastOrPresent
    private LocalDateTime createAt;

    @Column(name = "update_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @PastOrPresent
    private LocalDateTime updateAt;

    public Contact(
            @NotEmpty(message = "Não pode estar vazio ou null!")
            @Email String email,

            @NotEmpty(message = "Não pode estar vazio ou null!")
            @Pattern(regexp = "\\d{12}", message = "O campo deve conter exatamente 12 dígitos")
            String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
