package com.veem.exceptions;


import com.veem.model.VeemErrorResponse;

public class VeemForbiddenException extends VeemException
{
    public VeemForbiddenException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
