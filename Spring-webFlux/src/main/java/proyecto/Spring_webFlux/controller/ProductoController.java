package proyecto.Spring_webFlux.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import proyecto.Spring_webFlux.models.dao.ProductoDao;
import proyecto.Spring_webFlux.models.documents.Producto;
import reactor.core.publisher.Flux;

@Controller
public class ProductoController {

    @Autowired
    public ProductoDao dao;

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping({"/listar","/"})
        public String listar(Model model){
        Flux<Producto> productos = dao.findAll()
                .map(producto -> {
                    producto.setNombre(producto.getNombre().toUpperCase());
                    return producto;
                });

            productos.subscribe(prod -> log.info(prod.getNombre()));


        model.addAttribute("productos", productos);
        model.addAttribute("titulo","Listado de productos");
        return "listar";
    }
}
