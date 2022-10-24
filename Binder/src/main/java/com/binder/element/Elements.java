package com.binder.element;

import com.binder.ElementUnit;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class Elements {

    // order
    List<Element> elements = new LinkedList<>();

    public Elements() {
        elements.add(new SimpleElement());
        elements.add(new EnumElement());
        elements.add(new ArrayElement());
        elements.add(new CollectionElement());
        elements.add(new MapElement());
        elements.add(new ObjectElement());
    }

    public Elements(List<Element> elements) {
        this();
        this.elements.addAll(elements);
    }

    public boolean add(Element element) {
        return elements.add(element);
    }

    public <T> T getResult(ElementUnit elementUnit) {
        Type type = elementUnit.getType();
        for (Element element : elements) {
            if (element.isSupport(type)) {
                return element.parser(elementUnit, this);
            }
        }
        return null;
    }

    public boolean isSupport(Element element) {
        for (Element e : elements) {
            Class<? extends Element> cls = e.getClass();
            Class<? extends Element> elementCls = element.getClass();
            if (cls.equals(elementCls)) {
                return false;
            }
        }
        return true;
    }

}
