package br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.model.NinjaModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_missao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MissaoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private Long id; // primary key

  @Column(nullable = false, length = 50)
  private String nome;

  @Column(nullable = false, length = 50)
  private String dificuldade;

  // @OneToMany- Uma missão pode estar associada a vários ninjas.
  @OneToMany(mappedBy = "missao")
  private List<NinjaModel> ninjas;

  @CreationTimestamp
  @Column(updatable = false, nullable = false, name = "criado_em")
  private LocalDateTime criadoEm;

}
