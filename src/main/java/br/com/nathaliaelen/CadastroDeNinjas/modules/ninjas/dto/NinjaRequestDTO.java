package br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Representa o que o cliente envia
public record NinjaRequestDTO(

      @NotBlank 
      @Size(max = 50)
      String nome,
              
      @Min(0)
      int idade,
          
      @Email(message = "O campo [email] deve conter um e-mail válido.")
      String email,
                  
      @NotNull
      Long missaoId // atribuir uma missão a um ninja

) {}
