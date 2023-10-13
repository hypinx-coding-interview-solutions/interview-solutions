package com.hypinx.coding.capitalone.casestudy;

import java.time.LocalDateTime;

/**
 * Dummy class - this class was not provided. It was made to remove the compliation errors
 * DO NOT USE FOR REFERENCE
 */
class Coupon {
    public void setClaimedAt(String localDateTime) {
    }

    public String getClaimedAt() {
        return "";
    }

    public int getUserId() {
        return 1;
    }

    public Double getCouponValue() {
        return 1.0;
    }

    public String getMerchantName() {
        return "";
    }

    public LocalDateTime getCouponValidUntil() { return LocalDateTime.now(); }

    public boolean isStackable() { return true; }

    public String getCouponType() { return ""; }

    public double getValue() { return 1.0; }
}
