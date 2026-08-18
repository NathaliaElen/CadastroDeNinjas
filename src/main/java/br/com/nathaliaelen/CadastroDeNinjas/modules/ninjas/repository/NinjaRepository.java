package br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.model.NinjaModel;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
  
  Optional<NinjaModel> findByEmail(String email);

}
