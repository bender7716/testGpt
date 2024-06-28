package com.example.testgpt.dto.threads.getMessages.response;

import com.example.testgpt.message.Message;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@RequiredArgsConstructor
public class GetMessagesThreadResponse {
    private List<Data> data;

    @Setter
    @Getter
    public static class Data {
        private String id;
        private String thread_id;
        private String role;
        private List<Content> content;

        public Data(String role, List<Content> content) {
            this.role = role;
            this.content = content;
        }

        @Setter
        @Getter
        @RequiredArgsConstructor
        public static class Content{
            private String type;
            private Text text;

            @Setter
            @Getter
            @RequiredArgsConstructor
            public static class Text{
                private String value;
            }
        }
    }


}
