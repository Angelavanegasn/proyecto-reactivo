package proyecto.Spring_webFlux;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import proyecto.Spring_webFlux.models.dao.ProductoDao;
import proyecto.Spring_webFlux.models.documents.Producto;
import reactor.core.publisher.Flux;

import java.util.Date;


@SpringBootApplication
public class SpringWebFluxApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebFluxApplication.class, args);
	}

	private static  final Logger log = LoggerFactory.getLogger(SpringWebFluxApplication.class);

	@Autowired
	private ProductoDao dao;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	@Override
	public void run(String... args) throws Exception {

		mongoTemplate.dropCollection("productos").subscribe();

		Flux.just(new Producto("Celular huawei","460.000"),
				new Producto("Celular iphone","470.000"),
				new Producto("Celular samsung","480.000"),
				new Producto("portatil HP","490.000"),
				new Producto("portatil mc","560.000"),
				new Producto("portatil lenovo","600.000"),
				new Producto("televisor LG","680.000")

				)
				.flatMap(producto -> {
						producto.setCreateAt(new Date());
						return dao.save(producto);
	})
				.subscribe(producto -> log.info("ha finalizado" + producto.getId() + " " + producto.getNombre() ));

	}
}
