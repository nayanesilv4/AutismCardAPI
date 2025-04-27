package com.autismcard.autismcard.repository;

import com.autismcard.autismcard.model.Card;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, UUID> {

  Optional<Card> findByCpf(String cpf);
}
