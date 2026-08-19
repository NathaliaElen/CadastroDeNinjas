package br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.dto.MissaoRequestDTO;
import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.dto.MissaoResponseDTO;
import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.exception.MissaoExistenteException;
import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.exception.MissaoNaoEncontradaException;
import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.model.MissaoModel;
import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.repository.MissaoRepository;

@Service
public class MissaoService {

  private final MissaoRepository missaoRepository;

  public MissaoService(MissaoRepository missaoRepository) {
    this.missaoRepository = missaoRepository;
  }

  private MissaoResponseDTO toResponseDTO(MissaoModel missao) {
    return new MissaoResponseDTO(
        missao.getId(),
        missao.getNome(),
        missao.getDificuldade(),
        missao.getCriadoEm());
  }
  
  private MissaoModel buscarMissaoPorId(Long id) {
    return missaoRepository.findById(id)
            .orElseThrow(MissaoNaoEncontradaException::new);
  }

  // criar uma missão (post)
  public MissaoResponseDTO criar(MissaoRequestDTO dto) {

    var missaoExistente = missaoRepository.findByNome(dto.nome());

    if (missaoExistente.isPresent()) {
      throw new MissaoExistenteException();
    }

    MissaoModel missao = new MissaoModel();

    missao.setNome(dto.nome());
    missao.setDificuldade(dto.dificuldade());

    var missaoCriada = missaoRepository.save(missao);

    return toResponseDTO(missaoCriada);

  }

  // buscar todas as missões (get)
  public List<MissaoResponseDTO> listarTodas() {

    var listarTodasMissoes = missaoRepository.findAll();

    return listarTodasMissoes.stream()
      .map(this::toResponseDTO)
      .toList();

  }

  // buscar uma missão por id (get)
  public MissaoResponseDTO buscarPorId(Long id) {

    var missao = buscarMissaoPorId(id);

    return toResponseDTO(missao);

  }

  // editar informações de uma missão (put)
  public MissaoResponseDTO editar(Long id, MissaoRequestDTO dto) {

    var missao = buscarMissaoPorId(id);

    missao.setNome(dto.nome());
    missao.setDificuldade(dto.dificuldade());

    var missaoAtualizada = missaoRepository.save(missao);

    return toResponseDTO(missaoAtualizada);

  }

  // deletar uma missão (delete)
  public void deletar(Long id) {

    buscarMissaoPorId(id);

    missaoRepository.deleteById(id);

  }
  
}
