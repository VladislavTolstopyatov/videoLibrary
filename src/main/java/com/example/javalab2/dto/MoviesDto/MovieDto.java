package com.example.javalab2.dto.MoviesDto;

import com.example.javalab2.dto.ActorDto;
import com.example.javalab2.dto.FeedbackDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private String genre;
    private String directorFio;
    private LocalDate dateOfRelease;
    private Integer duration;
    private List<FeedbackDto> feedbackDtoList;
    private List<ActorDto> actorDtoList;
}
