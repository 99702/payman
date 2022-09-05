package com.payman.event;

import com.payman.dto.request.MobileDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class ResetPasswordSendEvent extends ApplicationEvent {
    private final String mobile;
    private final String applicationUrl;
    public ResetPasswordSendEvent(MobileDto mobile, String applicationUrl) {
        super(mobile);
        this.mobile = mobile.getMobile();
        this.applicationUrl = applicationUrl;
    }
}