package uce.rh.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.rh.modelo.DetalleDireccion;
import uce.rh.servicio.DetalleDireccionServicio;

import java.util.List;

@RestController
@RequestMapping("/cm-app")
@CrossOrigin(value = "*")
public class DetalleDireccionControlador {

    private final DetalleDireccionServicio detalleDireccionServicio;

    public DetalleDireccionControlador(DetalleDireccionServicio detalleDireccionServicio) {
        this.detalleDireccionServicio = detalleDireccionServicio;
    }

    @GetMapping("/direcciones")
    public List<DetalleDireccion> obtenerDirecciones() {
        return detalleDireccionServicio.listarDirecciones();
    }

    @PostMapping("/direcciones")
    public ResponseEntity<?> guardarDireccion(@RequestBody DetalleDireccion direccion) {
        if (direccion.getCliente() == null || direccion.getCliente().getIdCliente() == null) {
            return ResponseEntity.badRequest().body("Error: La dirección debe estar asociada a un cliente válido.");
        }
        try {
            DetalleDireccion nuevaDireccion = detalleDireccionServicio.guardarDireccion(direccion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDireccion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la dirección: " + e.getMessage());
        }
    }

    @DeleteMapping("/direcciones/{id}")
    public ResponseEntity<?> eliminarDireccion(@PathVariable Integer id) {
        try {
            detalleDireccionServicio.eliminarDireccion(id);
            return ResponseEntity.ok("Dirección eliminada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la dirección: " + e.getMessage());
        }
    }
}
