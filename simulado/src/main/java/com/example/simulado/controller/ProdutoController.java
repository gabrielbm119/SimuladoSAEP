package com.example.simulado.controller;

import com.example.simulado.dto.ProdutoRecordDTO;
import com.example.simulado.model.ProdutoModel;
import com.example.simulado.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoModel> createProduto(@RequestBody @Valid ProdutoRecordDTO produtoRecordDTO) {
        var serviceResponse = produtoService.createProduto(produtoRecordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> getAllProduto() {
        var serviceResponse = produtoService.getAllProduto();
        return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProdutoById(@PathVariable(value = "id") int idProduto) {
        var serviceResponse = produtoService.getProdutoById(idProduto);
        if(serviceResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentação não encontrada");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value = "id") int idProduto,
                                                     @RequestBody @Valid ProdutoRecordDTO produtoRecordDTO) {
        var serviceResponse = produtoService.updateProduto(idProduto, produtoRecordDTO);
        if(serviceResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentação não encontrada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable(value = "id") int idProduto) {
        var serviceResponse = produtoService.deleteProduto(idProduto);
        if(serviceResponse == true) {
            return ResponseEntity.status(HttpStatus.OK).body("Movimentação excluída com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentação não encontrada");
        }
    }
}