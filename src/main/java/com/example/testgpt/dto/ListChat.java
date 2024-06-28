package com.example.testgpt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ListChat {

    private String role;

    private List<Content> content;

    @Data
    @RequiredArgsConstructor
    public static class Content{
        private Text text;

        @Data
        @RequiredArgsConstructor
        public static class Text{
            private String value;
        }
    }
}
