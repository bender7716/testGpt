package com.example.testgpt.controller;

import com.example.testgpt.dto.content.Root;
import com.example.testgpt.dto.getFile.FileResponse;
import com.example.testgpt.dto.listFiles.ListFilesResponse;
import com.example.testgpt.dto.threads.getMessages.response.GetMessagesThreadResponse;
import com.example.testgpt.service.GptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
public class GptController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Qualifier("openaiRestTemplateLoadCreateThread")
    @Autowired
    private RestTemplate restTemplateThread;

    @Qualifier("openaiRestTemplateLoad")
    @Autowired
    private RestTemplate restTemplateLoad;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.url.load}")
    private String apiUrlLoad;

    @Value("${openai.api.url.file}")
    private String apiUrlFile;

    @Value("${openai.api.url.create.thread}")
    private String apiUrlCreateThread;

    private final GptService gptService;

    public GptController(GptService gptService) {
        this.gptService = gptService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody List<Root> prompt) {
        return ResponseEntity.ok(gptService.chat(prompt));
    }

    @PostMapping("/createThread")
    public ResponseEntity<String> createThread(){
        return ResponseEntity.ok(gptService.createThread());
    }
    
    @PostMapping("/createMessageInThread")
    public ResponseEntity<String> createMessage(@RequestParam String thread_id, @RequestBody List<Root> prompt){
        return ResponseEntity.ok(gptService.createMessage(thread_id, prompt));
    }

    @GetMapping("/getMessagesThread")
    public ResponseEntity<List<GetMessagesThreadResponse.Data>> getMessagesThread(@RequestParam String thread_id){
        return ResponseEntity.ok(gptService.getMessagesThread(thread_id));
    }

    @DeleteMapping("deleteThread")
    public ResponseEntity<String> deleteThread(@RequestParam String thread_id){
        return ResponseEntity.ok(gptService.deleteThread(thread_id));
    }

    @GetMapping("/file")
    public ResponseEntity<FileResponse> file(@RequestParam String file_id){
        return ResponseEntity.ok(gptService.file(file_id));
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile() throws IOException {
        return ResponseEntity.ok(gptService.uploadFile());


//        FileSystemResource fileSystemResource = new FileSystemResource("D://cat.jpg");
//        if (fileSystemResource.exists()){
//            System.out.println("okay");
//        }
//        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
//        body.add("file", fileSystemResource);
////        byte[] bytes = Files.readAllBytes(Path.of("D://cat.jpg"));
////        File file1 = ResourceUtils.getFile("src/main/resources/cat.jpg");
//
//
//
//        String purpose = "vision";
//        LoadFileRequest loadFileRequest = new LoadFileRequest(body, purpose);
//        LoadFileResponse loadFileResponse = restTemplateLoad.postForObject(apiUrlLoad, loadFileRequest, LoadFileResponse.class);
//
//        assert loadFileResponse != null;
//        return loadFileResponse.getId();
    }

    @GetMapping("/listFiles")
    public ResponseEntity<List<ListFilesResponse.Data>> listFiles(){
        return ResponseEntity.ok(gptService.listFiles());
    }

    @DeleteMapping("/deleteFile")
    public ResponseEntity<Boolean> deleteFile(@RequestParam String file_id){
        return ResponseEntity.ok(gptService.deleteFile(file_id));
    }

    @PostMapping("/createRun")
    public ResponseEntity<String> createRun(String thread_id, String assistant_id){
        return ResponseEntity.ok(gptService.createRun(thread_id, assistant_id));
    }



}
