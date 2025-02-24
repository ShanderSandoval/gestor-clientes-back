package uce.rh.servicio;

import org.springframework.stereotype.Service;
import uce.rh.modelo.DetalleDireccion;
import uce.rh.repositorio.DetalleDireccionRepositorio;

import java.util.List;

@Service
public class DetalleDireccionServicio implements IDetalleDireccionServicio{
    private final DetalleDireccionRepositorio detalleDireccionRepositorio;

    public DetalleDireccionServicio(DetalleDireccionRepositorio detalleDireccionRepositorio) {
        this.detalleDireccionRepositorio = detalleDireccionRepositorio;
    }
    @Override
    public List<DetalleDireccion> listarDirecciones() {
        return detalleDireccionRepositorio.findAll();
    }

    @Override
    public DetalleDireccion guardarDireccion(DetalleDireccion direccion) {
        if (direccion.getCliente() == null || direccion.getCliente().getIdCliente() == null) {
            throw new IllegalArgumentException("La dirección debe estar asociada a un cliente válido.");
        }
        return detalleDireccionRepositorio.save(direccion);
    }

    @Override
    public void eliminarDireccion(Integer id) {
        if (!detalleDireccionRepositorio.existsById(id)) {
            throw new IllegalArgumentException("No se encontró la dirección con ID: " + id);
        }
        detalleDireccionRepositorio.deleteById(id);
    }
}
