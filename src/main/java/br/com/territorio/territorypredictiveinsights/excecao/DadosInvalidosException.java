package br.com.territorio.territorypredictiveinsights.excecao;

public class DadosInvalidosException extends RuntimeException {
    public DadosInvalidosException(String mensagem) {
        super(mensagem);
    }
}