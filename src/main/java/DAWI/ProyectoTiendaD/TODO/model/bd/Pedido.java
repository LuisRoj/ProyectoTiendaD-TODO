package DAWI.ProyectoTiendaD.TODO.model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpedido;
    @Column(name = "numero")
    private String numero;
    @Column(name = "fechacreacion")
    private Date fechacreacion;
    @Column(name = "total")
    private Double total;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "idusuario")
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    @Column(name = "estadopedido")
    private EstadoPedido estadopedido;

    public enum EstadoPedido {
        PENDIENTE, EN_PREPARACION, ENVIADO, ENTREGADO, CANCELADO
    }
}

