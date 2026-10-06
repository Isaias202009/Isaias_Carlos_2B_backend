package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Salones.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalonesDTO {


    private Long id_salon;

    @NotBlank(message = "El nombre del salon es obligatorio")
    @Size(max = 100,min=1,message = "El nombre del salon no puede contener un maximo de 100 caracteres.")
    private String nombre_salon;

    @NotNull(message = "La capacidad del salon es obligatoria")
    @Size(min = 1)
    private int capacidad;

    @NotNull(message = "El precio de renta del salon es obligatoria")
    @Size(min = 0)
    private double precio_renta;

    @NotBlank(message = "La ubicacion del salon es obligatorio")
    @Size(max = 100,min=1,message = "La ubicacion del salon no puede contener un maximo de 100 caracteres.")
    private String ubicacion;
}
