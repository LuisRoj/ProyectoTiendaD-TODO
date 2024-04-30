package DAWI.ProyectoTiendaD.TODO.model.bd;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private int idproducto;
    @Column(name = "nomidproducto")
    private String nomidproducto;
    @Column(name = "descproducto")
    private String descproducto;
    @Column(name = "precioproducto")
    private Double precioproducto;
    @Column(name = "stockproducto")
    private Integer stockproducto;
    @Column(name = "marcaproducto")
    private String marcaproducto;

}
