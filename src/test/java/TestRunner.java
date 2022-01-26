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
}
