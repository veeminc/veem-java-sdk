package com.veem.exceptions;

import java.sql.Date;
import java.time.Instant;

public class VeemSdkException extends VeemException
{
    public VeemSdkException(final String message)
    {
        this(null, message);
    }

    public VeemSdkException(final Throwable cause, final String message)
    {
        super(0, message, Date.from(Instant.now()), null, null, null);
        this.initCause(cause);
    }
}
