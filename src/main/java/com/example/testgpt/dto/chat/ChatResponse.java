package com.example.testgpt.dto.chat;

import com.example.testgpt.message.Message;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ChatResponse {

    private List<Choice> choices;

    public ChatResponse(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {
        private int index;
        private Message message;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public Choice(int index, Message message) {
            this.index = index;
            this.message = message;
        }
    }

}
