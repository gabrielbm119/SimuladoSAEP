package com.example.simulado.service;

import com.example.simulado.controller.MovimentacaoController;
import com.example.simulado.dto.MovimentacaoRecordDTO;
import com.example.simulado.model.MovimentacaoModel;
import com.example.simulado.repositories.MovimentacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoModel createMovimentacao(MovimentacaoRecordDTO movimentacaoRecordDTO) {
        var movimentacaoModel = new MovimentacaoModel();
        BeanUtils.copyProperties(movimentacaoRecordDTO, movimentacaoModel);
        return movimentacaoRepository.save(movimentacaoModel);
    }

    public List<MovimentacaoModel> getAllMovimentacao() {
        return movimentacaoRepository.findAll();
    }

    public Object getMovimentacaoById(int idMovimentacao) {
        Optional<MovimentacaoModel> movimentacaoModel = movimentacaoRepository.findById(idMovimentacao);
        if (movimentacaoModel.isPresent()) {
            return movimentacaoModel.get();
        } else {
            return null;
        }
    }
}