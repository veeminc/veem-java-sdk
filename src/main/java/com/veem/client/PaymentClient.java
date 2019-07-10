package com.veem.client;

import static com.veem.client.ResponseHandler.handleResponse;
import static com.veem.client.converters.PaymentConverter.convert;
import static com.veem.constants.BatchItemType.PAYMENT;
import static java.util.Optional.ofNullable;
import static java.util.UUID.randomUUID;

import com.neovisionaries.i18n.CountryCode;
import lombok.val;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.veem.client.converters.BatchConverter;
import com.veem.client.converters.PageConverter;
import com.veem.client.converters.PaymentConverter;
import com.veem.client.requests.PaymentRequest;
import com.veem.constants.BatchItemType;
import com.veem.constants.Direction;
import com.veem.exceptions.VeemException;
import com.veem.model.*;

public final class PaymentClient
{
    private final VeemClient veemClient;
    private final PaymentApi paymentApi;
    private final MetadataClient metadataClient;

    PaymentClient(VeemClient veemClient)
    {
        this.veemClient = veemClient;
        this.paymentApi = veemClient.context.paymentApi;
        this.metadataClient = veemClient.metadata();
    }

    /**
     * Gets all {@link Payment}s that satisfy the search criteria.
     *
     * @return The {@link Payment} with the requested ID, if it exists.
     */
    public Page<Payment> list(final PaymentListParameters parameters) throws VeemException
    {
        return PageConverter.convert(
                handleResponse(paymentApi.list(
                        ofNullable(parameters.getDirection()).map(Direction::getStringValue).orElse(null),
                        parameters.getPaymentIds(),
                        parameters.getBatchId(),
                        parameters.getStatus(),
                        ofNullable(parameters.getSortParameters()).orElseGet(Collections::emptyMap).keySet().stream()
                                .map(field -> String.format("%s:%s",
                                        field.getStringValue(),
                                        parameters.getSortParameters().get(field).getStringValue()))
                                .collect(Collectors.toList()),
                        parameters.getPageNumber(),
                        parameters.getPageSize(),
                        veemClient.authorization)::execute),
                PaymentConverter::convert);
    }

    /**
     *
     *
     * @param payment
     * @return The {@link Payment} with the requested ID, if it exists.
     */
    public Payment draft(final Payment payment) throws VeemException
    {
        final PaymentRequest paymentRequest = convert(payment);
        paymentRequest.setApproveAutomatically(false);
        return convert(handleResponse(
                paymentApi.create(paymentRequest, randomUUID().toString(), veemClient.authorization)::execute));
    }

    /**
     *
     *
     * @param payment
     * @return The {@link Payment} with the requested ID, if it exists.
     */
    public Payment send(final Payment payment) throws VeemException
    {
        final PaymentRequest paymentRequest = convert(payment);
        paymentRequest.setApproveAutomatically(true);
        return convert(handleResponse(
                paymentApi.create(paymentRequest, randomUUID().toString(), veemClient.authorization)::execute));
    }

    /**
     * Gets a specific {@link Payment} from Veem, given an payment ID.
     *
     * @param paymentId The ID of the {@link Payment} to fetch
     * @return The {@link Payment} with the requested ID, if it exists.
     */
    public Payment get(final long paymentId) throws VeemException
    {
        return convert(handleResponse(paymentApi.get(paymentId, veemClient.authorization)::execute));
    }

    /**
     * Gets a specific {@link Payment} from Veem, given an payment ID.
     *
     * @param paymentId The ID of the {@link Payment} to fetch
     * @return The {@link Payment} with the requested ID, if it exists.
     */
    public Payment send(final long paymentId) throws VeemException
    {
        return convert(handleResponse(
                paymentApi.send(paymentId, randomUUID().toString(), veemClient.authorization)::execute));
    }

    /**
     * Gets a specific {@link Payment} from Veem, given an payment ID.
     *
     * @param paymentId The ID of the {@link Payment} to fetch
     * @return The {@link Payment} with the requested ID, if it exists.
     */
    public Payment cancel(final long paymentId) throws VeemException
    {
        return convert(handleResponse(
                paymentApi.cancel(paymentId, randomUUID().toString(), veemClient.authorization)::execute));
    }

    /**
     * Gets valid {@link Payment#purposeOfPayment} values for a given country.
     *
     * @param countryCode The country to get values for
     * @return Purpose of payment values for the given countryCode
     */
    public Collection<PurposeOfPayment> purposeOfPaymentValues(final CountryCode countryCode) throws VeemException
    {
        return ofNullable(metadataClient.getCountryCurrencyMap())
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(e -> e.getCountry() == countryCode)
                .map(CountryInfo::getPurposeOfPaymentInfo)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public Batch createBatch(final List<Payment> payments, final boolean send) throws VeemException
    {
        val paymentRequests = payments.stream()
                .map(PaymentConverter::convert)
                .collect(Collectors.toList());
        paymentRequests.forEach(request -> request.setApproveAutomatically(send));
        return BatchConverter.convert(PAYMENT, handleResponse(
                paymentApi.createBatch(paymentRequests, randomUUID().toString(), veemClient.authorization)::execute));
    }

    public Batch getBatch(final long batchId) throws VeemException
    {
        return BatchConverter.convert(PAYMENT,
                handleResponse(paymentApi.getBatch(batchId, veemClient.authorization)::execute));
    }
}
