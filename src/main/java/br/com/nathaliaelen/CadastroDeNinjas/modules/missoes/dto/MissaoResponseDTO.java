package br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.dto;

import java.time.LocalDateTime;

// Representa o que a API devolve
public record MissaoResponseDTO(

  Long id,
  String nome,
  String dificuldade,
  LocalDateTime criadoEm

) {}
