


## package control   
Control class and data packages   
Classes ending with Data don't have public methods and have only public attributes.    
Data classes are for saving to files and actual implementation is done elsewhere


    
##### FileHandler    

saves and loads HomeConf, users, viewData from files    
    
    
##### Control


Control handles login, saving and loading data
public boolean login   
public void logout   
public boolean register(String name, String pass)
public void addToConf(SmartObject o)   
public void removeFromConf(SmartObject o)   
public boolean addView(View view)
public void deleteView(View view);


##### HomeConf   


Represents the physical houses
Admin can add and remove smartObjects from houses and add new to them    
Adding and removing is done in Control class


##### ViewData


Admin created View for the user    
ViewData can have as much or less than what HomeConf has    



##### UserData


user login info   


##### User 





## Package house  


##### Attribute 

Every house object has attributes 
Room can have temparature, ...
House can be on fire, ....


##### SmartObject   

Object has to be in some room 
public House getHouse() 
public Room getRoom() 




##### Room

Room has objects and room has to be in some house


##### House

has rooms and objects    



## Package screen

Screen takes the whole window space



##### Root

root is always visible but some screen is always filled over it
root has every screen 
root has control to switch the current screen
only one screen is visible at time



##### abstract Screen

every screen extends this

    public abstract void show();
    public abstract void hide();
    public abstract void alert();


alert opens popup window that has annoying error info and such



##### UserSelect    


First screen where you select the user    
If the user is password protected go to login screen  




##### LoginScreen


Opens either AdminScreen or UserScreen



##### AdminScreen





##### UserScreen








Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

Debugging client side code
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"

Developing a theme using the runtime compiler
-------------------------

When developing the theme, Vaadin can be configured to compile the SASS based
theme at runtime in the server. This way you can just modify the scss files in
your IDE and reload the browser to see changes.

To use on the runtime compilation, open pom.xml and comment out the compile-theme 
goal from vaadin-maven-plugin configuration. To remove a possibly existing 
pre-compiled theme, run "mvn clean package" once.

When using the runtime compiler, running the application in the "run" mode 
(rather than in "debug" mode) can speed up consecutive theme compilations
significantly.

It is highly recommended to disable runtime compilation for production WAR files.

Using Vaadin pre-releases
-------------------------

If Vaadin pre-releases are not enabled by default, use the Maven parameter
"-P vaadin-prerelease" or change the activation default value of the profile in pom.xml .
