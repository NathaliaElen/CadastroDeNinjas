package br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.service;

import org.springframework.stereotype.Service;

import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.repository.MissaoRepository;

@Service
public class MissaoService {

  private final MissaoRepository missaoRepository;

  public MissaoService(MissaoRepository missaoRepository) {
    this.missaoRepository = missaoRepository;
  }
  
}
