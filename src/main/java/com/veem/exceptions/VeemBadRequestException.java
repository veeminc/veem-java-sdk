package com.veem.exceptions;

import com.veem.model.VeemErrorResponse;

public class VeemBadRequestException extends VeemException
{
    public VeemBadRequestException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
