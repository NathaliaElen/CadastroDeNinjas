package br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.model.MissaoModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_ninja")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private Long id; // primary key
  
  @Column(nullable = false, length = 50)
  private String nome;

  @Column(nullable = false)
  private int idade;
  
  @Column(unique = true, nullable = false, length = 50)
  private String email;

  // @ManyToOne - Vários ninjas podem estar associados a uma mesma missão.
  @ManyToOne
  @JoinColumn(name = "missao_id", nullable = false) // foreign key
  private MissaoModel missao;

  @CreationTimestamp
  @Column(updatable = false, nullable = false, name = "criado_em")
  private LocalDateTime criadoEm;
  
}
