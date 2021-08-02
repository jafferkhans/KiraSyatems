################## Project Configurations ##################
1. The enclosed project is a Maven project along with the TestNG framework
2. The dependencies for this project would just be the JDK and Maven(Installation)
3.The chromedriver versions for both Mac and Windows are included in the project.
4. If the project is run in windows, please change line 25 in the class(KiraSystemsSanityCheck.java), from : 
	System.setProperty("webdriver.chrome.driver","chromedriver_mac_os_x"); to :
	System.setProperty("webdriver.chrome.driver","chromedriver_win.exe");
5. The project has been implemented in the Mac environment using Eclipse IDE.
6.TestNG file RunAllTests.xml can be used to run both tests.(From the command line: "mvn clean install -DsuiteXmlFile=RunAllTests.xml"
7. Results for the last run can be found in the "test-output" folder(under the project folder).

################## Assumptions ##################

################## Part A ##################
The location used to save the snapshots is the /tmp folder.
All snapshots are saved with the same name as the Test method.
The test case validates if the first search result points to the URL "https://kirasystems.com/".

################## Part B ##################
1. The IDs for the 3 mentioned stops have been picked from making a GET call to the http://restbus.info/api/agencies/ttc/routes/504/ 
       King St West At John St West Side - 23890
       King St West At John St East Side - 23891
       King St West At Peter St West Side - 23892
2. Considering the above stops a data-driven approach has been taken.
3. The code iterates for every distinct route ID and gets the response.
4. The URL and other related information are placed in a properties file. More environment files/fields can be added based on the environment.
5. RestAssured libraries have been used to make the API calls along with the TestNG framework.
6. Data provider StopsToCheck has been created with just the stop ids(more stop ids can be added to use the same code).
7. Assertions have been used to validate the response. Following objects and fields are being validated.
	Check If fields Agency ID and title are available in the response. Else assert as failed.
	Check If fields Route ID and title are available in the response. Else assert as failed.
	Check If fields Stop ID and title are available in the response. Else assert as failed.
	Ensure that there's at least one prediction for the route.
