package com.veem.client;

/**
 *
 */
public final class VeemClient
{
    private final AttachmentClient attachmentClient;
    private final ContactClient contactClient;
    private final ExchangeRateClient exchangeRateClient;
    private final InvoiceClient invoiceClient;
    private final MetadataClient metadataClient;
    private final PaymentClient paymentClient;
    private final CustomerClient customerClient;

    final VeemContext context;
    final String authorization;

    VeemClient(final VeemContext context, final String accessToken)
    {
        this.context = context;
        this.authorization = String.format("Bearer %s", accessToken);

        this.attachmentClient = new AttachmentClient(this);
        this.contactClient = new ContactClient(this);
        this.exchangeRateClient = new ExchangeRateClient(this);
        this.invoiceClient = new InvoiceClient(this);
        this.metadataClient = new MetadataClient(this);
        this.paymentClient = new PaymentClient(this);
        this.customerClient = new CustomerClient(this);
    }

    /**
     *
     * @return
     */
    public AttachmentClient attachments()
    {
        return attachmentClient;
    }

    /**
     *
     * @return
     */
    public ContactClient contacts()
    {
        return contactClient;
    }

    /**
     *
     * @return
     */
    public ExchangeRateClient exchangeRates()
    {
        return exchangeRateClient;
    }

    /**
     *
     * @return
     */
    public InvoiceClient invoices()
    {
        return invoiceClient;
    }

    /**
     *
     * @return
     */
    public MetadataClient metadata()
    {
        return metadataClient;
    }

    /**
     *
     * @return
     */
    public PaymentClient payments()
    {
        return paymentClient;
    }

    public CustomerClient customers()
    {
        return customerClient;
    }
}

