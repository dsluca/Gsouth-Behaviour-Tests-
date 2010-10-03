/**
*   
*
*My First Story. 
* 
*/

package com.gsouth.behave
import com.thoughtworks.selenium.*


before "start selenium", {
	given "selenium is up and running", {
		selenium = new DefaultSelenium("localhost",
			4444, "*safari", "http://localhost:8083")
		selenium.start()
	}
}

scenario "The Administrator signs on", {
	when "the adminstrator is not logged in", {
		selenium.open("back/logout")
	}

	and "the administrator user name and pw is entered in the login form", {
		/*Becaasue we are using the google user service, 
			locally we have an email and a admin check box */
		selenium.open("back/login")
		selenium.type("email", "admin@boo.com")
		selenium.check("admin")
	}

	and "the Go! button is pressed", {
		selenium.click("submit-login")
	}

	then "the administrator home page should be displayed", {
		selenium.waitForPageToLoad("5000")
		selenium.getTitle().shouldBeEqualTo "Photo CMS - Backend"
	}

}

scenario "A Non-Admin tries to access the admin page", {
	when "There is no user logged in", {
		selenium.open("back/logout")
	}

	and "a non admin user signs in", {
		selenium.open("back/login")
		selenium.type("email", "user@boo.com")
		selenium.uncheck("admin")
		selenium.click("submit-login")
	}

	then "the user hello page is loaded", {
		selenium.waitForPageToLoad("5000")
		selenium.getTitle().shouldBeEqualTo "GSouth - Welcome back!"
	}

}

after "stop selenium", {
	then "selenium should stop", {
		selenium.stop()
	}
}

