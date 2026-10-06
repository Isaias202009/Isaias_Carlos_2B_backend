package Isaias_Cralos_2_B_BackEnd.FinalBoss.modules.Clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class ClientesDTO {
    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 100,min=1,message = "El nombre del cliente no puede contener un maximo de 100 caracteres.")
    private String nombre;

    @NotBlank(message = "El apellido del cliente es obligatorio")
    @Size(max = 100,min=1,message = "El apellido del cliente no puede contener un maximo de 100 caracteres.")
    private String apellido;

    @NotBlank(message = "El telefono del cliente es obligatorio")
    @Size(max = 15,min=1,message = "El telefono del cliente no puede contener un maximo de 15 caracteres.")
    private String telefono;

    @Email(message = "El email no es valido")
    @NotBlank(message = "El email del cliente es obligatorio")
    @Size(max = 100,min=1,message = "El email del cliente no puede contener un maximo de 100 caracteres.")
    private String email;

    @NotBlank(message = "La direccion del cliente es obligatorio")
    @Size(max = 200,min=1,message = "La direccion del cliente no puede contener un maximo de 200 caracteres.")
    private String direccion;

}
