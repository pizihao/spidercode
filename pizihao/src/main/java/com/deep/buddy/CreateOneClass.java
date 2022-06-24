package com.deep.buddy;

import com.esotericsoftware.reflectasm.MethodAccess;
import net.bytebuddy.ByteBuddy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * <h2>创建一个类</h2>
 * 并不是生成字节码而是直接创建对象
 *
 * @author Create by liuwenhao on 2022/6/24 11:10
 */
public class CreateOneClass {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> userCls = new ByteBuddy()
            .subclass(Object.class)
            .name("User")
            .defineProperty("name", String.class)
            .defineField("age", Integer.TYPE, 1)
            .make()
            .load(CreateOneClass.class.getClassLoader())
            .getLoaded();

        Constructor<?> constructor = userCls.getConstructor();
        Object newInstance = constructor.newInstance();

        MethodAccess methodAccess = MethodAccess.get(userCls);
        methodAccess.invoke(newInstance,"setName","liu");
        Object getName = methodAccess.invoke(newInstance, "getName");
        System.out.println(getName);
    }
}