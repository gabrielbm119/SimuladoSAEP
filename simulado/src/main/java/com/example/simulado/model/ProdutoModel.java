package com.example.simulado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBPRODUTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idProduto;
    private String nomeProduto;
    private int quantidadeProduto;
    private int qtdMinimaProduto;
    private int qtdMaximaProduto;
}