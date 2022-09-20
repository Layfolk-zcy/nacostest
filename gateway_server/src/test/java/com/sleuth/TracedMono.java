/*
package com.sleuth;

import org.springframework.cloud.sleuth.CurrentTraceContext;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

*/
/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: TracedMono
 * @projectName: erukeribbon
 * @description: 自定义mono
 * @date: 2022-08-11 16:25
 **//*

public class TracedMono<T> extends Mono<T> {
    private final Mono<T> delegate;
    private final Tracer tracer;
    private final CurrentTraceContext currentTraceContext;
    private final Span span;

    TracedMono(Mono<T> delegate, Tracer tracer, CurrentTraceContext currentTraceContext, Span span) {
        this.delegate = delegate;
        this.tracer = tracer;
        this.currentTraceContext = currentTraceContext;
        this.span = span;
    }

    @Override
    public void subscribe(CoreSubscriber<? super T> actual) {
        delegate.subscribe(new TracedCoreSubscriber(actual, tracer, currentTraceContext, span));
    }
}*/
