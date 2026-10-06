package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.repository;

import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.entity.ClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesEntity,Long> {
}
