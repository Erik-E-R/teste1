package com.example.teste1.dto;



import com.example.teste1.model.Morador;
import com.example.teste1.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record MoradorRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O bairro é obrigatório")
        String bairro,

        @Email(message = "E-mail deve ser válido")
        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        @Size(min = 5, max = 11, message = "A senha deve conter no entre 5 á 11 caracteres.")
        @NotBlank(message = "A senha é obrigatória")
        String senha,

        UsuarioRole role
) {
}
