package com.autismcard.autismcard.mapper;

import com.autismcard.autismcard.dto.CardRequestDto;
import com.autismcard.autismcard.dto.CardResponseDto;
import com.autismcard.autismcard.model.Card;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-27T19:38:31-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardResponseDto toResponseDto(Card card) {
        if ( card == null ) {
            return null;
        }

        String id = null;
        String cpf = null;
        String fullName = null;
        LocalDate birthDate = null;
        String cid = null;
        String phone = null;
        String reportLink = null;
        String responsibleName = null;
        String responsiblePhone = null;

        id = uuidToString( card.getId() );
        cpf = maskCpf( card.getCpf() );
        fullName = card.getFullName();
        birthDate = card.getBirthDate();
        cid = card.getCid();
        phone = card.getPhone();
        reportLink = card.getReportLink();
        responsibleName = card.getResponsibleName();
        responsiblePhone = card.getResponsiblePhone();

        String age = computeAge(card.getBirthDate());

        CardResponseDto cardResponseDto = new CardResponseDto( id, cpf, fullName, birthDate, cid, phone, reportLink, responsibleName, responsiblePhone, age );

        return cardResponseDto;
    }

    @Override
    public Card toEntity(CardRequestDto cardRequestDto) {
        if ( cardRequestDto == null ) {
            return null;
        }

        Card.CardBuilder card = Card.builder();

        card.cpf( cardRequestDto.cpf() );
        card.fullName( cardRequestDto.fullName() );
        card.birthDate( cardRequestDto.birthDate() );
        card.cid( cardRequestDto.cid() );
        card.phone( cardRequestDto.phone() );
        card.reportLink( cardRequestDto.reportLink() );
        card.responsibleName( cardRequestDto.responsibleName() );
        card.responsiblePhone( cardRequestDto.responsiblePhone() );

        return card.build();
    }
}
