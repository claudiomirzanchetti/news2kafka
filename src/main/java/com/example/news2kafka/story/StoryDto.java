package com.example.news2kafka.story;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryDto {
    private Long id;

    @JsonProperty("by")
    private String author;

    private int score;

    private String title;

    private String url;
}
