package com.nd.pluto.writter;

import java.lang.instrument.Instrumentation;

public class PlutoAgent {
    public static void agentmain(String args, Instrumentation instrumentation){
        premain(args, instrumentation);
    }

    public static void premain(String args, Instrumentation instrumentation){
        instrumentation.addTransformer(new MainTransformer());
    }
}
