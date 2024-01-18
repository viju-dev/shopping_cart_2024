package discount;

import product.Product;

public class Discount {
    static String[] discountLabels = {"flat_10_discount","bulk_5_discount","bulk_10_discount","tiered_50_discount"}; // to store discount couponnames
    private double discountPrice ; // to store discount price
    private int discountFlag ; // to track most beficial discountLabel

    private int discountedProd; // to store discounted product based on which discount is selected

    public Discount( ) {
        this.discountPrice = 0;
        this.discountFlag = -1;
        this.discountedProd = -1;
    }

    public static String[] getDiscountLabels() {
        return discountLabels;
    }

    public static void setDiscountLabels(String[] discountLabels) {
        Discount.discountLabels = discountLabels;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getDiscountFlag() {
        return discountFlag;
    }

    public void setDiscountFlag(int discountFlag) {
        this.discountFlag = discountFlag;
    }
    public int getDiscountedProd() {
        return discountedProd;
    }

    public void setDiscountedProd(int discountedProd) {
        this.discountedProd = discountedProd;
    }

    //functions for different-different discounts calculations

    public void flat_10_discount(double subTotal){
        if(subTotal > 200){
            discountPrice -= 10;
            discountFlag = 0;
            discountedProd = -1;
        }
    }


    public void bulk_5_discount(Product[] prodArr, int[] prodQntyArr){
        for (int i=0;i<prodArr.length;i++){
            if (prodQntyArr[i] > 10){
                double temp = (0.05) * (prodArr[i].getPrice() * prodQntyArr[i]);
                if (temp > discountPrice){
                    discountPrice = temp;
                    discountFlag = 1;
                    discountedProd = i;
                }
            }
        }
    }

    public void bulk_10_discount(int totalQnty, double subTotal){
        if (totalQnty > 20){
            double temp = (0.1) * (subTotal);
            if (temp > discountPrice){
                discountPrice = temp;
                discountFlag = 2;
                discountedProd = -1;
            }
        }
    }

    public void tiered_50_discount(Product[] prodArr, int[] prodQntyArr, int totalQnty){
        if (totalQnty > 30){
            for (int i=0;i<prodArr.length;i++){
                if(prodQntyArr[i] > 15){
                    int discountedQnty = prodQntyArr[i] - 15;
                    double temp = discountedQnty * prodArr[i].getPrice()/2;
                    if(temp > discountPrice){
                        discountPrice = temp;
                        discountFlag = 3;
                        discountedProd = i;
                    }
                }
            }
        }

    }
}
