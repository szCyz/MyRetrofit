package com.example.hsxfd.cyzretrofit.network;

/**
 * 接口异常
 */
public class APIException extends Throwable {
    public String status;
    public String message;

    public APIException(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
