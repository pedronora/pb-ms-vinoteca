package br.edu.infnet.vinhoservice.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String detail) {
    super(detail);
  }
}
