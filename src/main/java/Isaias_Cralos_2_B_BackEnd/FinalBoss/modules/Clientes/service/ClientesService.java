package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.service;

import Isaias_Cralos_2_B_BackEnd.FinalBoss.exceptions.DataNotFoundException;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.dto.ClientesDTO;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.entity.ClientesEntity;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.repository.ClientesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientesService {

    private final ClientesRepository repo;

    public ClientesDTO crearCliente(ClientesDTO dto){
        ClientesEntity entity = ConvertirAEntity(dto);
        ClientesEntity entitysave=repo.save(entity);
        return ConvertirADTO(entitysave);
    }

    private ClientesDTO ConvertirADTO(ClientesEntity entitysave){

        ClientesDTO dto = new ClientesDTO();
        dto.setId(entitysave.getId_cliente());
        dto.setNombre(entitysave.getNombre());
        dto.setApellido(entitysave.getApellido());
        dto.setTelefono(entitysave.getTelefono());
        dto.setEmail(entitysave.getEmail());
        dto.setDireccion(entitysave.getDireccion());
        return dto;
    }

    private ClientesEntity ConvertirAEntity(ClientesDTO dto){
        ClientesEntity objEntity = new ClientesEntity();
        objEntity.setNombre(dto.getNombre());
        objEntity.setApellido(dto.getApellido());
        objEntity.setTelefono(dto.getTelefono());
        objEntity.setEmail(dto.getEmail());
        objEntity.setDireccion(dto.getDireccion());
        return objEntity;
    }

    public List<ClientesDTO> obtenerTodos(){
        List<ClientesEntity> entidades = repo.findAll();
        List<ClientesDTO> dtos = new ArrayList<>();
        for (ClientesEntity entity: entidades){
            dtos.add(ConvertirADTO(entity));
        }
        return dtos;
    }

    public ClientesDTO obtenerPorId(Long id){
        ClientesEntity entity = repo.findById(id).orElseThrow(()->new DataNotFoundException("Cliente no encontrado"));
    return ConvertirADTO(entity);
    }

    public ClientesDTO actualizar(ClientesDTO dto, Long id){
        ClientesEntity entity = repo.findById(id).orElseThrow(()->new DataNotFoundException("Cliente no encontrado"));
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
        entity.setDireccion(dto.getDireccion());

        ClientesEntity actualizado = repo.save(entity);
        return ConvertirADTO(actualizado);
    }

    public boolean eliminarCliente(Long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

}
