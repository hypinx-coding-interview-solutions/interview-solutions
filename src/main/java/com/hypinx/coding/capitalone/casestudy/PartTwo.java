//package com.hypinx.coding.capitalone.casestudy;
//
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//import static com.hypinx.coding.capitalone.casestudy.PartOne.*;
//
//
//import static com.hypinx.coding.capitalone.casestudy.PartTwo.*;
//
//
//public class PartTwo {
//    public static final String DISCOUNT = "";
//    public static final String FREE_SHIP = "1";
//    public static final String FLAT_DISCOUNT = "2";
//}
//
//record Pair<T, U>(T key, U value) {
//}
//
///** Ignore the following, it's to prevent compilation issues
// *
// */
//
//
///**
// * After analyzing the code you can say the following for the solution:
// *
// * So if we introduce a new coupon type called FLAT_DISCOUNT to take off any specific dollar amount, we will need to add in a new case
// * for our switch statement. Currently we have DISCOUNT which takes a percentage off the subTotal and FREE_SHIP which sets the shipping
// * cost to 0.0. So we will have to introduce a new case and a new method to calculate a flat discount, we can call it calculateFlatDiscount
// *
// * The calculateFlatDiscount method we will have an edge case, that is if the flat discount is greater than subTotal. So we will need to add a
// * check for that and set the maximum discount based on the subTotal.
// *
// * Now in order to calculate the best coupons order we have 2 scenarios. We need to account for the best discount with stackable and non stackable
// * coupons. Non stackable coupons we can only use one so we can keep track of the maximum discount possible using a non stackable. For the other
// * scenario, we have to keep track of flat discount and percentage. We should first prioritize percentage discounts that are stackable and then
// * flat discount on top to get the best value.
// *
// * So I will start by adding the switch case and helper method
// */
///* Original Code
//public class StackCoupons {
//
//    public static double calculate(Coupon coupon, double subTotal, double shipping) {
//        double calculatedAmount = 0;
//
//        switch (Coupon.getType()) {
//            case DISCOUNT:
//                calculatedAmount = calculateDiscount(coupon, subTotal, shipping);
//                break;
//            case FREE_SHIP:
//                calculatedAmount = calculateFreeShip(coupon, subTotal, shipping);
//                break;
//        }
//
//        return calculatedAmount;
//    }
//
//    // Pair returns two values from a method.
//    private static Pair<Double, Double> calculateDiscount(Coupon coupon, double subTotal, double shipping) {
//        double newTotal = (1 - coupon.getCouponValue()) * subTotal;
//        return new Pair<>(newTotal, shipping);
//    }
//
//    private static Pair<Double, Double> calculateFreeShip(Coupon coupon, double subTotal, double shipping) {
//        double shipping = 0.0;
//        return new Pair<>(subTotal, shipping);
//    }
//
//    private static Pair<Double, List<Coupon>> getBestCouponsOrder(double totalBeforeTax, double cartShipping, List<Coupon> coupons) {
//        double bestValue = 0.0;
//
//        // TODO: calculate ordering of coupons that gives the best value in terms of customer savings
//
//        return bestValue;
//    }
//
//    public static Pair<Double, Double> stackCoupons(List<Coupon> coupons, int maxCoupons) {
//        Cart userCart = new Cart();
//        List<Coupon> bestCoupons = UserCoupons(coupons).getCoupons(maxCoupons);
//        BestOrder bestOrder = getBestCouponsOrder(userCart.getTotalBeforeTax(), userCart.getCartShipping(), bestCoupons, maxCoupons);
//
//        double finalTotal = bestOrder.getValue() + userCart.getCartTaxes();
//        double savings = userCart.getCartTotal() - finalTotal;
//
//        return new Pair<>(finalTotal, savings);
//    }
//}
//*/
//
//
//public class StackCoupons {
//
//    public static double calculate(Coupon coupon, double subTotal, double shipping) {
//        double calculatedAmount = 0;
//
//        switch (coupon.getCouponType()) {
//            case DISCOUNT:
//                calculatedAmount = calculateDiscount(coupon, subTotal, shipping);
//                break;
//            case FREE_SHIP:
//                calculatedAmount = calculateFreeShip(coupon, subTotal, shipping);
//                break;
//                // Add following 3 lines
//            case FLAT_DISCOUNT:
//                calculatedAmount = calculateFlatDiscount(coupon, subTotal, shipping);
//                break;
//        }
//
//        return calculatedAmount;
//    }
//
//    // Pair returns two values from a method.
//    private static Pair<Double, Double> calculateDiscount(Coupon coupon, double subTotal, double shipping) {
//        double newTotal = (1 - coupon.getCouponValue()) * subTotal;
//        return new Pair<>(newTotal, shipping);
//    }
//
//    private static Pair<Double, Double> calculateFreeShip(Coupon coupon, double subTotal, double shipping) {
//        double shipping = 0.0;
//        return new Pair<>(subTotal, shipping);
//    }
//
//    // Add calculateFlatDiscount method below
//
//    /**
//     * The calculateFlatDiscount will take in a coupon, the subTotal, and current shipping as arguments.
//     */
//    private static Pair<Double, Double> calculateFlatDiscount(Coupon coupon, double subTotal, double shipping) {
//        // Ensure we don't go below 0 if the flat discount is higher than the subTotal. The maximum discount is either 0
//        // or the difference between subTotal minus coupon value
//        double newTotal = Math.max(0, subTotal - coupon.getValue());
//        // Then we can return the newTotal and shipping
//        return new Pair<>(newTotal, shipping);
//    }
//
//    /**
//     * The getBestCouponsOrder will take 3 arguements, subTotal, shipping, and list of coupons.
//     *
//     */
//    private static Pair<Double, List<Coupon>> getBestCouponsOrder(double subTotal, double shipping, List<Coupon> coupons) {
//        // We will use 2 variables to keep track of best stackable and non stackable discounts
//        double bestValueNonStackable = 0.0, bestValueStackable = 0.0;
//        // Next we should sort our coupon list by the percentage - so I will define a comparator function
//        // STOP HERE AND SCROLL DOWN TO WRITE THE COMPARATOR FUNCTON THEN COME BACK HERE
//        Collections.sort(coupons, new CouponTypeComaprator());
//
//        // TODO: calculate ordering of coupons that gives the best value interms of customer savings
//
//        /**
//         * Now that our coupons are giving priority to DISCOUNT type before FLAT we can use a for loop to go over every coupon.
//         * For each coupon we will get the couponType and see if it's stackable or not. We will calculate the discount. If it's stackable
//         * we will update the value for bestValueStackable, otherwise if it's not stackable we will update bestValueNonStackable by seeing
//         * if the current bestValueNonStackable is greater or the newly calculated value.
//         */
//        for (int i = 0; i < coupons.size(); i++) {
//            Coupon currentCoupon = coupons.get(i);
//            String couponType = currentCoupon.getCouponType();
//            boolean stackable = currentCoupon.isStackable();
//
//            double calculatedDiscount = calculate(currentCoupon, subTotal, shipping);
//            if (stackable) {
//                bestValueStackable += calculatedDiscount;
//            } else {
//                bestValueNonStackable = Math.max(bestValueNonStackable, calculatedDiscount);
//            }
//        }
//
//
//        // Return the greater of the two values
//        return Math.max(bestValueNonStackable, bestValueStackable);
//    }
//
//    /**
//     * The CouponTypeComparator will sort the coupon by PERCENTAGE types first then FLAT_DISCOUNT/SHIPPING after
//     * @param <Coupon>
//     */
//
//    static class CouponTypeComaprator implements Comparator<Coupon> {
//
//        @Override
//        public int compare(Coupon o1, Coupon o2) {
//            // Return 1 to indicate to return first coupon
//            if (o1.getCouponType().equals(DISCOUNT)) return 1;
//            // We can return -1 here because if the first coupon is not DISCOUNT then we will return the second. If it is a discount then
//            // it's fine, otherwise even if it's not a DISCOUNT type we don't care about the order
//            return -1;
//        }
//    }
//
//    /**
//     * NO CHANGES NEED TO BE MADE TO STACK COUPONS
//     */
//    public static Pair<Double, Double> stackCoupons(List<Coupon> coupons, int maxCoupons) {
//        Cart userCart = new Cart();
//        List<Coupon> bestCoupons = UserCoupons(coupons).getCoupons(maxCoupons);
//        BestOrder bestOrder = getBestCouponsOrder(userCart.getTotalBeforeTax(), userCart.getCartShipping(), bestCoupons, maxCoupons);
//
//        double finalTotal = bestOrder.getValue() + userCart.getCartTaxes();
//        double savings = userCart.getCartTotal() - finalTotal;
//
//        return new Pair<>(finalTotal, savings);
//    }
//}