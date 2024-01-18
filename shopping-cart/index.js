const prompt = require("prompt-sync")();
// Product Name : Price
// Product A : $20
// Product B : $40
// Product C : $50

let productNamesArr = ["Product A","Product B","Product C"]; //contains all product names
let prodPriceArr = [20,40,50]; // contains product pricing respective to productNamesArr
let prodQntyArr = []; // will contain product quantity respective to productNamesArr

for(let i=0;i<productNamesArr.length;){
    let temp = prompt("Enter "+productNamesArr[i]+" quantity : ");
    if(!isNaN(temp) && temp >= 0){ //empty enter will give us empty string and we'll consider it as 0; 
                                   // (temp != "" && temp != null) adding this 2 conditions with (!isNaN(temp) && temp >= 0)  will make infinite loop until user enter valid numerical so we have avoided it
     prodQntyArr.push(parseInt(temp));
     i++;
    }
    else{
        console.log("Invalid Value : Enter Numerical Value...");
    }
}

let subtotalPrice = 0; // to store total price before discount , gifting and shipping charges
let totalQnty = 0;     // to store total quantity of items considering all products
for(let i=0;i < prodPriceArr.length;i++){
    subtotalPrice += prodPriceArr[i] * prodQntyArr[i];
    totalQnty += prodQntyArr[i];
}

if(totalQnty <= 0 || totalQnty==null || isNaN(totalQnty)){ // if total quanity is 0 or null or not a number then we'll simply return 
    console.log("Cart is Empty...!");
    return ;
}

let discountArr = ["flat_10_discount","bulk_5_discount","bulk_10_discount","tiered_50_discount"]; // discount coupon names
let discountPrice = 0; // to store discount price
let discountFlag = -1; // to store/track coupon that gonna use after comparing benefits

// flat_10_discount calculation function
function func_flat_10_discount(){
    if( subtotalPrice > 200){
        // flat_10_discount = subtotalPrice-10;
        discountPrice = 10;
        discountFlag=0;
    }
    return discountPrice;
}
func_flat_10_discount();

// bulk_5_discount calculation function
let discountedProd = -1;
function func_bulk_5_discount(){

    for(let i =0;i<prodQntyArr.length;i++){
        if(prodQntyArr[i] > 10){
            let temp = 5/100 *(prodPriceArr[i] * prodQntyArr[i]);
            if(temp > discountPrice){
                discountPrice = temp;
                discountFlag=1;
                discountedProd = i;
            }
        }
    }
}
func_bulk_5_discount();

// bulk_10_discount calculation function
function func_bulk_10_discount(){

    if( totalQnty > 20){
        let temp = 10/100 * (subtotalPrice);
        if(temp > discountPrice ){
                discountPrice = temp;
                discountFlag=2;
        }
    }
}
func_bulk_10_discount();

// tiered_50_discount calculation function
function func_tiered_50_discount(){
    if(totalQnty > 30){
        for(let i =0;i<prodQntyArr.length;i++){
            if(prodQntyArr[i] > 15){
                let discountedQnty = prodQntyArr[i] - 15;
                let temp = 0;
                for(let j =0;j<discountedQnty;j++){
                    temp = temp + (prodPriceArr[i]/2);// or 50/100 * prodPriceArr[i]
                }
                // let temp += 5/100 *(prodPriceArr[i] * prodQntyArr[i]);
                if(temp > discountPrice){
                    discountPrice = temp;
                    discountFlag=3;
                    discountedProd = i;
                }
            }
        }
    }
}
func_tiered_50_discount();


//gift and packaging
let giftWrapFee = 1;
let totalGiftWrapFees = 0;
for(let i = 0;i<productNamesArr.length;){
    let temp = prompt("Do you want to wrap "+productNamesArr[i]+" as gift ? Type -> yes or no :");
    temp = temp.toLowerCase();
    if(temp == "" || temp == "yes" || temp == "no" ){ // empty enter will consider as "yes"
        if(temp == "yes" || temp == ""){
            totalGiftWrapFees += giftWrapFee * prodQntyArr[i];
        }
        i++;
    }
}

let shippingCharge = 5;
let shippingPackages = Math.round(totalQnty/10);
console.log(shippingPackages);
if(totalQnty%10 != 0){
    shippingPackages++;
}
console.log(shippingPackages);
let totalShippingPrice = shippingCharge * shippingPackages;
console.log(totalShippingPrice);

let totalPrice = subtotalPrice - discountPrice + totalGiftWrapFees + totalShippingPrice;


//showing result
 console.log("\nProducts :");
 console.log("-------------------------------------------------------");
for(let i=0 ;i<productNamesArr.length;i++){  
    console.log (i+1+" | name :"+productNamesArr[i] + " | price : $" +prodPriceArr[i]+" | quantity : "+prodQntyArr[i] );
}
console.log("-------------------------------------------------------");
console.log("SubTotal        : $"+ subtotalPrice)
console.log(`Discount Coupon : '${discountArr[discountFlag]}'`);
console.log("Discount Price  : $"+discountPrice);
console.log("Gift wrap fees  : $"+totalGiftWrapFees);
console.log("Shipping fees   : $"+totalShippingPrice)
console.log("Total           : $"+totalPrice )
