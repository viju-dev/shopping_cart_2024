import additionalfees.AdditionalFees;
import discount.Discount;
import product.Product;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

//      took 3 products as per given information
        Scanner sc = new Scanner(System.in);
        Product p1 = new Product("Product A", 20);
        Product p2 = new Product("Product B", 40);
        Product p3 = new Product("Product C", 50);

//      created prodQntyArr of size as that of total products to store quantity of each respectively
        Product[] prodArr = {p1,p2,p3};
        int[] prodQntyArr = new int[3];
        for (int i=0;i<prodQntyArr.length;i++){
            try {
                System.out.println("Enter quantity for "+prodArr[i].getName() +" : ");
                prodQntyArr[i]=sc.nextInt();
            }
            catch (RuntimeException e){
                throw new RuntimeException("Enter valid input...");
            }
        }

        //calculating subtotal price and total qnatity
        double subTotal = 0; // initial total price
        int totalQnty = 0; // initial total quantity
        for (int i=0;i<prodArr.length;i++){
            subTotal += prodArr[i].getPrice() * prodQntyArr[i];
            totalQnty += prodQntyArr[i];
        }

        //checking and choosing most beneficial discount
        Discount discount = new Discount();
        discount.flat_10_discount(subTotal);
        discount.bulk_5_discount(prodArr,prodQntyArr);
        discount.bulk_10_discount(totalQnty,subTotal);
        discount.tiered_50_discount(prodArr,prodQntyArr,totalQnty);


//        calculating additional charges like Gift wrapping and Shipping
        AdditionalFees additionalFees = new AdditionalFees(1,10,5);
        for (int i = 0;i<prodArr.length;i++){
            System.out.println("Do you want to wrap "+prodArr[i].getName()+" as gift ? type: yes or no");
            try {
                String str = sc.next();
                additionalFees.calc_totalGiftWrapFee(prodQntyArr[i]);
            }
            catch (RuntimeException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        additionalFees.calc_ShippingPackages(totalQnty);
        additionalFees.calc_totalShippingFee();

//        calculating final total price
        double totalPrice = subTotal - discount.getDiscountPrice() + additionalFees.getTotalGiftWrapFee() + additionalFees.getTotalShippingFee();

//      printing result
        System.out.println("Products : \n-----------------------------------------------------------");
        for(int i = 0;i<prodArr.length;i++){
            System.out.println(i+1+" | name : "+prodArr[i].getName() + "| price : $" +prodArr[i].getPrice() +"| quantity : "+prodQntyArr[i]);
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("SubTotal : $"+subTotal);
        System.out.println("Discount Coupon : '"+Discount.getDiscountLabels()[discount.getDiscountFlag()]+"'\nDiscount Price : $"+discount.getDiscountPrice());
        System.out.println("Gift wrap fees : $"+additionalFees.getTotalGiftWrapFee()+"\nShipping fees : $"+additionalFees.getTotalShippingFee());
        System.out.println("Total : $"+totalPrice);
    }
}