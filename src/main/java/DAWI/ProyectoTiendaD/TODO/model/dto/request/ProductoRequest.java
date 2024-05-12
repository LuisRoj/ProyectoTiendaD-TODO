package DAWI.ProyectoTiendaD.TODO.model.dto.request;
import lombok.Data;

@Data
public class ProductoRequest {
    private Integer idproducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String marca;
    private String imagen;
}
