/**
*   
*
*My First Story. 
* 
*/

import com.thoughtworks.selenium.*


before "start selenium", {
	given "selenium is up and running", {
		selenium = new DefaultSelenium("localhost",
			4444, "*safari", "http://localhost:8083/back")
		selenium.start()
	}
}

scenario "The Administrator signs on", {

	when "the administrator user name and pw is entered in the login form", {
		selenium.open("http://localhost:8083/back")
		selenium.type("uname", "Administrator")
		selenium.type("password", "letMeIn")
	}

	and "the Go! button is pressed", {
		selenium.click("submit")
	}

	then "the administrator home page should be displayed", {
		selenium.waitForPageToLoad("5000")
	}

}

after "stop selenium", {
	then "selenium should stop", {
		selenium.stop()
	}
}

