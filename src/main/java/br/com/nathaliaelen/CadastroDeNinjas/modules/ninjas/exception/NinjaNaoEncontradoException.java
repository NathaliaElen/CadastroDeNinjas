package br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.exception;

public class NinjaNaoEncontradoException extends RuntimeException {
  
  public NinjaNaoEncontradoException() {
    super("Ninja não encontrado.");
  }

}
