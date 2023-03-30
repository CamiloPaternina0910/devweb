package com.devweb.taskmanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GeneralResponse {

    private boolean error;
    private int status;
    private Object data;

}