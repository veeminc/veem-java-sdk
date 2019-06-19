package com.veem.exceptions;

import com.veem.model.VeemErrorResponse;

public class VeemConflictException extends VeemException
{
    public VeemConflictException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
