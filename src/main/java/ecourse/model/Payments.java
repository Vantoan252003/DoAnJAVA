package ecourse.model;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "payments")
public class Payments {
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "payment_id")
    private short paymentsId;

    @Column(name = "order_id")
    private short orderId;

    @Column(name = "payment_date")
    private Date paymentsDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentsMethod paymentsMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentsStatus paymentsStatus;

    @Column(name = "amount")
    private Float amount;

    public short getPaymentsId() {
        return paymentsId;
    }

    public void setPaymentsId(short paymentsId) {
        this.paymentsId = paymentsId;
    }

    public short getOrderId() {
        return orderId;
    }

    public void setOrderId(short orderId) {
        this.orderId = orderId;
    }

    public Date getPaymentsDate() {
        return paymentsDate;
    }

    public void setPaymentsDate(Date paymentsDate) {
        this.paymentsDate = paymentsDate;
    }

    public PaymentsMethod getPaymentsMethod() {
        return paymentsMethod;
    }

    public void setPaymentsMethod(PaymentsMethod paymentsMethod) {
        this.paymentsMethod = paymentsMethod;
    }

    public PaymentsStatus getPaymentsStatus() {
        return paymentsStatus;
    }

    public void setPaymentsStatus(PaymentsStatus paymentsStatus) {
        this.paymentsStatus = paymentsStatus;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setOrder'");
    }

    
}
