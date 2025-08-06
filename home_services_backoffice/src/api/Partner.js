import axios from "axios";
import {
  GET_VERIFIED_PARTNERS,
  GET_ALL_PARTNERS,
  DELETE_PARTNER,
  VERIFY_PARTNER,
  GET_UNVERIFIED_PARTNERS,
} from "./Config";

export const getVerifiedPartners = async () => {
  try {
    const response = await axios.get(GET_VERIFIED_PARTNERS);
    console.log("Verified partners fetched successfully:", response);
    return response.data;
  } catch (error) {
    console.error("Error fetching verified partners:", error.response?.data || error.message);
    throw error;
  }
};

export const getUnverifiedPartners = async () => {
  try {
    const response = await axios.get(GET_UNVERIFIED_PARTNERS);
    console.log("Unverified partners fetched successfully:", response);
    return response.data;
  } catch (error) {
    console.error("Error fetching unverified partners:", error.response?.data || error.message);
    throw error;
  }
};

export const verifyPartner = async (partnerId) => {
  try {
    const response = await axios.put(VERIFY_PARTNER(partnerId));
    console.log("Partner verified successfully:", response);
    return response.data;
  } catch (error) {
    console.error("Error verifying partner:", error.response?.data || error.message);
    throw error;
  }
};
