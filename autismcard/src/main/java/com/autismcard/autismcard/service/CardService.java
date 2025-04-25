package com.autismcard.autismcard.service;

import com.autismcard.autismcard.model.Card;
import com.autismcard.autismcard.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    // Lista todas as carteirinhas
    public List<Card> getAll(){
        return cardRepository.findAll();
    }

    // Salva uma nova carteirinha
    public Card save(Card card){
        return cardRepository.save(card);
    }

    // Lista a carteirinha por CPF
    public Card getByCpf(String cpf){
        return cardRepository.findById(cpf).orElse(null);
    }

    // Atualiza uma carteirinha existente
    public boolean exists(String cpf){
        return cardRepository.existsById(cpf);
    }

    //Deleta uma carteirinha existente
    public void delete(String cpf){
        cardRepository.deleteById(cpf);
    }


}
