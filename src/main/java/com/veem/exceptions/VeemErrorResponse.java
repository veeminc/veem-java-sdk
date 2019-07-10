package com.veem.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Standard error response with
 *
 * <p>Is used for both JSON and XML responses.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "message",
    "logTag",
    "timestamp",
    "fileName",
    "lineNumber"
})
public class VeemErrorResponse
{
    private String message;
    private int code;
    private String logTag;
    private Date timestamp;
    private String fileName;
    private Integer lineNumber;
}
