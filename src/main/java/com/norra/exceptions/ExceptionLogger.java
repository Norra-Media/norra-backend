package com.norra.exceptions;

import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public final class ExceptionLogger {

    private ExceptionLogger() { }

    public static void logError(Exception e) {
        if (e != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            log.error(stackTrace);
            Sentry.capture(e);
        }
    }
}