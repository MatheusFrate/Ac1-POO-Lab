package com.example.ac1.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.ac1.model.Pedido;

import org.springframework.stereotype.Component;

@Component
public class PedidoRepository {
    private List<Pedido> pedidos = new ArrayList <Pedido>();

    public Pedido save(Pedido pedido){
        pedido.setCodigo(pedidos.size()+1);
        pedidos.add(pedido);
        return pedido;
    }

    public List<Pedido> getAllPedidos(){
        return pedidos;
    }

    public Pedido getPedidoById( int codigo){
        for ( Pedido aux: pedidos){
            if(aux.getCodigo() == codigo)
                return aux;
        }
        return null;
    }
     public void remover( Pedido pedido){
        pedidos.remove(pedido);
     }

     public Pedido update(Pedido pedido){
         Pedido aux = getPedidoById(pedido.getCodigo());
         if(aux != null){
             aux.setValor(pedido.getValor());
             aux.setDescricao(pedido.getDescricao());
             aux.setCliente(pedido.getCliente());
             aux.setDataPedido(pedido.getDataPedido());
         }
         return aux;
     }

}
