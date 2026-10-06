package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.entity;

import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.entity.ClientesEntity;
import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.entity.SalonesEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@ToString
@Table(name = "eventos")
public class EventosEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID_EVENTO")
    private Long id_evento;
    @Column(name = "NOMBRE_EVENTO")
    private String nombre_evento;
    @Column(name = "FECHA_EVENTO")
    private LocalDate fecha_evento;
    @Column(name = "CANTIDAD_PERSONAS")
    private int cantidad_personas;
    @Column(name = "CANTIDAD_HORAS")
    private int cantidad_horas;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "TOTAL_PAGO")
    private double total_pago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE",referencedColumnName = "ID_CLIENTE")
    private ClientesEntity clientes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SALON",referencedColumnName = "ID_SALON")
    private SalonesEntity salones;
}
