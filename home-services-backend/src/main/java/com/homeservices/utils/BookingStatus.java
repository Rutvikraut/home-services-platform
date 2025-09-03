package com.homeservices.utils;

public enum BookingStatus {
	PENDING,       // Waiting for partner assignment
    CONFIRMED,     // Accepted by partner
    INPROGRESS,   // Partner started job
    COMPLETED,     // Job done
    PAID,
    CANCELLED      // Cancelled by user/admin
}
