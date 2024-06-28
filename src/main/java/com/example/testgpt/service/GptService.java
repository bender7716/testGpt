package com.example.testgpt.service;

import com.example.testgpt.dto.ListChat1;
import com.example.testgpt.dto.chat.ChatRequest;
import com.example.testgpt.dto.chat.ChatResponse;
import com.example.testgpt.dto.content.Root;
import com.example.testgpt.dto.createRun.CreateRunRequest;
import com.example.testgpt.dto.createRun.CreateRunResponse;
import com.example.testgpt.dto.deleteFile.DeleteFileResponse;
import com.example.testgpt.dto.getFile.FileRequest;
import com.example.testgpt.dto.getFile.FileResponse;
import com.example.testgpt.dto.listFiles.ListFilesRequest;
import com.example.testgpt.dto.listFiles.ListFilesResponse;
import com.example.testgpt.dto.threads.createMessageInThread.Request.CreateMessageInThreadRequest;
import com.example.testgpt.dto.threads.createMessageInThread.CreateMessageInThreadResponse;
import com.example.testgpt.dto.threads.createThread.CreateThreadRequest;
import com.example.testgpt.dto.threads.createThread.CreateThreadResponse;
import com.example.testgpt.dto.threads.getMessages.GetMessagesThreadRequest;
import com.example.testgpt.dto.threads.getMessages.response.GetMessagesThreadResponse;
import com.example.testgpt.mapper.Mapper;
import com.example.testgpt.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class GptService {
    private static final String API_URL = "https://api.openai.com/v1/files";

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Qualifier("openaiRestTemplateLoadCreateThread")
    @Autowired
    private RestTemplate restTemplateThread;

    @Qualifier("openaiRestTemplateLoad")
    @Autowired
    private RestTemplate restTemplateLoad;

    @Value("${openai.api.url.create.thread}")
    private String apiUrlCreateThread;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.url.load}")
    private String apiUrlLoad;

    @Value("${openai.api.url.file}")
    private String apiUrlFile;

    @Autowired
    private Mapper mapper;


    public String createMessage(String thread_id, List<Root> prompt) {
        String role = prompt.get(0).role;
        String content = prompt.get(0).content.get(0).text.value;

        CreateMessageInThreadRequest createMessageInThreadRequest = new CreateMessageInThreadRequest(role, content);
        //Добавление сообщения в thread
        CreateMessageInThreadResponse createMessageInThreadResponse = restTemplateThread
                .postForObject(apiUrlCreateThread + "/" + thread_id + "/messages", createMessageInThreadRequest, CreateMessageInThreadResponse.class);

        assert createMessageInThreadResponse != null;

        //Получение сообщений из thread
        List<GetMessagesThreadResponse.Data> messagesThread = getMessagesThread(thread_id);
        List<Root> roots = mapper.mapListGetMessagesThreadResponseDataToListRoot(messagesThread);

        //Отправка списка вопросов в ChatGPT
        String answer = chat(roots);

        //Добавление ответа в thread
        CreateMessageInThreadRequest createMessageInThreadRequest1 = new CreateMessageInThreadRequest("assistant", answer);
        CreateMessageInThreadResponse createMessageInThreadResponse1 = restTemplateThread
                .postForObject(apiUrlCreateThread + "/" + thread_id + "/messages", createMessageInThreadRequest1, CreateMessageInThreadResponse.class);

        assert createMessageInThreadResponse1 != null;

        return answer;
    }

    public String chat(List<Root> prompt) {
        List<Message> messages = mapper.mapListRootToListMessage(prompt);
        ListChat1 request = new ListChat1(model, messages);
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        return response.getChoices().get(0).getMessage().getContent();
    }

    public List<GetMessagesThreadResponse.Data> getMessagesThread(String thread_id){
        GetMessagesThreadRequest getMessagesThreadRequest = new GetMessagesThreadRequest(thread_id);
        GetMessagesThreadResponse getMessagesThreadResponse = restTemplateThread
                .getForObject(apiUrlCreateThread + "/" + thread_id + "/messages", GetMessagesThreadResponse.class, getMessagesThreadRequest);

        assert getMessagesThreadResponse != null;
        return getMessagesThreadResponse.getData();
    }

    public String createThread(){
        CreateThreadRequest createThreadRequest = new CreateThreadRequest();
        CreateThreadResponse createThreadResponse = restTemplateThread.postForObject(apiUrlCreateThread, createThreadRequest, CreateThreadResponse.class);

        assert createThreadResponse != null;

        return createThreadResponse.getId();
    }

    public String deleteThread(String thread_id){
        restTemplateThread.delete(apiUrlCreateThread + "/" + thread_id);
        return null;
    }

    public String uploadFile(){
        return null;
    }

    public List<ListFilesResponse.Data> listFiles(){
        ListFilesRequest listFilesRequest = new ListFilesRequest();
        ListFilesResponse listFilesResponse = restTemplate.getForObject(apiUrlLoad, ListFilesResponse.class, listFilesRequest);
        assert listFilesResponse != null;
        return listFilesResponse.getData();
    }

    public FileResponse file(String file_id){
        FileRequest fileRequest = new FileRequest(file_id);

        return restTemplate.getForObject(apiUrlFile + file_id, FileResponse.class, fileRequest);
    }

    public Boolean deleteFile(String file_id){

//        DeleteFileResponse deleteFileResponse = restTemplate.delete(apiUrlLoad + "/" + file_id);
        restTemplate.delete(apiUrlLoad + "/" + file_id);
//        assert deleteFileResponse != null;
//        return  deleteFileResponse.getDeleted();
        return null;
    }

    public String createRun(String thread_id, String assistant_id) {
        CreateRunRequest createRunRequest = new CreateRunRequest(assistant_id);
        CreateRunResponse createRunResponse = restTemplateThread.postForObject(apiUrlCreateThread + "/" + thread_id + "/runs", createRunRequest, CreateRunResponse.class);

        assert createRunResponse != null;
        return createRunResponse.getId();
    }
}
