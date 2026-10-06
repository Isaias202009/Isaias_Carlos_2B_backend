package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.controller;


import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.dto.ClientesDTO;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.dto.SalonesDTO;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.service.SalonesService;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/salones")
@CrossOrigin
public class SalonesController {
    private  final SalonesService service;

    public SalonesController(SalonesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SalonesDTO>>>
    obtenerDatos(){
        try {
            List<SalonesDTO>lista=service.obtenerTodos();
            ApiResponse<List<SalonesDTO>>respuestaExitosa= new ApiResponse<>(true,"Proceso completadoooooooooo",lista);
            return ResponseEntity.ok(respuestaExitosa);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse<List<SalonesDTO>> respuestaError= new ApiResponse<>(false,"No se pudo obtener los datos de los salones",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}
