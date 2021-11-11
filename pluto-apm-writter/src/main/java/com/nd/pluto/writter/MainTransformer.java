package com.nd.pluto.writter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MainTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        if (className != null && className.contains("Main")) {
            System.out.println("className : " + className);
        }
        if (className != null && className.contains("ProcessBuilder")) {
            System.out.println("className : " + className);
        }
        return classfileBuffer;
    }

}