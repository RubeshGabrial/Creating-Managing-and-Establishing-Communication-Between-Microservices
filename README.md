
• EurekaServer is the service that will register all other microservices.

• SpringCloudAPIGateway is the service that will act as API Gateway.

• UserAuthenticationService is the service that will have login and save the user functionalities.

• UserTrackService is the service that will have all the CRUD functionalities related to Track object.

• pom.xml is the parent pom.



**Task 1**

• Create a new project using the Spring Initializr to build the Spring Cloud API Gateway.

   - Add the dependencies for Spring Cloud Gateway in the pom.xml.
     
   - Add the Spring Cloud API Gateway in the module of the parent pom.
     
• Configure the routes in the API Gateway. The route should be written using the application name in the application.yml file, instead of the URI of the application. Do not write the port number.

   - All services must be routed through the API Gateway.
     
   - The API gateway must send the request to the corresponding service.
     
• Test the output in Postman.



**Task 2**

• Create a new project using the SpringInitializr to build the Eureka Server.

  - Add the dependencies for the Eureka Server in the pom.xml.
    
  - Add the Eureka Server in the module of the parent pom.
    
  - Add the Eureka client dependency in the UserAuthenticationServer, UserTrackService, and SpringCloudAPIGateway services.
    
• All Services must get registered in Eureka Server.

  - Configure the routes in the API Gateway with service name.
    
• All services must be routed through the API Gateway.

• The API Gateway must send the request to the corresponding service.

• Test the output in Postman.



**Task 3**

• Add the dependencies for Spring Cloud Feign Client in the pom.xml of User TrackService.

• Annotate the main class of UserTrackService with @EnableFeignClients.

• Create a package proxy inside this package to create the UserProxy class.

• This class will have same method saveUser that exists in the controller of UserAuthentication.

• Autowire the UserProxy in the service layer of UserTrackService.

• Use the proxy to send the data to the service.

• So now calling /register should call /save the method internally using microservices communication.

• Run and test your application using Postman.
