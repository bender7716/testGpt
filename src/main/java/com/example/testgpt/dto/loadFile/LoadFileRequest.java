package com.example.testgpt.dto.loadFile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.MultiValueMap;

import java.io.File;

@Data
@Setter
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LoadFileRequest {
    public MultiValueMap<String, Object> body;
    public String purpose;

    public LoadFileRequest(MultiValueMap<String, Object> body, String purpose) {
        this.body = body;
        this.purpose = purpose;
    }
}
