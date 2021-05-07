package com.example.news2kafka.story;

import com.example.news2kafka.common.KafkaProducer;
import com.example.news2kafka.common.MessageDto;
import com.example.news2kafka.util.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoryPublisher {
    private final KafkaProducer kafkaProducer;
    private final HackerNewsService hackerNewsService;
    private final JsonParser jsonParser;

    @Value("${kafka.topic.output}")
    private String hackerNewsOutput;

    public List<StoryDto> publish(Integer numberOfStoriesRequested) {
        List<StoryDto> stories = hackerNewsService.findRandomStories(numberOfStoriesRequested);
        publishStories(stories);
        return stories;
    }

    private void publishStories(List<StoryDto> stories) {
        for (StoryDto s : stories) {
            MessageDto message = MessageDto.builder()
                    .payload(jsonParser.writeToJson(s))
                    .build();

            sendMessage(message);
        }
    }

    private MessageDto sendMessage(MessageDto messageDto) {
        return kafkaProducer.sendMessage(messageDto, hackerNewsOutput, UUID.randomUUID().toString());
    }
}
