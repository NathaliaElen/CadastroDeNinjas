package br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.model.MissaoModel;

public interface MissaoRepository extends JpaRepository<MissaoModel, Long> {
  
}
