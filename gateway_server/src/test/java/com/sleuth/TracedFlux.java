/*
package com.sleuth;

import org.springframework.cloud.sleuth.CurrentTraceContext;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

*/
/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: TracedFlux
 * @projectName: erukeribbon
 * @description: 自定义Flux
 * @date: 2022-08-11 16:23
 **//*

public class TracedFlux<T> extends Flux<T> {
    private final Flux<T> delegate;
    private final Tracer tracer;
    private final CurrentTraceContext currentTraceContext;
    private final Span span;

    TracedFlux(Flux<T> delegate, Tracer tracer, CurrentTraceContext currentTraceContext, Span span) {
        this.delegate = delegate;
        this.tracer = tracer;
        this.currentTraceContext = currentTraceContext;
        this.span = span;
    }

    @Override
    public void subscribe(CoreSubscriber<? super T> actual) {
        delegate.subscribe(new TracedCoreSubscriber(actual, tracer, currentTraceContext, span));
    }
}

*/
