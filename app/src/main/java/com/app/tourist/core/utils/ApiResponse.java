package com.app.tourist.core.utils;

public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean isSuccess;
    private boolean isError;
    private int statusCode;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ApiResponse() {
    }

    public ApiResponse(T data, String message, boolean isSuccess, boolean isError, int statusCode) {
        this.data = data;
        this.message = message;
        this.isSuccess = isSuccess;
        this.isError = isError;
        this.statusCode = statusCode;
    }
}
