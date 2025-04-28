ALTER TABLE `cards`
    ADD CONSTRAINT UK_cards_cpf UNIQUE (`cpf`);