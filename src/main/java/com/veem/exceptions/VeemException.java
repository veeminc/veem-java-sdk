package com.veem.exceptions;

import static lombok.AccessLevel.PACKAGE;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

import com.veem.model.VeemErrorResponse;

/**
 * Exception type for inter and intra service use.
 * This exception type has extra fields for
 * improved debugging:
 * <ul>
 *     <li>errorCode: Unique code that describes the exact issue</li>
 *     <li>message: Translation of the error code</li>
 *     <li>logTag: The request's log tag</li>
 *     <li>timestamp: The timestamp when the exception was thrown</li>
 *     <li>fileName: The file where the exception occurred</li>
 *     <li>lineNumber: The line in file where the exception occurred</li>
 * </ul>
 */
@Getter
@RequiredArgsConstructor(access = PACKAGE)
public abstract class VeemException extends Exception
{
    private final int errorCode;
    private final String message;
    private final Date timestamp;
    private final String logTag;
    private final String fileName;
    private final Integer lineNumber;

    /**
     * Convert a {@link VeemErrorResponse} back to a {@link VeemException}.
     * Intended for constructing exceptions from HTTP error responses.
     *
     * @param errorResponse A {@link VeemErrorResponse} to use to populate the exception fields
     */
    VeemException(VeemErrorResponse errorResponse)
    {
        this.errorCode = errorResponse.getCode();
        this.message = errorResponse.getMessage();
        this.timestamp = errorResponse.getTimestamp();
        this.logTag = errorResponse.getLogTag();
        this.fileName = errorResponse.getFileName();
        this.lineNumber = errorResponse.getLineNumber();
    }
}
