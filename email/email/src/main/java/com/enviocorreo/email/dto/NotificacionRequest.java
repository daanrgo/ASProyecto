package com.enviocorreo.email.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionRequest {
    private String toEmail;
    private String subject;
    private String body;
}