export interface CheckoutRequest {

  fullName: string;

  phoneNumber: string;

  addressLine1: string;

  addressLine2?: string;

  city: string;

  state: string;

  pincode: string;

  paymentMethod: string;
}