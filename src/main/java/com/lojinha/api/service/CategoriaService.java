package com.lojinha.api.service;

import com.lojinha.api.dto.CategoriaDTO;
import com.lojinha.api.model.Categoria;
import com.lojinha.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaDTO::new)
                .toList();
    }

    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));
        return new CategoriaDTO(categoria);
    }

    public CategoriaDTO salvar(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());

        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoriaSalva);
    }

    public CategoriaDTO atualizar(Long id, CategoriaDTO dto) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        categoriaExistente.setNome(dto.getNome());

        Categoria categoriaAtualizada = categoriaRepository.save(categoriaExistente);
        return new CategoriaDTO(categoriaAtualizada);
    }

    public void deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}