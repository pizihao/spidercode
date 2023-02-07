package com.example.mybatisderive.extend;

import org.springframework.util.Assert;

/**
 * @param <T>
 * @see org.springframework.core.NamedThreadLocal
 */
public class NameInheritableThreadLocal<T> extends InheritableThreadLocal<T> {

    private final String name;

    public NameInheritableThreadLocal(String name) {
        Assert.hasText(name, "Name must not be empty");
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
