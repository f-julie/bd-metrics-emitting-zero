import com.amazonaws.services.cloudwatch.model.StandardUnit;

/**
 * Order Processor class.
 */
public class OrderProcessor {

    private PaymentProcessor paymentClient;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a OrderProcessor object.
     *
     * @param newPaymentClient The payment processor
     * @param metricsPublisher Used to publish metrics to CloudWatch
     */
    public OrderProcessor(PaymentProcessor newPaymentClient, MetricsPublisher metricsPublisher) {
        this.paymentClient = newPaymentClient;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Sets a new payment processor for handling payments.
     *
     * @param newPaymentClient The new payment processor
     */
    public void setPaymentProcessor(PaymentProcessor newPaymentClient) {
        paymentClient = newPaymentClient;
    }

    /**
     * Processes an order and payment.
     *
     * @param newOrder The order to be processed
     */
    public void processOrder(Order newOrder) {
        try {
            // Other order processing code omitted
            paymentClient.processPayment(newOrder);
            metricsPublisher.addMetric("ORDER_FAILURES", 0, StandardUnit.Count);

        } catch (Exception e) {
            metricsPublisher.addMetric("ORDER_FAILURES", 1, StandardUnit.Count);
            System.out.println("Exception thrown while processing order: " + e.getMessage());
        }
    }
}
