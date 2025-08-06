const BASE_URL = "https://localhost:44335/api/admin";

export const GET_VERIFIED_PARTNERS = `${BASE_URL}/orders/partner/verified`;
export const GET_UNVERIFIED_PARTNERS = `${BASE_URL}/orders/partner/unverified`;
export const GET_ALL_PARTNERS = `${BASE_URL}/orders/partner`;
export const DELETE_PARTNER = (id) => `${BASE_URL}/orders/partner/${id}`;

export const VERIFY_PARTNER = (id) => `${BASE_URL}/orders/partner/${id}/verify`;
export const GET_ALL_ORDERS = `${BASE_URL}/orders/orders`;

export const ASSIGN_ORDER_TO_PARTNER = (partnerId, orderId) =>
  `https://localhost:44335/api/admin/orders/partner/${partnerId}/orders/${orderId}`;