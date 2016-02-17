## package control



##### FileHandler    


Loads and saves conf and state to files  




##### *Data classes   


Data classes are used for saving to file and they only contain values.    
Actual doing code is implemented elsewhere.    



##### HomeConf    


Represents the physical houses   
Admin can add and remove objects from conf







## package house



##### House    
has rooms and objects     




## package screen

Every screen extends abstract class Screen    
Root has every screen but only one screen is visible     
Screen takes the whole window space    



UserSelect is the first screen    
if the selected user requires password goto login page else userpage or adminpage




## package sh 


init application



##### Globals

user // current user     
root // root screen    
control // used to control application








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
