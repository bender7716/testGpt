package com.example.testgpt.dto.getFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileRequest {
    private String file_id;

    public FileRequest(String file_id) {
        this.file_id = file_id;
    }
}
