package com.nd.pluto.writter.compile;


import org.objectweb.asm.MethodVisitor;

interface MethodVisitorFactory {
    MethodVisitor create(MethodVisitor paramMethodVisitor, int paramInt, String paramString1, String paramString2);
}
