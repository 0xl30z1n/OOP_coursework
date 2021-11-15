package br.iesb.imarket.dto.response;

public class MessageSingletron {

    private String message;
    private static final MessageSingletron messageSingletron = new MessageSingletron();

    private MessageSingletron(){

    }

    public static MessageSingletron getMessageSingletron(){
        return messageSingletron;
    }

    public MessageSingletron withMessage(String msg){
        this.message = msg;
        return this;
    }

    public MessageResponseDTO build(){
        MessageResponseDTO msg = new MessageResponseDTO(message);
        return msg;
    }
}
