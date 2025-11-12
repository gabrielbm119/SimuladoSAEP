package com.example.simulado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRecordDTO(
        @NotBlank String nomeProduto,
        @NotNull int quantidadeProduto,
        int qtdMinimaProduto,
        int qtdMaximaProduto
){}