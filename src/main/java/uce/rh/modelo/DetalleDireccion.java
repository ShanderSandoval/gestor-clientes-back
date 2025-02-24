package uce.rh.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "cliente")
public class DetalleDireccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleDireccion;

    private String direccion;
    private String ciudad;
    private String codigoPostal;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
}
