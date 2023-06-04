package lhind.internship.myflight.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class BaseResponse {

    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}
