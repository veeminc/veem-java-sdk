package com.veem.exceptions;

import com.veem.model.VeemErrorResponse;

public class VeemNotFoundException extends VeemException
{
    public VeemNotFoundException(VeemErrorResponse errorResponse)
    {
        super(errorResponse);
    }
}
