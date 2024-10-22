To start the application follow the steps below:
1. Ensure that you have JAVA v17 or higher installed on your machine
2. from root directory run `.\gradlew build` to build the application
3. run `java -jar .\build\libs\task-1.0.jar` or `.\gradlew bootrun`

OR

1. ensure that you have docker installed on your machine
2. run `docker-compose up` from the root directory

in each case the application should be avalable on `localhost:8080` by default


### Endpoints
there is collection of endpoints in `endpoitns` folder exported from `Bruno` (https://www.usebruno.com/)

for full api documentation check `/swagger-ui/index.html`
