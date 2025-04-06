# bookstore

This projects use:
  Java 11 Spring Boot
  H2 (db info:: user: sa password: password)
  Restful endpoints
  
Controllers:
  Customer Controller,
  Book Controller,
  Order Controller,
  Statistic Controller
    
Validations:
  Customers cannot buy less and equal than 0 item.
  Customer cannot buy item not enough stock quantity.
  Customer cannot register existing email address.
  
After run project, you can request endpoints via Book Store.postman_collection in repository.
You should import the Book Store.postman_collection file to Postman. 
