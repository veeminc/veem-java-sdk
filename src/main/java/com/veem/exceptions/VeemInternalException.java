package com.veem.exceptions;

public class VeemInternalException extends VeemException
{
    public VeemInternalException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
