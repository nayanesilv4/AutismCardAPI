package com.autismcard.autismcard.controller;

import com.autismcard.autismcard.dto.CardRequestDto;
import com.autismcard.autismcard.dto.CardResponseDto;
import com.autismcard.autismcard.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@Tag(name = "Autism Card API", description = "CRUD operations para Autism ID Cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    @Operation(summary = "Listar todas as carteirinhas", description = "Retorna todas as carteirinhas sem paginação")
    @ApiResponse(responseCode = "200", description = "Carteirinhas encontradas com sucesso")
    public ResponseEntity<List<CardResponseDto>> getAll() {
        return ResponseEntity.ok(cardService.getAll());
    }

    @GetMapping("/paginated")
    @Operation(summary = "Listar carteirinhas paginadas", description = "Retorna as carteirinhas com paginação")
    @ApiResponse(responseCode = "200", description = "Carteirinhas encontradas com sucesso")
    public ResponseEntity<Page<CardResponseDto>> getPaginated(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(cardService.getPaginated(pageable));
    }

    @PostMapping
    @Operation(summary = "Criar nova carteirinha", description = "Cria uma nova carteirinha com os dados fornecidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Carteirinha criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<CardResponseDto> create(@RequestBody @Valid CardRequestDto card) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.save(card));
    }

    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Buscar carteirinha por CPF", description = "Retorna a carteirinha correspondente ao CPF informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carteirinha encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Carteirinha não encontrada")
    })
    public ResponseEntity<CardResponseDto> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(cardService.getByCpf(cpf));
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Atualizar carteirinha", description = "Atualiza os dados da carteirinha com o UUID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carteirinha atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Carteirinha não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<CardResponseDto> update(@PathVariable String uuid, @RequestBody @Valid CardRequestDto cardDto) {
        return ResponseEntity.ok(cardService.update(uuid, cardDto));
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Excluir carteirinha", description = "Remove a carteirinha com o UUID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Carteirinha excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Carteirinha não encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable String uuid) {
        cardService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}