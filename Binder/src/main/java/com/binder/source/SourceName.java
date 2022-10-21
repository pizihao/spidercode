package com.binder.source;


import com.binder.element.Element;
import com.binder.util.Constants;

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

    public SourceName() {
    }

    public SourceName(String prefix, String elementName, Integer index, Object obj) {
        this.prefix = prefix;
        this.elementName = elementName;
        this.index = index;
        this.obj = obj;
    }

    /**
     * prefix + elementName
     */
    public String getFullName() {
        return prefix + "." + elementName;
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

    /**
     * Convert Obj to String
     */
    public String objToString() {
        return String.valueOf(obj);
    }
}
