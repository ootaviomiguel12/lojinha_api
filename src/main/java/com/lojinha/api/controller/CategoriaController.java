package com.lojinha.api.controller;

import com.lojinha.api.model.Categoria;
import com.lojinha.api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    //listar todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        List<Categoria> categorias = categoriaService.listarTodas();
        return ResponseEntity.ok(categorias); //retorna HTTP 200 Ok
    }


    //buscar categorias por Id
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(categoria -> ResponseEntity.ok(categoria)) //retorna http 200 Ok + categoria
                .orElse(ResponseEntity.notFound().build()); //retorna http 404 Not Found se nao existir
    }


    //criar novas categorias
    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria); //retorna http 201 created
    }

    //deletar categorias por Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Long id){
        try{
            categoriaService.deletar(id);
            return ResponseEntity.noContent().build();//http 204 no content
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build(); //http 404 not found
        }
    }
}
