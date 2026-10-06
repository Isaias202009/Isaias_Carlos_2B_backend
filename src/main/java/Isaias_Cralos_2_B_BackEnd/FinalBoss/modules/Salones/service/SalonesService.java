package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.service;

import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.dto.ClientesDTO;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.entity.ClientesEntity;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.dto.SalonesDTO;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.entity.SalonesEntity;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.repository.SalonesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonesService {

    private final SalonesRepository repo;

    public List<SalonesDTO> obtenerTodos() {
        List<SalonesEntity> entidades = repo.findAll();
        List<SalonesDTO> dtos = new ArrayList<>();
        for (SalonesEntity entity : entidades) {
            dtos.add(ConvertirADTO(entity));
        }
        return dtos;
    }

    private SalonesDTO ConvertirADTO(SalonesEntity entitysave) {

        SalonesDTO dto = new SalonesDTO();
        dto.setId_salon(entitysave.getId_salon());
        dto.setNombre_salon(entitysave.getNombre_salon());
        dto.setCapacidad(entitysave.getCapacidad());
        dto.setPrecio_renta(entitysave.getPrecio_renta());
        dto.setUbicacion(entitysave.getUbicacion());
        return dto;
    }
}