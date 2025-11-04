package com.example.simulado.controller;

import com.example.simulado.dto.MovimentacaoRecordDTO;
import com.example.simulado.model.MovimentacaoModel;
import com.example.simulado.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<MovimentacaoModel> createMovimentacao(@RequestBody @Valid MovimentacaoRecordDTO movimentacaoRecordDTO) {
        var serviceResponse = movimentacaoService.createMovimentacao(movimentacaoRecordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResponse);
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoModel>> getAllMovimentacao() {
        var serviceResponse = movimentacaoService.getAllMovimentacao();
        return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMovimentacaoById(@PathVariable(value = "id") int idMovimentacao) {
        var serviceResponse = movimentacaoService.getMovimentacaoById(idMovimentacao);
        if(serviceResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentação não encontrada");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovimentacao(@PathVariable(value = "id") int idMovimentacao,
                                                    @RequestBody @Valid MovimentacaoRecordDTO movimentacaoRecordDTO) {
        var serviceResponse = movimentacaoService.updateMovimentacao(idMovimentacao, movimentacaoRecordDTO);
        if(serviceResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentação não encontrada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovimentacao(@PathVariable(value = "id") int idMovimentacao) {
        var serviceResponse = movimentacaoService.deleteMovimentacao(idMovimentacao);
        if(serviceResponse == true) {
            return ResponseEntity.status(HttpStatus.OK).body("Movimentação excluída com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentação não encontrada");
        }
    }
}