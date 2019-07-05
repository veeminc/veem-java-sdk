package com.veem.exceptions;


public class VeemNotFoundException extends VeemException
{
    public VeemNotFoundException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
