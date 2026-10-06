package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.repository;

import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.dto.EventosDTO;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.entity.EventosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<EventosEntity, Long> {
}
