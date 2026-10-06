package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Eventos.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter@Setter@ToString
public class EventosDTO {
    private Long id;

    @NotBlank(message = "El nombre del evento es obligatorio")
    @Size(max = 100,min=1,message = "El nombre del evento no puede contener un maximo de 100 caracteres.")
    private String nombre_evento;

    @FutureOrPresent(message = "La fecha del mantenimiento no puede ser pasada")
    private LocalDate fecha_evento;

    @NotNull(message = "La cantidad de personas en el evento es obligatorio")
    private int cantidad_personas;

    @NotNull(message = "La cantidad de horas en el evento es obligatorio")
    private int cantidad_horas;

    @NotBlank(message = "El estado del evento es obligatorio")
    @Size(max = 20,min=1,message = "El estado del evento no puede contener un maximo de 20 caracteres.")
    private String estado;

    @NotNull
    private double total_pago;

    private Long id_cliente;

    private Long id_salon;

}
