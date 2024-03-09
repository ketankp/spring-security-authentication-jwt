package com.example.mpl.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponseDto {

    private int statusCode;
    private String status;
    private String message;
    private String description;

}
