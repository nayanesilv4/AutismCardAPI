package com.autismcard.autismcard.mapper;

import com.autismcard.autismcard.dto.CardRequestDto;
import com.autismcard.autismcard.dto.CardResponseDto;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.autismcard.autismcard.model.Card;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

/*
   O MapStruct é utilizado para realizar a conversão automática entre objetos,
   eliminando a necessidade de escrever código repetitivo. Com a anotação
   @Mapper(componentModel = "spring"), o mapper se torna um componente do Spring,
   permitindo sua injeção onde for necessário. Essa abordagem garante uma maior
   separação de responsabilidades e facilita a manutenção dos mapeamentos.
*/
@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "uuidToString")
    @Mapping(target = "cpf", source = "cpf", qualifiedByName = "maskCpf")
    @Mapping(target = "age", expression = "java(computeAge(card.getBirthDate()))")
    CardResponseDto toResponseDto(Card card);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cpf", source = "cpf")
    Card toEntity(CardRequestDto cardRequestDto);

    /**
     * Aplica máscara oculta no CPF, mantendo os três primeiros e os dois últimos dígitos visíveis.
     *
     * @param cpf CPF sem formatação.
     * @return CPF mascarado no formato "xxx.***.***-yy".
     */
    @Named("maskCpf")
    default String maskCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf;
        }
        String visibleStart = cpf.substring(0, 3);
        String visibleEnd = cpf.substring(9, 11);
        return visibleStart + ".***.***-" + visibleEnd;
    }

    /**
     * Converte UUID para String.
     *
     * @param uuid o UUID a ser convertido
     * @return a representação em string do UUID ou null se o UUID for null
     */
    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    /**
     * Converte String para UUID.
     *
     * @param uuidString a string representando o UUID
     * @return o UUID correspondente ou null se a string for null ou inválida
     */
    @Named("stringToUuid")
    default UUID stringToUuid(String uuidString) {
        if (uuidString == null || uuidString.trim().isEmpty()) {
            return null; // Ignore for new entities
        }
        try {
            return UUID.fromString(uuidString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("UUID inválido: " + uuidString, e);
        }
    }

    /**
     * Calcula a idade com base na data de nascimento.
     *
     * @param birthDate a data de nascimento
     * @return a idade formatada como string (e.g., "2 anos, 3 meses e 5 dias")
     * @throws IllegalArgumentException se a data de nascimento for nula ou no futuro
     */
    default String computeAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("A data de nascimento não pode ser nula");
        }

        LocalDate currentDate = LocalDate.now();
        if (birthDate.isAfter(currentDate)) {
            throw new IllegalArgumentException("A data de nascimento não pode ser no futuro");
        }

        Period period = Period.between(birthDate, currentDate);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        if (years == 0 && months == 0 && days == 0) {
            return "Recém-nascido";
        }

        StringBuilder age = new StringBuilder();
        boolean hasPrevious = false;

        if (years > 0) {
            age.append(years).append(years == 1 ? " ano" : " anos");
            hasPrevious = true;
        }

        if (months > 0) {
            age.append(hasPrevious ? (days > 0 ? ", " : " e ") : "");
            age.append(months).append(months == 1 ? " mês" : " meses");
            hasPrevious = true;
        }

        if (days > 0) {
            age.append(hasPrevious ? " e " : "");
            age.append(days).append(days == 1 ? " dia" : " dias");
        }

        // Return "Recém-nascido" if no components were added (e.g., less than a day)
        return age.isEmpty() ? "Recém-nascido" : age.toString();
    }
}