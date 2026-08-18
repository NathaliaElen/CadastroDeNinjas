package br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.exception;

public class MissaoNaoEncontradaException extends RuntimeException {
  
  public MissaoNaoEncontradaException() {
    super("Missão não encontrada.");
  }

}
