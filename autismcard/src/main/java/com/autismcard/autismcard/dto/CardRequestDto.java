package com.autismcard.autismcard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import org.hibernate.validator.constraints.br.CPF;

/* A classe CardDto foi criada como um record para representar uma versão imutável e simplificada da entidade Card.
Ela é usada para transferir dados entre diferentes camadas da aplicação (DTO - Data Transfer Object),
garantindo que apenas os dados necessários sejam expostos, promovendo segurança e separação de responsabilidades.
Além de colocar validação nos campos */
public record CardRequestDto(
    @NotBlank(message = "O CPF não pode ser vazio")
    @CPF(message = "CPF inválido")
    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 dígitos")
    String cpf,

    @NotBlank(message = "O nome completo não pode ser vazio")
    String fullName,

    @NotNull(message = "A data de nascimento não pode ser nula")
    LocalDate birthDate,

    @NotBlank(message = "O CID não pode ser vazio")
    String cid,

    @NotBlank(message = "O telefone não pode ser vazio")
    String phone,

    @NotBlank(message = "O link do relatório não pode ser vazio")
    String reportLink,

    @NotBlank(message = "O nome do responsável não pode ser vazio")
    String responsibleName,

    @NotBlank(message = "O telefone do responsável não pode ser vazio")
    String responsiblePhone
) {}