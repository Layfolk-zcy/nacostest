package com.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class CustomRemoteApplicationEvent extends RemoteApplicationEvent {

    private CustomRemoteApplicationEvent() {
        //一定要有，序列化时会用到
    }

    public CustomRemoteApplicationEvent(String msg, String originService,
                                        String destinationService) {
        super(msg, originService, destinationService);
    }
}
