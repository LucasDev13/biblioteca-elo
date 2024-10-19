package br.com.elotech.biblioteca_elo.domain.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class UserDomain {

    private String name;
    private String email;
    private String phoneNumber;
}
