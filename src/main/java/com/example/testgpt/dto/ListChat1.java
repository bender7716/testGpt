package com.example.testgpt.dto;

import com.example.testgpt.dto.content.Root;
import com.example.testgpt.message.Message;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ListChat1 {

    private String model;
    private List<Message> messages;


    public ListChat1(String model, List<Message> prompt) {
        this.model = model;

        this.messages = new ArrayList<>();


//        for (int i = 0; i<prompt.size(); i++){
//            this.messages.add(new Message(prompt.get(i).getRole(), prompt.get(i).getContent()));
//        }
        for (int i = prompt.size()-1; i>=0; i--){
            this.messages.add(new Message(prompt.get(i).getRole(), prompt.get(i).getContent()));
        }
    }
}
