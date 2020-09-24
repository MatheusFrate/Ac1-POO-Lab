package com.example.ac1.controller;

import java.net.URI;
import java.util.List;

import com.example.ac1.model.Pedido;
import com.example.ac1.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoRepository repository;

    @PostMapping
    public ResponseEntity<Pedido> cadastro(@RequestBody Pedido pedido){
        pedido = repository.save(pedido);
        final URI uri = URI.create("http://localhost:8080/pedidos/" + pedido.getCodigo());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public List<Pedido> getPedidos(){
        return repository.getAllPedidos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pedido> getPedido(@PathVariable final int codigo){
        final Pedido pedido = repository.getPedidoById(codigo);

        if(pedido != null)
            return ResponseEntity.ok(pedido);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Pedido> remover(@PathVariable final int codigo){
        final Pedido pedido = repository.getPedidoById(codigo);

        if(pedido != null){
            repository.remover(pedido);
            return ResponseEntity.noContent().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pedido> alterar(@PathVariable int codigo, @RequestBody Pedido pedido){

        if(repository.getPedidoById(codigo) != null){
            pedido.setCodigo(codigo);

            pedido = repository.update(pedido);
            return ResponseEntity.ok(pedido);
        }
        else
            return ResponseEntity.notFound().build();
    }

}
