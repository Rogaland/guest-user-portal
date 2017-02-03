package no.rogfk.guestuser.model;

import lombok.Data;

@Data
public class GuestUserCreateStatus {

    private String message;
    private NotifyStatus hostNotifyStatus;
    private NotifyStatus guestNotifyStatus;
    int timeout = 0;
}
