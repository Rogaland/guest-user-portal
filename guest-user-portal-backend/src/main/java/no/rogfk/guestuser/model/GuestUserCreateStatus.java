package no.rogfk.guestuser.model;

import lombok.Data;

@Data
public class GuestUserCreateStatus {

    int timeout = 0;
    private String guestMessage;
    private String hostMessage;
    private String errorMessage;
    private NotifyStatus hostNotifyStatus;
    private NotifyStatus guestNotifyStatus;
}
