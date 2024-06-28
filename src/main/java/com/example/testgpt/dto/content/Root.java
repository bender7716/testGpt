package com.example.testgpt.dto.content;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class Root{
    public String id;
    public String thread_id;
    public String role;
    public List<Content> content;
}
