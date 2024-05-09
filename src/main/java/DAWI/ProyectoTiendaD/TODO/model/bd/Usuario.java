package DAWI.ProyectoTiendaD.TODO.model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;
    @Column(name = "nomusuario")
    private String nomusuario;
    @Column(name = "apeusuario")
    private String apellidos;
    @Column(name = "correousuario")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "tlfusuario")
    private String telefono;
    @Column(name = "dirusuario")
    private String direccion;
    @Column(name = "activo")
    private Boolean activo;
    @ManyToMany(cascade =  CascadeType.MERGE,
            fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns =
    @JoinColumn(name = "idusuario"),
            inverseJoinColumns = @JoinColumn(name = "idrol"))
    private Set<Rol> roles;
}
