package com.example.simulado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TBMOVIMENTACAO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMovimentacao;
    private String tipoMovimentacao;
    @ManyToOne
    @JoinColumn(name = "produto_model_id_produto")
    private ProdutoModel produtoModel;
    private LocalDateTime dataHoraMovimentacao;
}