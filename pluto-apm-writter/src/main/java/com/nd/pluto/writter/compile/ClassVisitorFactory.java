package com.nd.pluto.writter.compile;

import com.llmofang.objectweb.asm.ClassAdapter;
import com.llmofang.objectweb.asm.ClassVisitor;

abstract class ClassVisitorFactory {
    private final boolean retransformOkay;

    public ClassVisitorFactory(boolean retransformOkay) {
        this.retransformOkay = retransformOkay;
    }

    public boolean isRetransformOkay() {
        return this.retransformOkay;
    }

    abstract ClassAdapter create(ClassVisitor paramClassVisitor);
}
