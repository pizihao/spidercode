package com.binder.properties;

import lombok.Getter;

/**
 * Config file type enum
 */
@Getter
public enum ConfigFileTypeEnum {

    /**
     * PROPERTIES
     */
    PROPERTIES("properties"),

    /**
     * XML
     */
    XML("xml"),

    /**
     * JSON
     */
    JSON("json"),

    /**
     * YML
     */
    YML("yml"),

    /**
     * YAML
     */
    YAML("yaml"),

    /**
     * TXT
     */
    TXT("txt");

    private final String value;

    ConfigFileTypeEnum(String value) {
        this.value = value;
    }

    public static ConfigFileTypeEnum of(String value) {
        for (ConfigFileTypeEnum typeEnum : ConfigFileTypeEnum.values()) {
            if (typeEnum.value.equals(value)) {
                return typeEnum;
            }
        }
        return PROPERTIES;
    }
}
