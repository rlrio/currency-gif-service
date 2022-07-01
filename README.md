# Currency Gif Service

This is a Spring Boot application that checks the exchange rate against the dollar and returns a gif if the rate has risen or fell compared to yesterday.

---
### To run this app:

- **!!!** First, you should add secrets.yml file to the root of the project.

- **Docker**  
    you can build a docker image with a Dockerfile 
    ***(docker build -t currency-app .)***   
    then simply run it ***(docker run -dp 8080:8080 currency-app)***

- **Idea**  
    clone this project and run CurrencyGifApplication class  
    or   
    use ***gradle build*** command and  
    ***java -jar build/libs/CurrencyGifProject-1.0-SNAPSHOT.jar***

- **Console**  
    go to the project root and use ***gradle build*** command and after that   
    ***java -jar build/libs/CurrencyGifProject-1.0-SNAPSHOT.jar*** command
