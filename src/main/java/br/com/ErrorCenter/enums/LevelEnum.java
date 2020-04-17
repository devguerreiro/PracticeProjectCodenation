package br.com.ErrorCenter.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum LevelEnum {
    @JsonProperty("info")
    INFO,

    @JsonProperty("warning")
    WARNING,

    @JsonProperty("error")
    ERROR;

}
