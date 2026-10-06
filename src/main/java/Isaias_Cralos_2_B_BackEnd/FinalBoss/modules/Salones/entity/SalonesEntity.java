package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.entity;

import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.entity.EventosEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "salones")
public class SalonesEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID_SALON")
    private Long id_salon;
    @Column(name = "NOMBRE_SALON")
    private String nombre_salon;
    @Column(name = "CAPACIDAD")
    private int capacidad;
    @Column(name = "PRECIO_RENTA")
    private double precio_renta;
    @Column(name = "UBICACION")
    private String ubicacion;

    @OneToMany(mappedBy = "salones")
    private List<EventosEntity> eventos = new ArrayList<>();
}
