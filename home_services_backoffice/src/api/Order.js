import axios from "axios";
import { GET_ALL_ORDERS, ASSIGN_ORDER_TO_PARTNER } from "./Config";

export const getAllOrders = async () => {
  try {
    const response = await axios.get(GET_ALL_ORDERS);
    return response.data;
  } catch (error) {
    console.error("Failed to fetch orders:", error);
    throw error;
  }
};

export const assignOrderToPartner = async (orderId, partnerId) => {
  const url = ASSIGN_ORDER_TO_PARTNER(partnerId, orderId);
  console.log("Assigning order with URL:", url);
  try {
    const response = await axios.put(url);
    return response.data;
  } catch (error) {
    console.error("Failed to assign order:", error);
    throw error;
  }
};
