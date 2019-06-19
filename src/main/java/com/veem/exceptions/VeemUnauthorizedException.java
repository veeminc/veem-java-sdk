package com.veem.exceptions;

import com.veem.model.VeemErrorResponse;

public class VeemUnauthorizedException extends VeemException
{
    public VeemUnauthorizedException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
