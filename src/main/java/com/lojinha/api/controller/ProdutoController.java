package com.lojinha.api.controller;

import com.lojinha.api.dto.ProdutoRequestDTO;
import com.lojinha.api.dto.ProdutoResponseDTO;
import com.lojinha.api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO novoProduto = produtoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO produtoAtualizado = produtoService.atualizar(id, dto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}