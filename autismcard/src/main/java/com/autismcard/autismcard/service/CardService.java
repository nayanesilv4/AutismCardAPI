package com.autismcard.autismcard.service;

import com.autismcard.autismcard.dto.CardRequestDto;
import com.autismcard.autismcard.dto.CardResponseDto;
import com.autismcard.autismcard.exception.NotFoundException;
import com.autismcard.autismcard.mapper.CardMapper;
import com.autismcard.autismcard.model.Card;
import com.autismcard.autismcard.repository.CardRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // evita a utilização do autowired no construtor (a injeção via autowired não é recomendada)
public class CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    // Lista todas as carteirinhas. Obs vou manter o método aqui, mas não é uma boa prática expor todos os dados
    public List<CardResponseDto> getAll(){
        return cardRepository.findAll()
                .stream()
                .map(cardMapper::toResponseDto)
                .toList();
    }

    // Retorna as carteirinhas de forma paginada.
    // Utilizamos a interface Pageable para gerenciar a paginação e o método map para converter cada entidade Card em CardDto.
    public Page<CardResponseDto> getPaginated(Pageable pageable) {
        return cardRepository.findAll(pageable)
            .map(cardMapper::toResponseDto);
    }

    // Salva uma nova carteirinha
    public CardResponseDto save(CardRequestDto card){
        return cardMapper.toResponseDto(cardRepository.save(cardMapper.toEntity(card)));
    }

    // Lista a carteirinha por CPF
    public CardResponseDto getByCpf(String cpf){
        return cardRepository.findByCpf(cpf)
            .map(cardMapper::toResponseDto)
            .orElseThrow(() -> new NotFoundException("Carteirinha não encontrada"));
    }

    // Atualiza uma carteirinha existente
    public boolean exists(String uuid){
        return cardRepository.findById(UUID.fromString(uuid)).isPresent();
    }

    //Deleta uma carteirinha existente
    public void delete(String uuid){
        if (!exists(uuid)) {
            throw new NotFoundException("Carteirinha não encontrada");
        }
        cardRepository.deleteById(UUID.fromString(uuid));
    }


    public CardResponseDto update(String uuid, CardRequestDto cardDto) {
        if (!exists(uuid)) {
            throw new NotFoundException("Carteirinha não encontrada");
        }
        Card cardToUpdate = cardMapper.toEntity(cardDto);
        cardToUpdate.setId(UUID.fromString(uuid));
        return cardMapper.toResponseDto(cardRepository.save(cardToUpdate));
    }
}
