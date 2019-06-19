package com.veem.exceptions;

import com.veem.model.VeemErrorResponse;

public class VeemInternalException extends VeemException
{
    public VeemInternalException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
