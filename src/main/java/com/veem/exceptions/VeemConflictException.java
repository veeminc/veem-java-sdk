package com.veem.exceptions;

public class VeemConflictException extends VeemException
{
    public VeemConflictException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
