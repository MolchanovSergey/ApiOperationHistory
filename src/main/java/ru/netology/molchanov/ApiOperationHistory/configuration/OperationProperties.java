package ru.netology.molchanov.ApiOperationHistory.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "operation")

public class OperationProperties {
    public int sleepMilliSeconds;

    public int getSleepMilliSeconds() {
        return sleepMilliSeconds;
    }
    public void setSleepMilliSeconds(int sleepMilliSeconds) {
        this.sleepMilliSeconds = sleepMilliSeconds;
    }
}
