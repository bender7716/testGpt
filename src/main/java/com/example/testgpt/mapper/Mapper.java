package com.example.testgpt.mapper;

import com.example.testgpt.dto.content.Content;
import com.example.testgpt.dto.content.Root;
import com.example.testgpt.dto.content.Text;
import com.example.testgpt.dto.threads.getMessages.response.GetMessagesThreadResponse;
import com.example.testgpt.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Mapper {

    public List<Message> mapListRootToListMessage(List<Root> root){
        List<Message> messages = new ArrayList<>();
        //int i = 0; i<root.size(); i++
        for (Root value : root) {
            messages.add(new Message(value.role, value.content.get(0).text.value));
        }
        return messages;
    }

    public Root mapGetMessagesThreadResponseDataToRoot(GetMessagesThreadResponse.Data data) {
        if ( data == null ) {
            return null;
        }

        Root root = new Root();

        root.setId( data.getId() );
        root.setThread_id( data.getThread_id() );
        root.setRole( data.getRole() );
        root.setContent( contentListToContentList( data.getContent() ) );

        return root;
    }

    public List<Root> mapListGetMessagesThreadResponseDataToListRoot(List<GetMessagesThreadResponse.Data> data) {
        if ( data == null ) {
            return null;
        }

        List<Root> list = new ArrayList<Root>( data.size() );
        for ( GetMessagesThreadResponse.Data data1 : data ) {
            list.add( mapGetMessagesThreadResponseDataToRoot( data1 ) );
        }

        return list;
    }

    protected Text textToText(GetMessagesThreadResponse.Data.Content.Text text) {
        if ( text == null ) {
            return null;
        }

        Text text1 = new Text();

        text1.setValue( text.getValue() );

        return text1;
    }

    protected Content contentToContent(GetMessagesThreadResponse.Data.Content content) {
        if ( content == null ) {
            return null;
        }

        Content content1 = new Content();

        content1.setType( content.getType() );
        content1.setText( textToText( content.getText() ) );

        return content1;
    }

    protected List<Content> contentListToContentList(List<GetMessagesThreadResponse.Data.Content> list) {
        if ( list == null ) {
            return null;
        }

        List<Content> list1 = new ArrayList<Content>( list.size() );
        for ( GetMessagesThreadResponse.Data.Content content : list ) {
            list1.add( contentToContent( content ) );
        }

        return list1;
    }

}
