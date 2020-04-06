package br.com.ErrorCenter.enums;

public enum LevelEnum {
    INFO("info"), WARNING("warning"), ERROR("error");

    private String level;

    LevelEnum(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

}
