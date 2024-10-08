package com.frontend.frontend.util;

import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.handler.HandlerResolver;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.PortInfo;
import java.util.List;

public class MyHandlerResolver implements HandlerResolver {



    @Override
    public List<Handler> getHandlerChain(PortInfo portInfo) {
        List<Handler> handlers = List.of(new Handler() {
            @Override
            public boolean handleMessage(MessageContext messageContext) {
                return false;
            }

            @Override
            public boolean handleFault(MessageContext messageContext) {
                return false;
            }

            @Override
            public void close(MessageContext messageContext) {

            }
        });
        return handlers;
    }
}
