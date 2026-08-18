package br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.dto;

import java.time.LocalDateTime;

// Representa o que a API devolve
public record NinjaResponseDTO(

      Long id,
                  
      String nome,
                  
      int idade,
                  
      String email,
            
      Long missaoId,
                  
      LocalDateTime criadoEm
    
) {}
