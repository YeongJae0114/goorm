package com.example.student.exceoption;

import ch.qos.logback.core.util.StringUtil;
import com.example.student.model.ErrorCode;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import org.springframework.util.StringUtils;

public class CustomException extends RuntimeException{
    @Getter
    private final ErrorCode errorCode;

    private String message;

    private Map.Entry<String, Object> data;


    public CustomException(ErrorCode errorCode, String message, Object data){
        this.errorCode=errorCode;
        this.message = message;
        this.data = new SimpleImmutableEntry<>(data.getClass().getSimpleName(),data);
    }

    public String getMessage(){
        if (StringUtils.hasLength(this.message)){
            return this.message;
        }
        return message;
    }
}
