# Symplicity Voting App
=====================================================================================
# Front End UI {HTML5, CSS3, BootStrap, AngularJS 1.5, GULP, BOWER}
The app has 2 services, a user service and a fruit service. Each of the services perform the required CRUD operations.
There are 5 controllers, namely,
'header': used with nav to provide functionalities like signOut or Home.
'footer': provides the basic footer for the app.
'fruit': This controller is the controller for the default HOME view of the app. It delegates data from the fruit service and provides it to the template view. I've added a feature where user can search for a fruit by its name. Also, we can sort the view by name or votes, in any required order(ASC or DESC). Furthermore, when the cursor is hovered over an item, its details are displayed. If the user is an admin, even more options are displayed. Finally, the entire view is a paginated list; so if there are many items or if more items are added dynamically, it won't be an issue. Once the user clicks on any fruit, its details are displayed.
'fruit-details': This controller provides the template view of fruit details. Fruit name and the user votes are displayed. Also, there is a button to register user's vote, which will inrement the vote count for that particular fruit.
'modal': When the user is an admin, it can add more fruits, edit an existing fruit or even delete a particular fruit. I've used modals to display those additional features for admin. This controller deals with those extra options and provides the appropriate view.
There are several template views like header,footer, fruit,fruit-details and modal which display the appropriate features.
Finally, this entire app is minified using gulp and creating gulp pipelines, which inject the dependencies(files) as and when required. Bower is used to provide dependencies for angular and bootstrap frameworks.
Routing in the app is done by UI-router and not the default ngRoute. $states are used, which help with the nested views and not binding a particular view with the url.
Many other features are used like session storage, to store the user object, using which we determine the role of user as an admin or a regular user.
Notification Provider is used to provide user-friendly notifications and to make the experience as simple as possible.
ngMessages are used for form validation and displaying appropriate messages where the requirements are not met. UI-bootstrap is used for pagination of the view.
=====================================================================================
# Back End{Java Spring, JPA, Hibernate, Maven, Swagger for API documentation, JUnit for unit testing, MySQL}
Just like the front-end, even the backend is MVC. Layered approach helps keep the code clean and injects dependency as required.
There are several layers starting the basic Entity layer(class) for entities like user, fruit etc. There's an enumeration ROLE used to determine if user is ADMIN or not.
There is a CONTOLLER layer which provides the mapping for API endpoints and directs the further calls.
Service layer which further forward the request to DAO(Data Access Object) for processing.
DAO is the final layer, which does all the communication with the DB and lower level processing and returns back the result.
Also, have provided an EXCEPTION layer that takes care of all exceptions like 'UserAlreadyExists','UserNotFound','FruitAlreadyExists' or 'FruitNotFound'. Appropriate API Responses are provided in case of success or failure such as status 200, 404 or 500 etc. JackSon library is used to convert data into JSON for reading and sending response. Also used MOCIKTO to mock and test the enitre backend API.
=====================================================================================
Overall, it's a completely tested, fully-functional web-app. Below are some screen-shots
![Alt text](https://cloud.githubusercontent.com/assets/20270770/18590217/ff3b3f4e-7bfb-11e6-8bca-a08551627020.PNG "Sign Up")
![Alt text](https://cloud.githubusercontent.com/assets/20270770/18590227/0756101e-7bfc-11e6-8212-f85a03c09490.PNG "Home")
![Alt text](https://cloud.githubusercontent.com/assets/20270770/18590232/0ae3ecba-7bfc-11e6-9e6a-b1393631e6be.PNG "Home")
![Alt text](https://cloud.githubusercontent.com/assets/20270770/18590235/0f3de34c-7bfc-11e6-9288-83cd96614a39.png "Hover Over Fruit")
![Alt text](https://cloud.githubusercontent.com/assets/20270770/18590240/1275da2e-7bfc-11e6-8940-02799094e42a.PNG "Fruit Details")
![Alt text](https://cloud.githubusercontent.com/assets/20270770/18590251/2106fab4-7bfc-11e6-9a72-6177867099d5.png "HomePage Sort/Search")