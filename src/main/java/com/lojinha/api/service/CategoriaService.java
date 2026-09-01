package com.lojinha.api.service;
import com.lojinha.api.model.Categoria;
import com.lojinha.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas()
    {
        return categoriaRepository.findAll();
    }
    public Optional<Categoria> buscarPorId(long id)
    {
        return categoriaRepository.findById(id);
    }

    public Categoria salvar (Categoria categoria)
    {
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()){
            throw new IllegalArgumentException("O nome da categoria nao pode estar vazio.");
        }
        return categoriaRepository.save(categoria);
    }

    public void deletar (Long id)
    {
        if (!categoriaRepository.existsById(id))
        {
            throw new RuntimeException("Categoria nao econtrada para o ID: " + id);

        }
        categoriaRepository.deleteById(id);
    }

}
