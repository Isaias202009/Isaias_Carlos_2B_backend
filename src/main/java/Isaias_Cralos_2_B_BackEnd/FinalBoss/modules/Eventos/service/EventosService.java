package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.service;

import Isaias_Cralos_2_B_BackEnd.FinalBoss.exceptions.DataNotFoundException;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.dto.ClientesDTO;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.entity.ClientesEntity;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.repository.ClientesRepository;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.dto.EventosDTO;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.entity.EventosEntity;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.repository.EventosRepository;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.entity.SalonesEntity;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.repository.SalonesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventosService {

    private final EventosRepository repo;
    private final ClientesRepository clientes_repo;
    private final SalonesRepository salones_repo;

    public EventosDTO crearEvento(EventosDTO dto) {
        EventosEntity entity = ConvertirAEntity(dto);
        EventosEntity entitysave = repo.save(entity);
        return ConvertirADTO(entitysave);
    }

    private EventosDTO ConvertirADTO(EventosEntity entitysave) {

        EventosDTO dto = new EventosDTO();
        dto.setId(entitysave.getId_evento());
        dto.setNombre_evento(entitysave.getNombre_evento());
        dto.setCantidad_personas(entitysave.getCantidad_personas());
        dto.setCantidad_horas(entitysave.getCantidad_horas());
        dto.setFecha_evento(entitysave.getFecha_evento());
        dto.setEstado(entitysave.getNombre_evento());
        dto.setTotal_pago(entitysave.getTotal_pago());
        if (entitysave.getClientes() != null) {
            dto.setId_cliente(entitysave.getClientes().getId_cliente());
        }
        if (entitysave.getSalones() != null) {
            dto.setId_salon(entitysave.getSalones().getId_salon());
        }
        return dto;
    }

    private EventosEntity ConvertirAEntity(EventosDTO dto) {
        EventosEntity objEntity = new EventosEntity();
        objEntity.setNombre_evento(dto.getNombre_evento());
        objEntity.setCantidad_personas(dto.getCantidad_personas());
        objEntity.setCantidad_horas(dto.getCantidad_horas());
        objEntity.setFecha_evento(dto.getFecha_evento());
        objEntity.setEstado(dto.getNombre_evento());
        objEntity.setTotal_pago(dto.getTotal_pago());
        if (dto.getId_cliente() != null) {
            ClientesEntity objClienteEntity = clientes_repo.findById(dto.getId_cliente()).orElseThrow(() -> new DataNotFoundException("Cliente no encontrado."));
            objEntity.setClientes(objClienteEntity);
        }
        if (dto.getId_salon() != null) {
            SalonesEntity objSalonesEntity = salones_repo.findById(dto.getId_cliente()).orElseThrow(() -> new DataNotFoundException("salon no encontrado."));
            objEntity.setSalones(objSalonesEntity);
        }
        return objEntity;
    }

    public List<EventosDTO> obtenerTodos() {
        List<EventosEntity> entidades = repo.findAll();
        List<EventosDTO> dtos = new ArrayList<>();
        for (EventosEntity entity : entidades) {
            dtos.add(ConvertirADTO(entity));
        }
        return dtos;
    }

    public EventosDTO obtenerPorId(Long id) {
        EventosEntity entity = repo.findById(id).orElseThrow(() -> new DataNotFoundException("evento no encontrado"));
        return ConvertirADTO(entity);
    }

    public EventosDTO actualizar(EventosDTO dto, Long id) {
        EventosEntity entity = repo.findById(id).orElseThrow(() -> new DataNotFoundException("evento no encontrado"));
        entity.setNombre_evento(dto.getNombre_evento());
        entity.setCantidad_personas(dto.getCantidad_personas());
        entity.setCantidad_horas(dto.getCantidad_horas());
        entity.setFecha_evento(dto.getFecha_evento());
        entity.setEstado(dto.getNombre_evento());
        entity.setTotal_pago(dto.getTotal_pago());
        ClientesEntity cli = clientes_repo.findById(dto.getId_cliente()).orElseThrow(()-> new DataNotFoundException("Cliente no encontrado"));
        SalonesEntity salon = salones_repo.findById(dto.getId_salon()).orElseThrow(()-> new DataNotFoundException("Salon no encontrado"));
        EventosEntity actualizado = repo.save(entity);

        entity.setClientes(cli);
        entity.setSalones(salon);
        return ConvertirADTO(actualizado);
    }

    public boolean eliminarEvento(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
