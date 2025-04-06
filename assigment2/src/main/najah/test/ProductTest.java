package main.najah.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.najah.code.Product;

@DisplayName("Test product")
public class ProductTest {
    Product p;
    private static final double DELTA=0.001;
    @Test
    @DisplayName("Constructor set name and price")
    void testConstructorVaildInput() {
    	String expectedName="PC";
    	double expectedPrice=500.99;
    	p= new Product(expectedName, expectedPrice);
    	assertEquals(expectedName, p.getName());
    	assertEquals(expectedPrice, p.getPrice(),DELTA);
    	assertEquals(0.0, p.getDiscount(), DELTA);
    }
    
    @Test
    @DisplayName("Constructor with Zero Price")
    void testConstructorZeroPrice() {
    	Product product =new Product("Laptop", 0.0);
    	assertEquals(0.0, product.getPrice(),DELTA);
    	assertEquals(0.0,product.getDiscount(),DELTA);
    }
    
    @Test
    @DisplayName("Constructor With no name")
    void testConstructorWithNoName() {
    	Product product=new Product(null, 500.0);
    	assertEquals(null, product.getName());
    }
    
    @Test 
    @DisplayName("Constructor With less than zero price")
    void testConstructorWithLessThanZeroPrice() {
    	assertThrows(IllegalArgumentException.class, ()->new Product("Mouse", -50.0));
    }
    
    @Test
    @DisplayName("Test Apply Discount More Than Fifty ")
    void testApplyDiscountMoreThanFifty() {
    	Product product=new Product("Tablet", 500);
    	assertThrows(IllegalArgumentException.class,()->product.applyDiscount(51));
    }
    
    
    
    
    
    @Test
    @DisplayName("Test Apply Discount Less Than Zero ")
    void testApplyDiscountLessThanZero() {
    	Product product= new Product("Laptop", 50);
    	assertThrows(IllegalArgumentException.class, ()->product.applyDiscount(-10));
    }
    @Test
    @DisplayName("Test Apply Discount ")
    void testApplyDiscount() {
    	Product product= new Product("Laptop", 50);
    	product.applyDiscount(20);
    	assertEquals(20.0,product.getDiscount(),DELTA);
    }
    @Test 
    @DisplayName("Test get Final Price")
    void tesGetFinalPrice() {
    	Product product=new Product("Headphone", 250);
    	product.applyDiscount(0);
    	assertEquals(250, product.getFinalPrice(),DELTA);
    }

}
