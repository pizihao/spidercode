package com.binder.reflect.generics;

import sun.reflect.generics.factory.CoreReflectionFactory;
import sun.reflect.generics.parser.SignatureParser;
import sun.reflect.generics.scope.ClassScope;
import sun.reflect.generics.tree.ClassSignature;
import sun.reflect.generics.tree.IntSignature;
import sun.reflect.generics.tree.TypeSignature;
import sun.reflect.generics.visitor.Reifier;

public class ReifierUtil {

    public static void main(String[] args) {
        IntSignature intSignature = IntSignature.make();
        ClassScope classScope = ClassScope.make(Integer.class);

        CoreReflectionFactory reflectionFactory = CoreReflectionFactory.make(Integer.class, classScope);
        Reifier reifier = Reifier.make(reflectionFactory);
        reifier.visitIntSignature(intSignature);

        SignatureParser signatureParser = SignatureParser.make();
        TypeSignature typeSignature = signatureParser.parseTypeSig("Ljava.lang.String;");
        System.out.println(typeSignature);

    }

}
