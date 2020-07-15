package net.dzyga.android.netronicapp.net;

import java.io.IOException;

public class NoInternetException extends IOException {
    public NoInternetException() {
    }

    public NoInternetException(String detailMessage) {
        super(detailMessage);
    }

    public NoInternetException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NoInternetException(Throwable throwable) {
        super(throwable);
    }
}
