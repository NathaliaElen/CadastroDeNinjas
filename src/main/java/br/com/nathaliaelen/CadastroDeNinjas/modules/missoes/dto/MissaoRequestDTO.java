package br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Representa o que o cliente envia
public record MissaoRequestDTO(

  @NotBlank
  @Size(max = 50)
  String nome,
          
  @NotBlank
  @Size(max = 50)
  String dificuldade
  
) {}
