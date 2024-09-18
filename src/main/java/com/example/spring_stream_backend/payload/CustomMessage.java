package com.example.spring_stream_backend.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustomMessage {
    private String message;

    private boolean success=false;
}
