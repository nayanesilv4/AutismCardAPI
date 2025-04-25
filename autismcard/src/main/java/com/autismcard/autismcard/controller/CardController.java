package com.autismcard.autismcard.controller;

import com.autismcard.autismcard.model.Card;
import com.autismcard.autismcard.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@Tag(name = "Autism Card API", description = "CRUD operations for Autism ID Cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAll(){
        return cardService.getAll();
    }

    @PostMapping
    public Card create(@RequestBody Card card){
        return cardService.save(card);
    }

    @GetMapping("/{cpf}")
    public Card getByCpf(@PathVariable String cpf){
        return cardService.getByCpf(cpf);
    }

    @PutMapping("/{cpf}")
    public Card update(@PathVariable String cpf, @RequestBody Card card){
        if(!cardService.exists(cpf)){
            throw new RuntimeException("Cpf não encontrado");
        }
       card.setCpf(cpf);

        return cardService.save(card);
    }

    @DeleteMapping("/{cpf}")
    public void delete(@PathVariable String cpf){
        cardService.delete(cpf);
    }



}
