import { environment } from '../../../environments/environment';

export function getProductImage(product: { imageUrl?: string | null } | null | undefined): string {
  const rawImageUrl = product?.imageUrl?.trim();

  if (!rawImageUrl) {
    return 'https://via.placeholder.com/800x600?text=No+Image';
  }

  if (/^https?:\/\//i.test(rawImageUrl)) {
    return rawImageUrl;
  }

  const normalized = rawImageUrl.startsWith('/') ? rawImageUrl : `/${rawImageUrl}`;

  return `${environment.imageBaseUrl}${normalized}`;
}
