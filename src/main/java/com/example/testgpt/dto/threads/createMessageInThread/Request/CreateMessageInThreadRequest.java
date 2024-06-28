package com.example.testgpt.dto.threads.createMessageInThread.Request;

import com.example.testgpt.message.Message;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CreateMessageInThreadRequest {

//    private List<Message> messages;
    private String role;
    private String content;

    public CreateMessageInThreadRequest(String role, String content) {
        this.role = role;
        this.content = content;
    }

//    public CreateMessageInThreadRequest(String role, String content) {
//        this.role = role;
//        this.content = content;
//    }


//    public CreateMessageInThreadRequest(String prompt) {
//        this.messages = new ArrayList<>();
//        this.messages.add(new Message("user", prompt));
//    }
    }

