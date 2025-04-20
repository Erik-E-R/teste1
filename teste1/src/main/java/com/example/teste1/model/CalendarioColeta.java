package com.example.teste1.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import java.util.Date;

@Document(collection = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarioColeta {

    @Id
    private String idCalendario;

    @NotBlank(message = "O bairro é obrigatório")
    private String bairro;

    @NotNull(message = "A data de coleta é obrigatória")
    private Date dataColeta;

}
