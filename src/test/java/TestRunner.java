import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;

import java.io.IOException;

public class TestRunner {
    Customer customer;

    @Test
    public void doLogin() throws ConfigurationException, IOException {
        customer = new Customer();
        customer.callingLoginAPI();
    }

    @Test
    public void getCustomerList() throws ConfigurationException, IOException {
        customer = new Customer();
        customer.callingCustomerListAPI();
    }

    @Test
    public void searchCustomer() throws ConfigurationException, IOException {
        customer = new Customer();
        customer.callingCustomerSearchAPI(102);
    }

    @Test
    public void generateCustomerInfo() throws ConfigurationException, IOException {
        customer = new Customer();
        customer.generateCustomer();
    }
    @Test
    public void createCustomer() throws IOException {
        customer = new Customer();
        customer.callingCreateCustomerAPI();
    }
    @Test
    public void updateCustomer() throws IOException {
        customer = new Customer();
        customer.callingUpdateCustomerAPI(102,"Md Shaon", "shaon@gmail.com","Dhaka","01680235344");
    }
    @Test
    public void deleteCustomer() throws IOException {
        customer = new Customer();
        customer.callingCustomerDeleteAPI(52327);
    }
}
