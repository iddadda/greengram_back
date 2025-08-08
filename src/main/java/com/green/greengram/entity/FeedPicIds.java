package com.green.greengram.entity;

import ch.qos.logback.classic.spi.LoggingEventVO;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FeedPicIds implements Serializable {
    private Long feedId;
    @Column(nullable = false, length = 50)
    private String pic;


}
