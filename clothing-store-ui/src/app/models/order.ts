export interface OrderItem {

  id: number;

  productId: number;

  productName: string;

  price: number;

  quantity: number;

  subtotal: number;
}

export interface Order {

  orderId: number;

  totalAmount: number;

  shippingAddress: string;

  phoneNumber: string;

  orderStatus: string;

  paymentStatus: string;

  orderDate: string;

  items: OrderItem[];
}