package com.java.rest.service.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.java.rest.service.Customer;
//import com.java.rest.service.CustomerService;
import com.sun.jersey.spi.resource.Singleton;

@Produces("application/xml")
@Path("/customers")
@Singleton
public class CustomerResource {

  private TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();

  public CustomerResource() throws IOException {
    // hardcode a single customer into the database for demonstration
    // purposes
    Customer customer = new Customer();
    customer.setCustomerId(244703);
    customer.setFirstName("asdsad");
    customer.setLastName("GHOSH");
    customer.setZipcode("712147");
    addCustomer(customer);
  }

  @GET
  public List<Customer> getCustomers() {
    List<Customer> customers = new ArrayList<Customer>();
    customers.addAll(customerMap.values());
    return customers;
  }

  @GET
  @Path("{id}")
  public Customer getCustomer(@PathParam("id") int cId) {
    return customerMap.get(cId);
  }

  @POST
  @Path("add")
  @Produces("text/plain")
  @Consumes("application/xml")
  public String addCustomer(Customer customer) throws IOException {
  //  new CustomerService().persist(customer,0l);
    return "Customer " + customer.getFirstName() + " added with Id ";
  }
}