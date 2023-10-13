package com.hypinx.coding.capitalone.casestudy;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PartOne {
}

/**
 * First part of code that is shared.
 * Question: Explain what is happening
 */

class UserCoupons {
    private int user;
    private final List<Coupon> coupons;

    public UserCoupons(int user, List<Coupon> coupons) {
        this.user = user;
        this.coupons = coupons;
    }

    /**
     * The sortAndSelectNCoupons method takes in a list of valid coupons, the associated coupon value, a ordering (descending or ascending) and an integer n which determines how many coupons to select. We start by creating a result list. Then we check what the value of order is and sort the coupons based on coupon value either in descending or ascending order. Then we iterate over every valid coupon. We run a for loop n number of times, if the coupon matches the value to any element in the couponValues list, we will add that to the result.
     *
     * So this method will ultimately return a list of the coupons that matches with couponValues, it will be limited to size n and the ordering will be either ascending or descending based on couponValue.
     * STOP HERE ----
     *
     * Answer ONLY when asked the third party: What enhancements would you make?
     * Answer: I would make a change to the sortAndSelectNCoupons method. Right now we are taking 2 lists: validCoupons and couponValues.
     * Each coupon already has a value associated it. We are sorting the couponValues and then looping over all the validCoupons. For each validCoupon we are then looping over
     * the couponValues and if it matches we add the result in. This is incorrect logic because in this scenario the couponValues are derived directly from validCoupons, if the first
     * validCoupon has the lowest discount, since we are looping over all couponValues we will eventually find a match even if its at the bottom and add the coupon in. So this is generating
     * incorrect results.
     *
     * So basically I would just take all the valid coupons and apply a comparator function to sort the valid coupons based on the couponValues they have. This minimizes the data
     * being passed in and unnecessary looping. We should also bind the value of n, because if max_coupons is 100 and our validCoupons list is only 10 values, we will get an
     * out of bounds exception
     */
    public List<Coupon> sortAndSelectNCoupons(List<Coupon> validCoupons, List<Double> couponValues, String order, int n) {
        List<Coupon> result = new ArrayList();
        if (order.equals("desc")) {
            Collections.sort(couponValues, Collections.reverseOrder());
        } else {
            Collections.sort(couponValues);
        }

        for (Coupon coupon : validCoupons) {
            for (int i = 0; i < n; i++) {
                if (coupon.getCouponValue() == couponValues.get(i)) {
                    result.add(coupon);
                }
            }
        }

        return result;
    }

    /** Updated method ONLY SAY FOR Question 3 - What enhancements would you make? */

    public List<Coupon> sortAndSelectNCoupons(List<Coupon> validCoupons, String order, int n) {
        List<Coupon> result = new ArrayList();
        // Used for ascending order
        CouponComparator ascendingOrder = new CouponComparator();
        // Used for descending order
        Comparator<Coupon> descendingOrder = new CouponComparator().reversed();
        if (order.equals("desc")) {
            Collections.sort(validCoupons, descendingOrder);
        } else {
            Collections.sort(validCoupons, ascendingOrder);
        }

        // Now we only need one for loop till n iterations to get all the coupons we need
        n = Math.min(n, validCoupons.size());
        for (int i = 0; i < n; i++) {
            result.add(validCoupons.get(i));
        }

        return result;
    }

    class CouponComparator implements Comparator<Coupon> {

        @Override
        public int compare(Coupon o1, Coupon o2) {
            return Double.compare(o1.getCouponValue(), o2.getCouponValue());
        }

        @Override
        public Comparator<Coupon> reversed() {
            return Comparator.super.reversed();
        }
    }

