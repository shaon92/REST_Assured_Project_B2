
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestRunner {
    Customer customer;

    @Test(priority = 0, description = "Log In")
    public void doLogin() throws ConfigurationException, IOException {
        customer = new Customer();
        customer.callingLoginAPI();
    }

    @Test(priority = 1, description = "Get all customers")
    public void getCustomerList() throws ConfigurationException, IOException {
        customer = new Customer();
        customer.callingCustomerListAPI();
    }

   @Test(priority = 2, description = "Search Customer")
    public void searchCustomer() throws ConfigurationException, IOException {
        customer = new Customer();
        customer.callingCustomerSearchAPI(102);
    }

    @Test(priority = 3, description = "Generate Random Customer")
    public void generateCustomerInfo() throws ConfigurationException, IOException {
        customer = new Customer();
        customer.generateCustomer();
    }
    @Test(priority = 4, description = "Create Customer")
    public void createCustomer() throws IOException {
        customer = new Customer();
        customer.callingCreateCustomerAPI();
    }
    @Test(priority = 5, description = "Update Customer")
    public void updateCustomer() throws IOException {
        customer = new Customer();
        customer.callingUpdateCustomerAPI(102,"Md Shaon", "shaon@gmail.com","Dhaka","01680235344");
    }
    @Test(priority = 6, description = "Delete Customer")
    public void deleteCustomer() throws IOException {
        customer = new Customer();
        customer.callingCustomerDeleteAPI(352879);
    }
}
