package com.example.teste1.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

import java.util.Date;

@Document(collection = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Notificacao {

    @Id
    private String idNotificacao;

    @DBRef
    @NotNull(message = "O morador é obrigatório")
    private Morador morador;

    @DBRef
    @NotNull(message = "O calendário de coleta é obrigatório")
    private CalendarioColeta calendario;

    @NotNull(message = "A data de envio é obrigatória")
    private Date dataEnvio;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    @Email(message = "E-mail deve ser válido")
    @NotBlank(message = "O e-mail enviado é obrigatório")
    private String emailEnviado;


}