    /**
     * The getCoupons method sets a max_coupons variable to 1, we have a result list, validCoupons, and couponValues. We iterate over every coupon and for
     * each coupon we check if the userId is equal to 1, the merchantName is equal to "Merchant One". If this condition is true we check if the getClaimedAt
     * value for the coupon is an empty string or null. If so we are considering that as a valid coupon and we add it to validCoupon list and the value
     * of that coupon to the couponValues list.
     *
     * Finally we call the sortAndSelectNCoupons by passing validCoupons, couponValues, "desc", and max_coupons. So the result of this would give us back
     * a list of size 1 at most, with the coupon with the highest value. This is because our order is descending so highest on top. And we passed in
     * max_coupons as 1 so we will only get back 1 coupon at most.
     *
     *
     */
    public List<Coupon> getCoupons() {
        int max_coupons = 1;
        List<Coupon> result = new ArrayList();
        List<Coupon> validCoupons = new ArrayList();
        List<Double> couponValues = new ArrayList();

        /**
         * After reading the for loop say the following...
         * After this for loop runs, based on our data from the table we should have 4 items in the validCoupons and couponValues list
         */
        for (Coupon coupon : coupons) {
            if (coupon.getUserId() == 1 && coupon.getMerchantName().equals("Merchant One")) {
                if (coupon.getClaimedAt().equals("") || coupon.getClaimedAt() == null) {
                    validCoupons.add(coupon);
                    couponValues.add(coupon.getCouponValue());
                }
            }
        }

        /**
         * The sortAndSelectNCoupons method will return a maximum of 1 value even though we had multiple coupons
         */
        result = sortAndSelectNCoupons(validCoupons, couponValues, "desc", max_coupons);
        return result;
    }

    // Begin Question 3 ------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * ONLY SAY THE FOLLOWING FOR Question 3 - What enhancements would you make to the code?
     *
     * In the getCoupons method we have a lot of hardcoded values such as max_coupons to 1, the merchant being named MerchantOne, and userId check is always for user 1.
     * We can make this more dynamic and introduce variables that are passed in as arguments instead of hardcoding. Also in our if statements we are checking only if a coupon
     * has been claimed or not. We need to add a check to see if the coupon is still valid. In the event that coupon matches with userId, merchant name, and it has not been claimed
     * if the coupon expired one year ago it would still be picked up as a valid coupon without this check.
     *
     * We also need to check the stackable cases. If a coupon is stackable and all the valid checks pass we can add it in. Otherwise if a coupon is not stackable and we already have
     * a valid coupon added, we cannot add a non-stackable coupon with the stackable coupons. I would also refactor all the checks into a separate method since our definition of
     * what to consider as a valid coupon may change or be used in other locations.
     *
     * Also for some reason the max_coupons is snake_casing which is not consistent with the other camel case values so for aesthetic purposes change this to be consistent
     * with the rest of the code
     *
     * Changes below
     */

    public List<Coupon> getCoupons(int userId, String merchantName, int maxCoupons) {
        List<Coupon> result = new ArrayList();
        List<Coupon> validCoupons = new ArrayList();
        List<Double> couponValues = new ArrayList();

        // Delete the previous for loop and rewrite to this. Say the following
        /**
         * I'm going to delete the for loop and write a utility method to help determine if a coupon is valid since there's multiple checks.
         * If it is we can keep this logic clean and add the data to the proper lists.
         */
        for (Coupon coupon : coupons) {
            if (isValidCoupon(userId, merchantName, coupon, validCoupons)) {
                validCoupons.add(coupon);
                couponValues.add(coupon.getCouponValue());
            }
        }

        result = sortAndSelectNCoupons(validCoupons, couponValues, "desc", maxCoupons);
        return result;

    }

    public boolean isValidCoupon(int userId, String merchantName, Coupon coupon, List<Coupon> validCoupons) {
        // Change hardcoded check to dynamic check on line below
        if (coupon.getUserId() == userId && coupon.getMerchantName().equals(merchantName)) {
            if (coupon.getClaimedAt().equals("") || coupon.getClaimedAt() == null
                // Add check to see if coupon is still valid, we can convert the LocalDateTime to epoch seconds and use a zone offset to ensure we are not having
                // time zoning conflicts when working with timing. Only if the current time is behind the coupon valid until then we consider it a valid coupon
                && LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) < coupon.getCouponValidUntil().toEpochSecond(ZoneOffset.UTC)
                && coupon.isStackable() || (!coupon.isStackable() && validCoupons.size() == 0)) {
                    return true;
            }
        }

        return false;
    }

    // End of Question 3 ------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * The claimCoupon method simply updates the claimedAt value on the coupon with the current timing when the function is called
     */
    public void claimCoupon(Coupon coupon) {
        coupon.setClaimedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}


