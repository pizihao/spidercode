package com.binder;

import com.binder.source.SourceName;

import java.lang.reflect.Type;
import java.util.List;

public class ElementUnit {

    String prefix;

    String name;

    List<SourceName> sourceNames;

    Type type;

    public ElementUnit(String prefix, String name, List<SourceName> sourceNames, Type type) {
        this.prefix = prefix;
        this.name = name;
        this.sourceNames = sourceNames;
        this.type = type;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SourceName> getSourceNames() {
        return sourceNames;
    }

    public void setSourceNames(List<SourceName> sourceNames) {
        this.sourceNames = sourceNames;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}



