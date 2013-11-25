/*
Project Food Company -- CS 157A - section 1
    Group: 479
    Team Member: 
           Diem Nguyen
           Thinh Le
           Qui Trinh
*/

Schema: companydb
Website: 
           localhost:8080/comdb/    or      localhost:8080/comdb/login.jsp


*** "comdb" folder contains the jsp pages and java servlet classes of our website.
*** Before run the web, please follow these steps 
( suppose the path for downloading zip file under C:\ )
( suppose you have the local server Tomcat example C:\Tomcat5 ):

1. C:\comdb\sql\ contains all the text files and sql file of the database companydb

2. Open C:\comdb\sql\companydb.sql and run all queires in the file 
   -- to create the database "companydb"

3. Copy the comdb.war file from the path: C:\comdb\dist\comdb.war ,
and then paste it into tomcat webapps folder C:\Tomcat5\webapps

4. Start tomcat local server by running this file : C:\Tomcat5\bin\startup.bat 
   -- comdb will be deployed under webapps folder : C:\Tomcat\webapps\comdb

5. Go any browser and run :  localhost:8080/comdb/
