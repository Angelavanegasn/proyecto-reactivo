package proyecto.Spring_webFlux.models.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import proyecto.Spring_webFlux.models.documents.Producto;

public interface ProductoDao  extends ReactiveMongoRepository<Producto,String> {
}
