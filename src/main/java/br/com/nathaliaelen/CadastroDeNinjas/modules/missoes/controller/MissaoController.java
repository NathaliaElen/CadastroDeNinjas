package br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.dto.MissaoRequestDTO;
import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.dto.MissaoResponseDTO;
import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.service.MissaoService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/missoes")
public class MissaoController {

  private final MissaoService missaoService;

  public MissaoController(MissaoService missaoService) {
    this.missaoService = missaoService;
  }

  @PostMapping(version = "v1")
  public ResponseEntity<MissaoResponseDTO> salvar(
      @RequestBody @Valid MissaoRequestDTO missaoRequestDTO) {

    var missaoCriada = missaoService.criar(missaoRequestDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(missaoCriada);

  }
  
  @GetMapping(version = "v1")
  public ResponseEntity<List<MissaoResponseDTO>> listarTodas() {

    var missoes = missaoService.listarTodas();

    return ResponseEntity.status(HttpStatus.OK).body(missoes);

  }
  
  @GetMapping(value = "/{id}", version = "v1")
  public ResponseEntity<MissaoResponseDTO> buscarPorId(@PathVariable Long id) {

    var missao = missaoService.buscarPorId(id);

    return ResponseEntity.status(HttpStatus.OK).body(missao);

  }
  
  @PutMapping(value = "/{id}", version = "v1")
  public ResponseEntity<MissaoResponseDTO> editar(
      @PathVariable Long id,
      @RequestBody @Valid MissaoRequestDTO missaoRequestDTO
  ) {

    var missaoEditada = missaoService.editar(id, missaoRequestDTO);

    return ResponseEntity.status(HttpStatus.OK).body(missaoEditada);

  }

  @DeleteMapping(value = "/{id}", version = "v1")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {

    missaoService.deletar(id);

    return ResponseEntity.noContent().build();

  }

}
