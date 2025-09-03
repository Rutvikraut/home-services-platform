import axios from "axios";
import {
  GET_ALL_PARTNERS,
  GET_VERIFIED_PARTNERS,
  GET_UNVERIFIED_PARTNERS,
  REGISTER_PARTNER,
  GET_PARTNER_BY_ID,
  UPDATE_PARTNER,
  DELETE_PARTNER,
  GET_PARTNER_ORDERS,
  GET_PARTNER_EARNINGS,
  GET_PARTNER_SERVICES,
  VERIFY_PARTNER,
  ASSIGN_ORDER_TO_PARTNER,
  UPDATE_ORDER_STATUS_COMPLETED,
  UPDATE_ORDER_STATUS_INPROGRESS
} from "./config";

// Register a new partner
export const registerPartner = async (partnerData) => {
  try {
    const response = await axios.post(REGISTER_PARTNER, partnerData);
    return response.data;
  } catch (error) {
    console.error("Partner registration failed:", error.response?.data || error.message);
    throw error;
  }
};

// Get all partners
export const getAllPartners = async () => {
  try {
    const response = await axios.get(GET_ALL_PARTNERS);
    return response.data;
  } catch (error) {
    console.error("Failed to fetch all partners:", error.response?.data || error.message);
    throw error;
  }
};

// Get all verified partners
export const getVerifiedPartners = async () => {
  try {
    const response = await axios.get(GET_VERIFIED_PARTNERS);
    return response.data;
  } catch (error) {
    console.error("Failed to fetch verified partners:", error.response?.data || error.message);
    throw error;
  }
};

// Get all unverified partners
export const getUnverifiedPartners = async () => {
  try {
    const response = await axios.get(GET_UNVERIFIED_PARTNERS);
    return response.data;
  } catch (error) {
    console.error("Failed to fetch unverified partners:", error.response?.data || error.message);
    throw error;
  }
};

// Get partner by ID
export const getPartnerById = async (partnerId,token) => {
  try {
    console.log("called")
    const response = await axios.get(GET_PARTNER_BY_ID(partnerId),{
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  } catch (error) {
    console.error("Failed to fetch partner:", error.response?.data || error.message);
    throw error;
  }
};

// Update partner profile
export const updatePartner = async (partnerId, updateData) => {
  try {
    const response = await axios.put(UPDATE_PARTNER(partnerId), updateData);
    return response.data;
  } catch (error) {
    console.error("Failed to update partner:", error.response?.data || error.message);
    throw error;
  }
};

// Delete (soft-delete) partner
export const deletePartner = async (partnerId) => {
  try {
    const response = await axios.delete(DELETE_PARTNER(partnerId));
    return response.data;
  } catch (error) {
    console.error("Failed to delete partner:", error.response?.data || error.message);
    throw error;
  }
};

// Get partner's assigned orders
export const getPartnerOrders = async (partnerId,token) => {
  try {
    const response = await axios.get(GET_PARTNER_ORDERS(partnerId),{
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  } catch (error) {
    console.error("Failed to fetch partner orders:", error.response?.data || error.message);
    throw error;
  }
};

// Get partner's total earnings
export const getPartnerEarnings = async (partnerId) => {
  try {
    const response = await axios.get(GET_PARTNER_EARNINGS(partnerId));
    return response.data;
  } catch (error) {
    console.error("Failed to fetch partner earnings:", error.response?.data || error.message);
    throw error;
  }
};

// Get partner's offered services
export const getPartnerServices = async (partnerId) => {
  try {
    const response = await axios.get(GET_PARTNER_SERVICES(partnerId));
    return response.data;
  } catch (error) {
    console.error("Failed to fetch partner services:", error.response?.data || error.message);
    throw error;
  }
};

// Mark partner as verified
export const verifyPartner = async (partnerId) => {
  try {
    const response = await axios.put(VERIFY_PARTNER(partnerId));
    return response.data;
  } catch (error) {
    console.error("Failed to verify partner:", error.response?.data || error.message);
    throw error;
  }
};

// Assign order to partner
export const assignOrderToPartner = async (partnerId, orderId) => {
  try {
    const response = await axios.put(ASSIGN_ORDER_TO_PARTNER(partnerId, orderId));
    return response.data;
  } catch (error) {
    console.error("Failed to assign order:", error.response?.data || error.message);
    throw error;
  }
};


export const updateOrderStatusCompleted = async (partnerId, orderId) => {
  try {
    const response = await axios.put(UPDATE_ORDER_STATUS_COMPLETED(partnerId, orderId));
    return response.data;
  } catch (error) {
    console.error("Failed to change order status:", error.response?.data || error.message);
    throw error;
  }
};

export const updateOrderStatusInProgress = async (partnerId, orderId) => {
  try {
    const response = await axios.put(UPDATE_ORDER_STATUS_INPROGRESS(partnerId, orderId));
    return response.data;
  } catch (error) {
    console.error("Failed to change order status:", error.response?.data || error.message);
    throw error;
  }
};
