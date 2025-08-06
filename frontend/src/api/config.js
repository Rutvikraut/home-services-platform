const BASE_URL = "http://localhost:8080"

export const GET_ALL_CATEGORIES = `${BASE_URL}/categories`
export const ADD_CATEGORY = `${BASE_URL}`;
export const GET_CATEGORY_BY_ID = (id) => `${BASE_URL}/categories/${id}`;
export const UPDATE_CATEGORY = (id) => `${BASE_URL}/categories/${id}`;
export const DELETE_CATEGORY = (id) => `${BASE_URL}/categories/${id}`;
export const GET_SERVICES_BY_CATEGORY_ID = (id) => `${BASE_URL}/categories/${id}/services`

export const REGISTER_USER = `${BASE_URL}/user/register`;
export const LOGIN_USER = `${BASE_URL}/user/login`;
export const UPDATE_USER = (id) => `${BASE_URL}/user/${id}`;

export const GET_USER_ADDRESSES = (userId) => `${BASE_URL}/user/${userId}/addresses`;
export const DELETE_USER_ADDRESS = (userId, addressId) => `${BASE_URL}/user/${userId}/addresses/${addressId}`;
export const UPDATE_USER_ADDRESS = (userId, addressId) => `${BASE_URL}/user/${userId}/addresses/${addressId}`;
export const CREATE_USER_ADDRESS = (userId) => `${BASE_URL}/user/${userId}/addresses`;
export const UPDATE_USER_PASSWORD = (userId) => `${BASE_URL}/user/${userId}/password`;

export const GET_ALL_SERVICES = `${BASE_URL}/order/service`;
export const GET_ALL_SERVICES_BY_IDS = `${BASE_URL}/service/getByIds`;
export const GET_SERVICE_BY_ID = (id) => `${BASE_URL}/order/service/${id}`;
export const CREATE_SERVICE = `${BASE_URL}/order/service`;
export const UPDATE_SERVICE = (id) => `${BASE_URL}/order/service/${id}`;
export const DELETE_SERVICE = (id) => `${BASE_URL}/order/service/${id}`;

export const CREATE_ORDER = (userId) => `${BASE_URL}/order/user/${userId}/service`;
export const GET_ALL_ORDERS = `${BASE_URL}/order`;
export const GET_ORDER_BY_ID = (orderId) => `${BASE_URL}/order/${orderId}`;
export const GET_ORDERS_BY_USER_ID = (userId) => `${BASE_URL}/order/user/${userId}`;
export const GET_ORDERS_BY_PARTNER_ID = (partnerId) => `${BASE_URL}/order/partner/${partnerId}`;
export const GET_ORDERS_BY_STATUS = (status) => `${BASE_URL}/order/status/${status}`;
export const UPDATE_ORDER_STATUS = (orderId) => `${BASE_URL}/order/${orderId}/status`;
export const CANCEL_ORDER = (orderId) => `${BASE_URL}/order/${orderId}`;

// Partner API Routes
export const GET_ALL_PARTNERS = `${BASE_URL}/partner`;
export const GET_VERIFIED_PARTNERS = `${BASE_URL}/partner/verified`;
export const GET_UNVERIFIED_PARTNERS = `${BASE_URL}/partner/unverified`;

export const REGISTER_PARTNER = `${BASE_URL}/partner/register`; // POST
export const GET_PARTNER_BY_ID = (partnerId) => `${BASE_URL}/partner/${partnerId}`;
export const UPDATE_PARTNER = (partnerId) => `${BASE_URL}/partner/${partnerId}`;
export const DELETE_PARTNER = (partnerId) => `${BASE_URL}/partner/${partnerId}`;

export const GET_PARTNER_ORDERS = (partnerId) => `${BASE_URL}/partner/${partnerId}/orders`;
export const GET_PARTNER_EARNINGS = (partnerId) => `${BASE_URL}/partner/${partnerId}/earnings`;
export const GET_PARTNER_SERVICES = (partnerId) => `${BASE_URL}/partner/${partnerId}/services`;
export const UPDATE_ORDER_STATUS_PARTNER=(partnerId, orderId)=> `${BASE_URL}/partner/${partnerId}/orders/${orderId}/status`;

export const VERIFY_PARTNER = (partnerId) => `${BASE_URL}/partner/${partnerId}/verify`;
export const ASSIGN_ORDER_TO_PARTNER = (partnerId, orderId) =>
  `${BASE_URL}/partner/${partnerId}/orders/${orderId}`;




