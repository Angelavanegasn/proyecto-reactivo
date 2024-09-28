package proyecto.Spring_webFlux.models.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "productos")
@Data
public class Producto {

    @Id
    private String id;
    private String nombre;
    private String precio;
    private Date createAt;

    public Producto() {

    }
    public Producto(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
}
