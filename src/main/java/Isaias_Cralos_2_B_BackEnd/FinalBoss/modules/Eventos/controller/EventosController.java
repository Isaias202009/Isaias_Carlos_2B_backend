package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.controller;

import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.dto.ClientesDTO;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.entity.ClientesEntity;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.service.ClientesService;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.dto.EventosDTO;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.service.EventosService;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.entity.SalonesEntity;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.response.ApiResponse;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/eventos")
@CrossOrigin
public class EventosController {

    private final EventosService service;

    public EventosController(EventosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EventosDTO>>
    nuevoevento(@Valid @RequestBody EventosDTO json){
        try {
            EventosDTO dto=service.crearEvento(json);
            ApiResponse<EventosDTO> response= new ApiResponse<>(true,"Proceso completado",dto);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse<EventosDTO> respuestaError= new ApiResponse<>(false,"El proceso de insercion no se pudo completar",json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EventosDTO>>>
    obtenerDatos(){
        try {
            List<EventosDTO>lista=service.obtenerTodos();
            ApiResponse<List<EventosDTO>>respuestaExitosa= new ApiResponse<>(true,"Proceso completado",lista);
            return ResponseEntity.ok(respuestaExitosa);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse<List<EventosDTO>> respuestaError= new ApiResponse<>(false,"No se pudo obtener los datos de los clientes",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventosDTO>>obtenerDatosId(@PathVariable Long id){
        try {
            EventosDTO lista= service.obtenerPorId(id);
            ApiResponse<EventosDTO>respuestaExitosa= new ApiResponse<>(true,"Proceso completado",lista);
            return ResponseEntity.ok(respuestaExitosa);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse<EventosDTO> respuestaError= new ApiResponse<>(false,"No se pudo obtener los datos del cliente",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventosDTO>>actualizarCliente(@PathVariable Long id,@Valid@RequestBody EventosDTO dto){
        try {
            EventosDTO data = service.actualizar(dto,id);
            if(data != null){
                ApiResponse<EventosDTO> respuestaExitosa= new ApiResponse<>(true,"Proceso completado",data);
                log.info("El evento con ID "+id+",Fue actualizado.");
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<EventosDTO>respuestaFallida = new ApiResponse<>(true,"Proceso no completado");
            log.info("El evento con ID "+id+",no se pudo actualizar.");
            return ResponseEntity.ok(respuestaFallida);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse<EventosDTO> respuestaError= new ApiResponse<>(false,"El proceso de actualizacion no se pudo completar",dto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>eliminarDatos(@PathVariable Long id) {
        try {
            boolean respuesta = service.eliminarEvento(id);
            if (respuesta) {
                ApiResponse<Void> respuestaExitosa = new ApiResponse<>(true, "Evento con ID " + id + " ha sido eliminado");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuestaExitosa);
            }
            ApiResponse<Void> noEncontrado = new ApiResponse<>(false, "El evento con ID:" + id + "no se encontro");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Void> respuestaError = new ApiResponse<>(false, "No se pudo eliminar el evento seleccionado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}
