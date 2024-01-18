package additionalfees;

public class AdditionalFees {

    private double giftWrapFee ;//per unit
    private double totalGiftWrapFee;

    private int unitsPerPackage;
    private double shippingFee ;//per package (consist of shippingPackageUnits)
    private int shippingPackages ;
    private double totalShippingFee;

    public AdditionalFees(double giftWrapFee, int unitsPerPackage, double shippingFee) {
        this.giftWrapFee = giftWrapFee;
        this.totalGiftWrapFee = 0;
        this.unitsPerPackage = unitsPerPackage;
        this.shippingFee = shippingFee;
        this.shippingPackages = 0;
        this.totalShippingFee = 0;
    }

    public double getGiftWrapFee() {
        return giftWrapFee;
    }

    public void setGiftWrapFee(int giftWrapFee) {
        this.giftWrapFee = giftWrapFee;
    }

    public double getTotalGiftWrapFee() {
        return totalGiftWrapFee;
    }

    public void setTotalGiftWrapFee(int totalGiftWrapFee) {
        this.totalGiftWrapFee = totalGiftWrapFee;
    }

    public int getUnitsPerPackage() {
        return unitsPerPackage;
    }

    public void setUnitsPerPackage(int unitsPerPackage) {
        this.unitsPerPackage = unitsPerPackage;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(int shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getShippingPackages() {
        return shippingPackages;
    }

    public void setShippingPackages(int shippingPackages) {
        this.shippingPackages = shippingPackages;
    }

    public double getTotalShippingFee() {
        return totalShippingFee;
    }

    public void setTotalShippingFee(int totalShippingFee) {
        this.totalShippingFee = totalShippingFee;
    }

    public void calc_ShippingPackages(int totalQnty){
        shippingPackages += totalQnty/unitsPerPackage;
        if (totalQnty%unitsPerPackage != 0){
            shippingPackages++;
        }
    }
    public void calc_totalShippingFee(){
        totalShippingFee += shippingFee * shippingPackages;
    }
    public void calc_totalGiftWrapFee(int qnty){
        totalGiftWrapFee += giftWrapFee * qnty;
    }
}
