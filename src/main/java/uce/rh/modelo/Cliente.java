package uce.rh.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

//Creacion de las tablas
@Entity
//Anotacion para la generacion de datos GET y SET
@Data
//Constructor Vario
@NoArgsConstructor
//Constructor con todos los argumentos
@AllArgsConstructor
//Mandar a imprimir el objeto empleado
@ToString

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCliente;
    String nombre;
    String cedula;
    String celular;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleDireccion> direcciones;
}

