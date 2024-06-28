package com.example.testgpt.dto.getFile;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FileResponse {
    private String filename;
    private String id;
    private String object;
    private String purpose;
}
