package com.lojinha.api.service;

import com.lojinha.api.model.Categoria;
import com.lojinha.api.model.Produto;
import com.lojinha.api.repository.CategoriaRepository;
import com.lojinha.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Produto> listarTodos()
    {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id)
    {
        return produtoRepository.findById(id);
    }

    public Produto salvar (Produto produto){

        if (produto.getCategoria() == null || produto.getCategoria().getId() == null)
        {
            throw new IllegalArgumentException("O produto deve estar associado a uma categoria válida");
        }
        Long categoriaId = produto.getCategoria().getId();
        Categoria categoriaExistente = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada com o ID: " + categoriaId));
    produto.setCategoria(categoriaExistente);

    return produtoRepository.save(produto);

    }
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());

        return produtoRepository.save(produtoExistente);
    }
    public void deletar(Long id){
        if (!produtoRepository.existsById(id))
        {
            throw new RuntimeException("Produto nao encontrado para o ID: " + id);
        }
        produtoRepository.deleteById(id);
    }
}
