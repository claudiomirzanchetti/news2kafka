package com.example.news2kafka.story;

import com.example.news2kafka.exception.InvalidArgumentException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class HackerNewsService {
    private static final String JSON_SUFFIX = ".json";
    private final RestTemplate restTemplate;

    @Value("${hackerNews.url.topStories}")
    private String topStoriesUrl;

    @Value("${hackerNews.url.story}")
    private String baseStoryUrl;

    public List<StoryDto> findRandomStories(int numberToFetch) {
        List<Integer> storyIds = findTopStoryIds();
        validateNumberOfStories(numberToFetch, storyIds.size());
        return findRandomStories(numberToFetch, storyIds);
    }

    private List<StoryDto> findRandomStories(int limit, List<Integer> storyIds) {
        List<StoryDto> stories = new ArrayList<>();

        new Random()
                .ints(limit, 0, storyIds.size())
                .forEach(i -> {
                    stories.add(findStory(storyIds.get(i)));
                });

        return stories;
    }

    private List<Integer> findTopStoryIds() {
        return Arrays.asList(restTemplate.getForObject(topStoriesUrl, Integer[].class));
    }

    private StoryDto findStory(Integer id) {
        String url = StringUtils.join(baseStoryUrl, id, JSON_SUFFIX);
        return restTemplate.getForObject(url, StoryDto.class);
    }

    private void validateNumberOfStories(int numberOfStoriesToPublish, int numberOfStoriesIdsReceived) {
        if (numberOfStoriesToPublish < 1 || numberOfStoriesToPublish > numberOfStoriesIdsReceived) {
            throw new InvalidArgumentException(StringUtils.join("The number of stories to publish needs to between 1 and ", numberOfStoriesIdsReceived));
        }
    }
}
