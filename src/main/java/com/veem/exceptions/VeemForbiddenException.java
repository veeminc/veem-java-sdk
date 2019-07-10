package com.veem.exceptions;

public class VeemForbiddenException extends VeemException
{
    public VeemForbiddenException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
