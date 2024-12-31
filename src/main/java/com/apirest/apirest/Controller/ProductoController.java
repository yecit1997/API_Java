package com.apirest.apirest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Entities.Producto;
import com.apirest.apirest.Repositories.ProductoRepository;



@RestController
@RequestMapping("/producto")
public class ProductoController {

    // Creamos una instancia de ProductoRepository
    @Autowired
    private ProductoRepository productoRepository;

    // Obtenemos los datos que tenemos en nuestra entidad
    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    // Obtenemos un producto por ID
    @GetMapping("/{id}")
    public Producto getProductoForId(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow();
    }

    // Creamos un producto y lo retornamos
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    // Actualizamos un producto por ID
    @PutMapping("/{id}")
    public Producto UpdateProductoForId(@PathVariable Long id, @RequestBody Producto detalleProducto){
        Producto producto = productoRepository.findById(id)
        .orElseThrow();

        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());

        return productoRepository.save(producto);
    }
    

    // Eliminados un producto por ID
    @DeleteMapping("/{id}")
    public String deleteProductoForId(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow();

        productoRepository.delete(producto);

        return "El producto con ID: " + id + " ha sido eliminado";
    }

}