package com.veem.exceptions;

public class VeemUnauthorizedException extends VeemException
{
    public VeemUnauthorizedException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
