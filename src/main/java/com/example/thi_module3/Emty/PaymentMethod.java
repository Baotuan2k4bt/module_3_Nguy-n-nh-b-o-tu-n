package com.example.thi_module3.Emty;

public class PaymentMethod{
   int paymentMethodID;
   String paymentMethodName;

   public PaymentMethod(int paymentMethodID, String paymentMethodName) {
      this.paymentMethodID = paymentMethodID;
      this.paymentMethodName = paymentMethodName;
   }

   public int getPaymentMethodID() {
      return paymentMethodID;
   }

   public String getPaymentMethodName() {
      return paymentMethodName;
   }

   public void setPaymentMethodID(int paymentMethodID) {
      this.paymentMethodID = paymentMethodID;
   }

   public void setPaymentMethodName(String paymentMethodName) {
      this.paymentMethodName = paymentMethodName;
   }

}
