package br.com.nathaliaelen.CadastroDeNinjas.modules.missoes.exception;

public class MissaoExistenteException extends RuntimeException {

  public MissaoExistenteException() {
    super("Missão já existe!");
  }
  
}
