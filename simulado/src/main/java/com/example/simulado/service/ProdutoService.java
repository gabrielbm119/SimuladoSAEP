package com.example.simulado.service;

import com.example.simulado.dto.ProdutoRecordDTO;
import com.example.simulado.model.ProdutoModel;
import com.example.simulado.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoService movimentacaoService;

    public ProdutoModel createProduto(ProdutoRecordDTO produtoRecordDTO) {
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoRecordDTO, produtoModel);
        return produtoRepository.save(produtoModel);
    }

    public List<ProdutoModel> getAllProduto() {
        return produtoRepository.findAll();
    }

    public Object getProdutoById(int idProduto) {
        Optional<ProdutoModel> produtoModel = produtoRepository.findByIdProduto(idProduto);
        if (produtoModel.isPresent()) {
            return produtoModel.get();
        } else {
            return null;
        }
    }

    public Object updateProduto(int idProduto, ProdutoRecordDTO produtoRecordDTO) {
        Optional<ProdutoModel> produtoModel = produtoRepository.findByIdProduto(idProduto);
        if (produtoModel.isPresent()) {
            var produtoExistente = produtoModel.get();
            int quantidadeAntiga = produtoExistente.getQuantidadeProduto();
            BeanUtils.copyProperties(produtoRecordDTO, produtoExistente);
            ProdutoModel produtoAtualizado = produtoRepository.save(produtoExistente);
            int quantidadeNova = produtoAtualizado.getQuantidadeProduto();

            String tipoMovimentacao;
            if (quantidadeNova > quantidadeAntiga) {
                tipoMovimentacao = "ENTRADA";
            } else if (quantidadeNova < quantidadeAntiga) {
                tipoMovimentacao = "SAIDA";
            } else {
                tipoMovimentacao = "NEUTRO";
            }
            movimentacaoService.registrarMovimentacao(tipoMovimentacao, produtoAtualizado);

            return produtoAtualizado;
        } else  {
            return null;
        }
    }

    public boolean deleteProduto(int idProduto) {
        Optional<ProdutoModel> produtoModel = produtoRepository.findByIdProduto(idProduto);
        if (produtoModel.isPresent()) {
            produtoRepository.deleteById(idProduto);
            return true;
        } else {
            return false;
        }
    }
}