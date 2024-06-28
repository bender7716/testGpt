package com.example.testgpt.dto.listFiles;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ListFilesResponse {
    private List<Data> data;

    @Setter
    @Getter
    @RequiredArgsConstructor
    public static class Data {
        private String filename;
        private String id;
        private String object;
        private String purpose;
    }
}
