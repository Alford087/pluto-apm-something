package com.nd.pluto.writter.compile.visitor;

import com.google.common.collect.Sets;
import com.llmofang.objectweb.asm.AnnotationVisitor;
import com.llmofang.objectweb.asm.ClassAdapter;
import com.llmofang.objectweb.asm.ClassVisitor;
import com.llmofang.objectweb.asm.MethodVisitor;
import com.llmofang.objectweb.asm.commons.GeneratorAdapter;
import com.newrelic.agent.compile.InstrumentationContext;
import com.newrelic.agent.compile.Log;

import java.util.Set;

public class ReplaceCallSiteClassVisitor extends ClassAdapter {
    private final InstrumentationContext context;
    private final Log log;
    private final Set<String> recursiveCallCheckThreadLocals = Sets.newHashSet();

    public ReplaceCallSiteClassVisitor(ClassVisitor cv, InstrumentationContext context, Log log) {
        super(cv);
        this.context = context;
        this.log = log;
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String sig, String[] exceptions) {
        return new MethodWrapMethodVisitor(super.visitMethod(access, name, desc, sig, exceptions), access, name, desc);
    }

    private final class MethodWrapMethodVisitor extends GeneratorAdapter {
        private final String name;
        private final String desc;
        private boolean isReplaceClassSite;

        public MethodWrapMethodVisitor(MethodVisitor mv, int access, String name, String desc) {
            super(mv, access, name, desc);

            ReplaceCallSiteClassVisitor.this.log.debug("DUDE " + name + desc);
            this.name = name;
            this.desc = desc;
        }

        public AnnotationVisitor visitAnnotation(String name, boolean arg1) {
            if ("Lcom/newrelic/agent/android/instrumentation/ReplaceCallSite;".equals(name)) {
                this.isReplaceClassSite = true;
            }
            return super.visitAnnotation(name, arg1);
        }

        public void visitCode() {
            super.visitCode();
        }
    }
}
