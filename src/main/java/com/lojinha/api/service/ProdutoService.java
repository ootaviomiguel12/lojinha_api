package com.lojinha.api.service;

import com.lojinha.api.dto.ProdutoRequestDTO;
import com.lojinha.api.dto.ProdutoResponseDTO;
import com.lojinha.api.model.Categoria;
import com.lojinha.api.model.Produto;
import com.lojinha.api.repository.CategoriaRepository;
import com.lojinha.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
        return new ProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + dto.getCategoriaId()));

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto.setCategoria(categoria);

        Produto produtoSalvo = produtoRepository.save(produto);
        return new ProdutoResponseDTO(produtoSalvo);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + dto.getCategoriaId()));

        produtoExistente.setNome(dto.getNome());
        produtoExistente.setDescricao(dto.getDescricao());
        produtoExistente.setPreco(dto.getPreco());
        produtoExistente.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produtoExistente.setCategoria(categoria);

        Produto produtoAtualizado = produtoRepository.save(produtoExistente);
        return new ProdutoResponseDTO(produtoAtualizado);
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id);
    }
}