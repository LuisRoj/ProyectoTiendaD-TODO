package DAWI.ProyectoTiendaD.TODO.model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "idpedido")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "idproducto")
    private Producto producto;
    @Column(name = "cantidadpedido")
    private Integer cantidadpedido;
    @Column(name = "precio_unitariopedido")
    private Double precio_unitariopedido;
    @Column(name = "subtotalpedido")
    private Double subtotalpedido;
}
