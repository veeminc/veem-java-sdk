package com.veem.client;

import static java.util.Optional.ofNullable;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import retrofit2.Response;

import java.io.IOException;
import java.util.function.Function;

import com.veem.exceptions.*;
import com.veem.model.VeemErrorResponse;

/**
 * Retrofit2 response handler for internal Veem requests.
 */
@Slf4j
class ResponseHandler
{
    interface Request<T>
    {
        T call() throws IOException;
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Handle success and failure responses from internal Veem request.
     *
     * @param request A method reference to the API request execution.
     * @param <T> The type of the response body
     * @return The response body POJO
     */
    static <T> T handleResponse(final Request<Response<T>> request) throws VeemException
    {
        val response = handleException(request);
        if (response.isSuccessful())
        {
            return response.body();
        }
        else
        {
            throw convertErrorResponse(response);
        }
    }

    private static <T> T handleException(final Request<T> request) throws VeemSdkException
    {
        try
        {
            return request.call();
        }
        catch (IOException e)
        {
            throw new VeemSdkException(e, e.getMessage());
        }
    }

    /**
     * Convert the {@link Response} of a failed Retrofit2 request to an instance of {@link VeemException}.
     * If {@link Response#errorBody()} is a {@link VeemErrorResponse}, it will be converted back to its original
     * {@link VeemException}.
     *
     * @param response The {@link Response} from the failed request
     * @return An instance of a {@link VeemException} subtype.
     */
    private static VeemException convertErrorResponse(final Response response)
    {
        String errorBody = null;
        try
        {
            errorBody = response.errorBody() == null ? "{}" : response.errorBody().string();
            return getExceptionContructor(response.code()).apply(MAPPER.readValue(errorBody, VeemErrorResponse.class));
        }
        catch (IOException e)
        {
            log.error(String.format("Failed to deserialize error response for %d error", response.code()), e);
            return new VeemSdkException(e, extractError(response, errorBody));
        }
    }

    private static Function<VeemErrorResponse, VeemException> getExceptionContructor(final int statusCode)
    {
        switch (statusCode)
        {
            case 400:
                return VeemBadRequestException::new;
            case 401:
                return VeemUnauthorizedException::new;
            case 403:
                return VeemForbiddenException::new;
            case 404:
                return VeemNotFoundException::new;
            case 409:
                return VeemConflictException::new;
            case 500:
            default:
                return VeemInternalException::new;
        }
    }

    private static String extractError(final Response<?> response, final String errorBody)
    {
        String error;
        try
        {
            val errorResponse = (JSONObject) JSONValue.parse(errorBody);
            error = ofNullable(errorResponse)
                .map(er -> er.get("message"))
                .map(Object::toString)
                .orElseThrow(RuntimeException::new);
            log.error(error);
        }
        catch (Exception e)
        {
            log.error("Unable to parse error response body", e);
            error = response.message();
        }
        return error;
    }
}
