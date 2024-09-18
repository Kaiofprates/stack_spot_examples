package br.com.pocs.stackspot;

public class OrderProcessor {

    public enum OrderStatus {
        PENDING, APPROVED, REJECTED, SHIPPED, DELIVERED, CANCELLED
    }

    public record Order(OrderStatus status, boolean paymentReceived, boolean inventoryAvailable, boolean addressVerified) {
        public Order {
            status = status == null ? OrderStatus.PENDING : status;
        }

        public Order withStatus(OrderStatus newStatus) {
            return new Order(newStatus, paymentReceived, inventoryAvailable, addressVerified);
        }
    }

    public void processOrder(Order order) {
        order = checkOrderConditions(order);

        if (order.status() == OrderStatus.APPROVED) {
            order = shipOrder(order);
        }
    }

    private Order checkOrderConditions(Order order) {
        return switch (order) {
            case Order o when !o.paymentReceived() -> {
                System.out.println("Order rejected due to payment issue.");
                yield o.withStatus(OrderStatus.REJECTED);
            }
            case Order o when !o.inventoryAvailable() -> {
                System.out.println("Order rejected due to inventory issue.");
                yield o.withStatus(OrderStatus.REJECTED);
            }
            case Order o when !o.addressVerified() -> {
                System.out.println("Order rejected due to address verification issue.");
                yield o.withStatus(OrderStatus.REJECTED);
            }
            default -> {
                System.out.println("Order approved.");
                yield order.withStatus(OrderStatus.APPROVED);
            }
        };
    }

    private Order shipOrder(Order order) {
        System.out.println("Order shipped.");
        return deliverOrder(order.withStatus(OrderStatus.SHIPPED));
    }

    private Order deliverOrder(Order order) {
        System.out.println("Order delivered.");
        return order.withStatus(OrderStatus.DELIVERED);
    }

    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        Order order = new Order(OrderStatus.PENDING, true, true, true);
        processor.processOrder(order);
    }
}
