package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckMfaRequest {
    private String sid;
    private String code;

    @JsonCreator
    public CheckMfaRequest(@JsonProperty("name") final String sid, @JsonProperty("code") final String code) {
        this.sid = sid;
        this.code = code;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(final String sid) {
        this.sid = sid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }
}