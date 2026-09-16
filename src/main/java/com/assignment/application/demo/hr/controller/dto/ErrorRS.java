package com.assignment.application.demo.hr.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
public class ErrorRS {

    private Instant timestamp;
    private int status;
    private String error;
    private String message;

}
