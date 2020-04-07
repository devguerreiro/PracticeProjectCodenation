package br.com.ErrorCenter.enums;

public enum LevelEnum {
    INFO("info"), WARNING("warning"), ERROR("error");

    private String name;

    LevelEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
