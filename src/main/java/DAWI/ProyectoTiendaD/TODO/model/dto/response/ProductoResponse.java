package DAWI.ProyectoTiendaD.TODO.model.dto.response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoResponse {
    private Boolean respuesta;
    private String mensaje;
}
