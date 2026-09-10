export interface CartItem {

  id: number;

  productId: number;

  productName: string;

  imageUrl: string;

  price: number;

  quantity: number;

  size: string;

  color: string;

  subtotal: number;
}

export interface Cart {

  items: CartItem[];

  totalAmount: number;
}