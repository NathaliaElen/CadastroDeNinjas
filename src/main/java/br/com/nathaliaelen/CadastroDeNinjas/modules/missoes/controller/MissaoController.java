package br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.dto.MissaoRequestDTO;
import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.dto.MissaoResponseDTO;
import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.service.MissaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/missoes")
public class MissaoController {

  private final MissaoService missaoService;

  public MissaoController(MissaoService missaoService) {
    this.missaoService = missaoService;
  }

  @Operation(description = "Criar uma missão")
  @PostMapping(version = "v1")
  public ResponseEntity<MissaoResponseDTO> salvar(
      @RequestBody @Valid MissaoRequestDTO missaoRequestDTO) {

    var missaoCriada = missaoService.criar(missaoRequestDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(missaoCriada);

  }
  
  @Operation(description = "Listar todas as missões")
  @GetMapping(version = "v1")
  public ResponseEntity<List<EntityModel<MissaoResponseDTO>>> listarTodas() {

    var missoes = missaoService.listarTodas();

    var missoesComLinks = missoes.stream()
        .map(missao -> {
            var resource = EntityModel.of(missao);

            resource.add(
                linkTo(methodOn(MissaoController.class)
                    .buscarPorId(missao.id()))
                    .withSelfRel()
            );

            return resource;
        })
        .toList();

      return ResponseEntity.ok(missoesComLinks);

  }
  
  @Operation(description = "Buscar uma missão")
  @GetMapping(value = "/{id}", version = "v1")
  public ResponseEntity<EntityModel<MissaoResponseDTO>> buscarPorId(@PathVariable Long id) {

    var missao = missaoService.buscarPorId(id);

    var resource = EntityModel.of(missao);

    resource.add(
      linkTo(methodOn(MissaoController.class)
          .listarTodas())
          .withRel("Lista de Missões")
    );

    return ResponseEntity.ok(resource);

  }
  
  @Operation(description = "Editar uma missão")
  @PutMapping(value = "/{id}", version = "v1")
  public ResponseEntity<MissaoResponseDTO> editar(
      @PathVariable Long id,
      @RequestBody @Valid MissaoRequestDTO missaoRequestDTO
  ) {

    var missaoEditada = missaoService.editar(id, missaoRequestDTO);

    return ResponseEntity.status(HttpStatus.OK).body(missaoEditada);

  }

  @Operation(description = "Deletar uma missão")
  @DeleteMapping(value = "/{id}", version = "v1")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {

    missaoService.deletar(id);

    return ResponseEntity.noContent().build();

  }

}
