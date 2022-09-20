/*
package com.sleuth;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.cloud.sleuth.CurrentTraceContext;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;

*/
/**
 * https://cloud.tencent.com/developer/article/1927332
 *
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: TracedCoreSubscriber
 * @projectName: erukeribbon
 * @description: 自定义订阅
 * @date: 2022-08-11 16:21
 **//*

public class TracedCoreSubscriber<T> implements Subscriber<T> {

    private final Subscriber<T> delegate;
    private final Tracer tracer;
    private final CurrentTraceContext currentTraceContext;
    private final Span span;

    TracedCoreSubscriber(Subscriber<T> delegate, Tracer tracer, CurrentTraceContext currentTraceContext, Span span) {
        this.delegate = delegate;
        this.tracer = tracer;
        this.currentTraceContext = currentTraceContext;
        this.span = span;
    }

    @Override
    public void onSubscribe(Subscription s) {
        executeWithinScope(() -> {
            delegate.onSubscribe(s);
        });
    }

    @Override
    public void onError(Throwable t) {
        executeWithinScope(() -> {
            delegate.onError(t);
        });
    }

    @Override
    public void onComplete() {
        executeWithinScope(delegate::onComplete);
    }

    @Override
    public void onNext(T o) {
        executeWithinScope(() -> {
            delegate.onNext(o);
        });
    }

    private void executeWithinScope(Runnable runnable) {
        //如果当前没有链路信息，强制包裹
        if (tracer.currentSpan() == null) {
            try (CurrentTraceContext.Scope scope = this.currentTraceContext.maybeScope(this.span.context())) {
                runnable.run();
            }
        } else {
            //如果当前已有链路信息，则直接执行
            runnable.run();
        }
    }
}
*/
