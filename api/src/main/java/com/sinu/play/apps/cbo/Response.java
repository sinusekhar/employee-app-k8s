package com.sinu.play.apps.cbo;

import java.io.Serializable;

public class Response implements Serializable {
    public int code;
    public String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
