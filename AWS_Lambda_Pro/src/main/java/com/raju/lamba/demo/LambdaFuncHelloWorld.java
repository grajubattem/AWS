package com.raju.lamba.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFuncHelloWorld implements RequestHandler<Object, String> {

    public LambdaFuncHelloWorld() {}

    @Override
    public String handleRequest(Object input, Context context) {
            context.getLogger().log("Input: " + input);
            return "Hello World - " + input;
        }

	
    
}