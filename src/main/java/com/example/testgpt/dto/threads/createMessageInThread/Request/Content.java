package com.example.testgpt.dto.threads.createMessageInThread.Request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Content {
    public String type;
    public Text text;
}
