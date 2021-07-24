package com.fank243.study.system.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.messaging.MessageChannel;

public interface PolledProcessor {

    @Input
    PollableMessageSource source();

    @Output
    MessageChannel dest();

}
