package com.example.testgpt.dto.hook;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class HookResponse {
    private String url;
}
