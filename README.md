# dropboxproject
Simple selenium automation to do basic dropbox operations. (Login, Logout, FileUpload etc.)

1. Needs Maven and Java JDK Installed (minumim set to java 8) and "MVN_HOME" and "JAVA_HOME" set correctly.
2. then when in the folder with pom.xml run mvn clean test
3. From Eclipse -- import as Maven Project and install testNG plugin.
4. then open src/test/resources/testcases/testng.xml and then select run > Run As > TestNG Suite

Some Prerquisites : 
-- Needs a Folder C:/SP/Test.txt to be present with file to upload
-- Dropbox credentials should be updated in testng.xml

Link to Presentation : https://docs.google.com/presentation/d/1GjPApCOBHe4WfOZkxYVEiWFjoSGbk9BsR5BLCfe9A4s/edit?usp=sharing