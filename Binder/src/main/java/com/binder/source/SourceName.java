package com.binder.source;


import com.binder.element.Element;
import com.binder.mapper.SourceMapper;
import com.binder.util.Constants;

import java.util.Objects;

/**
 * name identifies a configuration item and distinguishes different configuration information<br>
 * {@link Element}
 */
public class SourceName {

    /**
     * Prefix of the configuration item as a whole
     */
    String prefix;

    /**
     * The identity of a single element
     */
    String elementName;

    /**
     * The element's ordinal number, the index number provided for arrays and collections.<br>
     * If it is -1, it is not an array or collection type
     */
    Integer index;

    /**
     * A value that can be uniquely determined
     */
    Object obj;

    SourceMapper sourceMapper;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public SourceMapper getSourceMapper() {
        return sourceMapper;
    }

    public void setSourceMapper(SourceMapper sourceMapper) {
        this.sourceMapper = sourceMapper;
    }

    public SourceName() {
    }

    public SourceName(String prefix, String elementName, Integer index, Object obj, SourceMapper sourceMapper) {
        this.prefix = prefix;
        this.elementName = elementName;
        this.index = index;
        this.obj = obj;
        this.sourceMapper = sourceMapper;
    }

    /**
     * prefix + elementName
     */
    public String getFullName() {
        return prefix + "." + sourceMapper.reverseConvert(elementName);
    }

    public String getOriginalName() {
        return sourceMapper.reverseConvert(elementName);
    }

    /**
     * prefix + elementName + [index], if index = -1, the  result like to getFullName
     */
    public String getQualifiedName() {
        if (index == -1) {
            return getFullName();
        }
        return getFullName() + Constants.LEFT_BRACKETS + index + Constants.RIGHT_BRACKETS;
    }

    public String getSimpleName() {
        if (elementName.contains(Constants.LEFT_BRACKETS) && elementName.contains(Constants.RIGHT_BRACKETS)) {
            int left = elementName.indexOf(Constants.LEFT_BRACKETS);
            return elementName.substring(0, left);
        }
        return elementName;
    }

    /**
     * Convert Obj to String
     */
    public String objToString() {
        return String.valueOf(obj);
    }

    @Override
    public String toString() {
        return "SourceName{" +
                "prefix='" + prefix + '\'' +
                ", elementName='" + elementName + '\'' +
                ", index=" + index +
                ", obj=" + obj +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceName that = (SourceName) o;
        return Objects.equals(prefix, that.prefix) && Objects.equals(elementName, that.elementName) && Objects.equals(index, that.index) && Objects.equals(obj, that.obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, elementName, index, obj);
    }
}
