package com.tvp.publicador.entity;

import lombok.Data;

@Data
public class Email {
    private String from;
    private String to;
    private String subject;
    private String text;
}