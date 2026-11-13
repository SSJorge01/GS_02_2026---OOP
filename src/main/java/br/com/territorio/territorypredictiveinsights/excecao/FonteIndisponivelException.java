package br.com.territorio.territorypredictiveinsights.excecao;

public class FonteIndisponivelException extends RuntimeException {
    public FonteIndisponivelException(String mensagem) {
        super(mensagem);
    }
}