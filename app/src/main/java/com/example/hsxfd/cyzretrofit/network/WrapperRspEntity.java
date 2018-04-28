package com.example.hsxfd.cyzretrofit.network;

/**
 * resultapi model包装器
 * @param <T>
 */
public class WrapperRspEntity<T> {
    private String status;
    private int error;
    private String message;
    private T results;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "WrapperRspEntity{" +
                "status='" + status + '\'' +
                ", error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
