package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.repository;

import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.entity.SalonesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonesRepository extends JpaRepository<SalonesEntity,Long> {
}
