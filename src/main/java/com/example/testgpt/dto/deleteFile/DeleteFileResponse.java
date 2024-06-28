package com.example.testgpt.dto.deleteFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteFileResponse {
    private String id;
    private String object;
    private Boolean deleted;
}
