/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.viewModel;

/**
 *
 * @author qivub
 */
public class ProductDetail {
    int ProductID;
    String ProductName;//ten
    String ProductDescription;//mo ta
    String ProductImage;// anh
    int Quantity;//so luong
    double Price;// gia
    boolean ProductStatus;// trang thai
    String CategoryName;//ten danh muc
    String BrandName;//ten hang
    String SizeNames;// size
    String ColorNames;// mau

    public ProductDetail(int ProductID, String ProductName, String ProductDescription, String ProductImage, int Quantity, double Price, boolean ProductStatus, String CategoryName, String BrandName, String SizeNames, String ColorNames) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductDescription = ProductDescription;
        this.ProductImage = ProductImage;
        this.Quantity = Quantity;
        this.Price = Price;
        this.ProductStatus = ProductStatus;
        this.CategoryName = CategoryName;
        this.BrandName = BrandName;
        this.SizeNames = SizeNames;
        this.ColorNames = ColorNames;
    }

    public ProductDetail() {
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String ProductDescription) {
        this.ProductDescription = ProductDescription;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String ProductImage) {
        this.ProductImage = ProductImage;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public boolean isProductStatus() {
        return ProductStatus;
    }

    public void setProductStatus(boolean ProductStatus) {
        this.ProductStatus = ProductStatus;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getSizeNames() {
        return SizeNames;
    }

    public void setSizeNames(String SizeNames) {
        this.SizeNames = SizeNames;
    }

    public String getColorNames() {
        return ColorNames;
    }

    public void setColorNames(String ColorNames) {
        this.ColorNames = ColorNames;
    }
    
}
