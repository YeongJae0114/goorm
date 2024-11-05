package com.example.student.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final Status status;
    private final MetaData metaData;
    private  List<T>result;

    public ApiResponse(List<T> result) {
        this.result = result;
        this.status = new Status(ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
        this.metaData = new MetaData(result.size());
    }

    @Getter
    @AllArgsConstructor
    private static class Status{
        private int code;
        private String message;
    }

    @Getter
    @AllArgsConstructor
    private static class MetaData{
        private int resultCount = 0;
    }
}
