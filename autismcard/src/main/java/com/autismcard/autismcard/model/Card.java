package com.autismcard.autismcard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/* O campo id foi alterado para UUID para garantir unicidade global e maior segurança.
   Isso é especialmente útil em sistemas distribuídos, onde múltiplas instâncias podem gerar IDs
   sem risco de colisão. Além disso, o UUID é mais difícil de ser adivinhado, aumentando a proteção contra ataques.
   e você não expõe o cpf diretamente na API.
   O uso do Lombok simplifica a geração de getters, setters, construtores e o padrão Builder,
   deixando o código mais limpo e profissional.
*/
@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 11)
    private String cpf;

    private String fullName;

    private LocalDate birthDate;

    private String cid;

    private String phone;

    private String reportLink;

    private String responsibleName;

    private String responsiblePhone;

}
