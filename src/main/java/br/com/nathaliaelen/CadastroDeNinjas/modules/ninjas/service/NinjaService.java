package br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.exception.MissaoNaoEncontradaException;
import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.model.MissaoModel;
import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.repository.MissaoRepository;
import br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.dto.NinjaRequestDTO;
import br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.dto.NinjaResponseDTO;
import br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.exception.EmailCadastradoException;
import br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.exception.NinjaNaoEncontradoException;
import br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.model.NinjaModel;
import br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.repository.NinjaRepository;

@Service
public class NinjaService {
  
  private final NinjaRepository ninjaRepository;
  private final MissaoRepository missaoRepository;

  public NinjaService(
      NinjaRepository ninjaRepository,
      MissaoRepository missaoRepository
  ) {
    this.ninjaRepository = ninjaRepository;
    this.missaoRepository = missaoRepository;
  }

  // método privado para converter Model → ResponseDTO
  private NinjaResponseDTO toResponseDTO(NinjaModel ninja) {
    return new NinjaResponseDTO(
        ninja.getId(),
        ninja.getNome(),
        ninja.getIdade(),
        ninja.getEmail(),
        ninja.getMissao().getId(),
        ninja.getCriadoEm());
  }
  
  private NinjaModel buscarNinjaPorId(Long id) {
    return ninjaRepository.findById(id)
            .orElseThrow(NinjaNaoEncontradoException::new);
  }

  private MissaoModel buscarMissaoPorId(Long id) {
    return missaoRepository.findById(id)
            .orElseThrow(MissaoNaoEncontradaException::new);
  }

  // criar um ninja (post)
  public NinjaResponseDTO criar(NinjaRequestDTO dto) {

    var ninjaExistente = ninjaRepository.findByEmail(dto.email());

    if (ninjaExistente.isPresent()) {
        throw new EmailCadastradoException();
    }

    var missao = buscarMissaoPorId(dto.missaoId());

    NinjaModel ninja = new NinjaModel();

    ninja.setNome(dto.nome());
    ninja.setIdade(dto.idade());
    ninja.setEmail(dto.email());
    ninja.setMissao(missao);

    var ninjaSalvo = ninjaRepository.save(ninja);

    return toResponseDTO(ninjaSalvo);

  }

    // listar todos os ninjas (get)
    public List<NinjaResponseDTO> listarTodos() {

      var listarTodosNinjas = ninjaRepository.findAll();

      return listarTodosNinjas.stream()
        .map(this::toResponseDTO)
        .toList();
        
    }

    // buscar um ninja por id (get)
    public NinjaResponseDTO buscarPorId(Long id) {

      var ninja = buscarNinjaPorId(id);

      return toResponseDTO(ninja);
    
    }

    // editar informações de um ninja (put)
    public NinjaResponseDTO editar(Long id, NinjaRequestDTO dto) {

      var ninja = buscarNinjaPorId(id);
      var missao = buscarMissaoPorId(dto.missaoId());

      ninja.setNome(dto.nome());
      ninja.setIdade(dto.idade());
      ninja.setEmail(dto.email());
      ninja.setMissao(missao);

      var ninjaAtualizado = ninjaRepository.save(ninja);

      return toResponseDTO(ninjaAtualizado);

    }
  
    // deletar um ninja pelo id (delete)
    public void deletar(Long id) {

      buscarNinjaPorId(id);
  
      ninjaRepository.deleteById(id);
    
    }
  
}
