package br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.controller.MissaoController;
import br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.dto.NinjaRequestDTO;
import br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.dto.NinjaResponseDTO;
import br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.service.NinjaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/ninjas")
public class NinjaController {
  
  private final NinjaService ninjaService;

  public NinjaController(NinjaService ninjaService) {
    this.ninjaService = ninjaService;
  }

  @Operation(description = "Criar um ninja")
  @PostMapping(version = "v1")
  public ResponseEntity<NinjaResponseDTO> salvar(
      @RequestBody @Valid NinjaRequestDTO ninjaRequestDTO) {

    var ninjaCriado = ninjaService.criar(ninjaRequestDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(ninjaCriado);

  }

  @Operation(description = "Buscar todos os ninjas")
  @GetMapping(version = "v1")
  public ResponseEntity<List<EntityModel<NinjaResponseDTO>>> listarTodos() {

    var ninjas = ninjaService.listarTodos();

    var ninjasComLinks = ninjas.stream()
        .map(ninja -> {
          var resource = EntityModel.of(ninja);

          resource.add(
                linkTo(methodOn(NinjaController.class)
                    .buscarPorId(ninja.id()))
                    .withSelfRel()
            );

          return resource;

      })
      .toList();

    return ResponseEntity.ok(ninjasComLinks);

  }

  @Operation(description = "Buscar um ninja")
  @GetMapping(value = "/{id}", version = "v1")
  public ResponseEntity<EntityModel<NinjaResponseDTO>> buscarPorId(@PathVariable Long id) {

    var ninja = ninjaService.buscarPorId(id);

    var resource = EntityModel.of(ninja);

    resource.add(
      linkTo(methodOn(NinjaController.class)
          .listarTodos())
          .withRel("Lista de Ninjas")
    );

    return ResponseEntity.ok(resource);

  }

  @Operation(description = "Editar um ninja")
  @PutMapping(value = "/{id}", version = "v1")
  public ResponseEntity<NinjaResponseDTO> editar(
      @RequestBody @Valid NinjaRequestDTO ninjaRequestDTO,
      @PathVariable Long id
  ) {

    var ninjaEditado = ninjaService.editar(id, ninjaRequestDTO);

    return ResponseEntity.status(HttpStatus.OK).body(ninjaEditado);

  }

  @Operation(description = "Deletar um ninja")
  @DeleteMapping(value = "/{id}", version = "v1")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {

    ninjaService.deletar(id);

    return ResponseEntity.noContent().build();

  }
  
}
