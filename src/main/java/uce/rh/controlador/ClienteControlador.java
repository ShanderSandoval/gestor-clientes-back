package uce.rh.controlador;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.rh.excepcion.RecursoNoEncontradoExcepcion;
import uce.rh.modelo.Cliente;
import uce.rh.servicio.ClienteServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController// Define que esta clase es un controlador RESTful que devuelve datos en formato JSON o XML.
//http://localhost:8080/cm-app/
@RequestMapping("cm-app") // Define la URL base para todas las rutas en esta clase como /rh-app.
//Se indica que recibimos peticiones de otro puerto (El 3000 en este caso que viene de React)
@CrossOrigin(value = "*")
public class ClienteControlador {

    //Con esta informacion mandamos la informacion a consola
    private static final Logger logger =
            LoggerFactory.getLogger(ClienteControlador.class);

    @Autowired
    private ClienteServicio clienteServicio;

    //http:localhost:8080/cm-app/clientes
    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes(){
        var clientes = clienteServicio.listarClientes();
        clientes.forEach((cliente -> logger.info(cliente.toString())));
        return clientes;
    }


    @PostMapping("/clientes")
    public Cliente agregarCliente(@RequestBody Cliente cliente){
        logger.info("Cliente a agregar: " + cliente);
        return clienteServicio.guardarCliente(cliente);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente>
    obtenerClientePorId(@PathVariable Integer id){
        Cliente cliente = clienteServicio.buscarClientePorId(id);
        if(cliente == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente>
    actualizarCliente(@PathVariable Integer id,
                      @RequestBody Cliente clienteRecibido){
        Cliente cliente = clienteServicio.buscarClientePorId(id);
        if (cliente == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        cliente.setNombre(clienteRecibido.getNombre());
        cliente.setCedula(clienteRecibido.getCedula());
        cliente.setDireccion(clienteRecibido.getDireccion());
        cliente.setCelular(clienteRecibido.getCelular());
        clienteServicio.guardarCliente(cliente);
        logger.info("Cliente a editar: " + cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarCliente(@PathVariable Integer id){
        Cliente cliente = clienteServicio.buscarClientePorId(id);
        if(cliente == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        clienteServicio.eliminarCliente(cliente);
        // Json {"eliminado": "true"}
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        logger.info("Cliente a eliminar: " + cliente);
        return ResponseEntity.ok(respuesta);
    }
}
