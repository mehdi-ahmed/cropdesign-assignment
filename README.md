# cropdesign-assignment 

Via the following link you can find a zipped xml file:
obo-xml.gz op http://www.geneontology.org/GO.downloads.ontology.shtml (legacy downloads section)
Write a java program with  user-friendly graphical interface that:

 - step 1: reads the xml file and displays the GO terms (term tag) in a tree structure (eg. JTree). This trees has the id of the GO term as nodes.
 - step 2: the additional data (def / synonym / ...) is in the subnodes of the tree.
 - step 3: When a certain term which has reference to an other term (<is_a> tag) and you click on this reference term tag, put the focus in the tree on the node of that other term.


Prerequisites: 
-Maven 3.
-JDK 6 or greater.


How To run the cropdesign-assignment Tool : 

I - For Windows users: 

 1 - Set up Maven 3 environment variable.
 ==> To set you maven environment variable, please consider reading this : 
http://maven.apache.org/download.cgi

 2 -  Set up JDK 6 or 7 (Tested on both) environment variable.
 ==> To set you Java environment variable, please consider reading this : 
https://www.java.com/en/download/help/path.xml

3  - Go to Gihub : 
  3.1 https://github.com/mehdi-ahmed/cropdesign-assignment
  3.2 click on the Download Zip, on the right of the screen.
  3.3 Unzip Folder and go to root folder. You will find sources files and bat file. Click on startswing.bat
  
II - For Linux/Mac users: 

  - Follow Step I.1 and I.2
  - open a shell terminal and type : 
     mvn clean install -DskipTests exec:java -Dexec.mainClass="com.cropdesign.assignment.ui.GoTermsTree"
  


