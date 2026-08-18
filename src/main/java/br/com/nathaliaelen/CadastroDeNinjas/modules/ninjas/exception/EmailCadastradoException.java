package br.com.nathaliaelen.CadastroDeNinjas.modules.ninjas.exception;

public class EmailCadastradoException extends RuntimeException {
  
  public EmailCadastradoException() {
    super("E-mail já cadastrado.");
  }

}
