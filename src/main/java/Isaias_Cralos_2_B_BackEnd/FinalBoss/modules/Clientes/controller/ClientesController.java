package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.controller;

import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.dto.ClientesDTO;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.service.ClientesService;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin
public class ClientesController {

    private final ClientesService service;

    public ClientesController(ClientesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientesDTO>>
    nuevoCliente(@Valid@RequestBody ClientesDTO json){
        try {
            ClientesDTO dto=service.crearCliente(json);
            ApiResponse<ClientesDTO> response= new ApiResponse<>(true,"Proceso completado",dto);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse<ClientesDTO> respuestaError= new ApiResponse<>(false,"El proceso de insercion no se pudo completar",json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientesDTO>>>
    obtenerDatos(){
        try {
            List<ClientesDTO>lista=service.obtenerTodos();
            ApiResponse<List<ClientesDTO>>respuestaExitosa= new ApiResponse<>(true,"Proceso completado",lista);
            return ResponseEntity.ok(respuestaExitosa);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse<List<ClientesDTO>> respuestaError= new ApiResponse<>(false,"No se pudo obtener los datos de los clientes",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientesDTO>>obtenerDatosId(@PathVariable Long id){
        try {
            ClientesDTO lista= service.obtenerPorId(id);
            ApiResponse<ClientesDTO>respuestaExitosa= new ApiResponse<>(true,"Proceso completado",lista);
            return ResponseEntity.ok(respuestaExitosa);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse<ClientesDTO> respuestaError= new ApiResponse<>(false,"No se pudo obtener los datos del cliente",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientesDTO>>actualizarCliente(@PathVariable Long id,@Valid@RequestBody ClientesDTO dto){
        try {
            ClientesDTO data = service.actualizar(dto,id);
            if(data != null){
                ApiResponse<ClientesDTO> respuestaExitosa= new ApiResponse<>(true,"Proceso completado",data);
                log.info("El cliente con ID "+id+",Fue actualizado.");
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<ClientesDTO>respuestaFallida = new ApiResponse<>(true,"Proceso no completado");
            log.info("El cliente con ID "+id+",no se pudo actualizar.");
            return ResponseEntity.ok(respuestaFallida);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse<ClientesDTO> respuestaError= new ApiResponse<>(false,"El proceso de actualizacion no se pudo completar",dto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>eliminarDatos(@PathVariable Long id) {
        try {
            boolean respuesta = service.eliminarCliente(id);
            if (respuesta) {
                ApiResponse<Void> respuestaExitosa = new ApiResponse<>(true, "Cliente con ID " + id + " ha sido eliminado");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuestaExitosa);
            }
            ApiResponse<Void> noEncontrado = new ApiResponse<>(false, "El cliente con ID:" + id + "no se encontro");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Void> respuestaError = new ApiResponse<>(false, "No se pudo eliminar el cliente seleccionado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}
