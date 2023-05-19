import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1, "Product1", 100, 1);
        Product product2 = new Product(2, "Product2", 200, 2);
        products.add(product1);
        products.add(product2);
        shoppingCart = new ShoppingCart(products);
    }

    @Test
    public void testAddProductToCart() {
        Product productToAdd = new Product(3, "Product3", 300, 1);
        shoppingCart.addProductToCart(productToAdd);
        Assert.assertEquals(3, shoppingCart.getProducts().size());
    }

    @Test
    public void testAddExistingProductToCart() {
        Product productToAdd = new Product(1, "Product1", 100, 2);
        shoppingCart.addProductToCart(productToAdd);
        Assert.assertEquals(2, shoppingCart.getProducts().size());
        Assert.assertEquals(3.0, shoppingCart.getProductById(1).getQuantity(), 0.0);
    }

    @Test(expected = ProductNotFoundException.class)
    public void testRemoveNonExistingProductFromCart() {
        Product productToRemove = new Product(3, "Product3", 300, 1);
        shoppingCart.removeProductFromCart(productToRemove);
    }

    @Test
    public void testRemoveProductFromCart() {
        Product productToRemove = new Product(1, "Product1", 100, 1);
        shoppingCart.removeProductFromCart(productToRemove);
        Assert.assertEquals(1, shoppingCart.getProducts().size());
    }

    @Test
    public void testGetCartTotalPrice() {
        Assert.assertEquals(500.0, shoppingCart.getCartTotalPrice(), 0.0);
    }
}

