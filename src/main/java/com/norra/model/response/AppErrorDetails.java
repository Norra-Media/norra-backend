package com.norra.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppErrorDetails{

    private String appErrorId;
    private String errorMsg;
    private Object errorDetails;

}
