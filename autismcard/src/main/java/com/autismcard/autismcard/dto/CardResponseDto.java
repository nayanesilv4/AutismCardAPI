package com.autismcard.autismcard.dto;

import java.time.LocalDate;

public record CardResponseDto(
    String id,
    String cpf,
    String fullName,
    LocalDate birthDate,
    String cid,
    String phone,
    String reportLink,
    String responsibleName,
    String responsiblePhone,
    String age
) {}