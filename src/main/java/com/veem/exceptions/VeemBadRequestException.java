package com.veem.exceptions;

public class VeemBadRequestException extends VeemException
{
    public VeemBadRequestException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
