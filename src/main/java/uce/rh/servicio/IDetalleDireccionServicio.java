package uce.rh.servicio;

import uce.rh.modelo.DetalleDireccion;
import java.util.List;

public interface IDetalleDireccionServicio {
    List<DetalleDireccion> listarDirecciones();
    DetalleDireccion guardarDireccion(DetalleDireccion direccion);
    void eliminarDireccion(Integer id);
}
