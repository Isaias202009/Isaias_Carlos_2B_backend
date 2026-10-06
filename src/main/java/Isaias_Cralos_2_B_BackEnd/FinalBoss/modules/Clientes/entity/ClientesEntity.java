package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.entity;

import Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.entity.EventosEntity;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name = "clientes")
public class ClientesEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long id_cliente;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DIRECCION")
    private String direccion;

    @OneToMany(mappedBy = "clientes")
    private List<EventosEntity>eventos = new ArrayList<>();
}
