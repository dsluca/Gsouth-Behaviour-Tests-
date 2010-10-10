

package org.dlucas.test
import com.thoughtworks.selenium.*


before "start selenium", {
	given "selenium is up and running", {
		selenium = new DefaultSelenium("localhost",
			4444, "*safari", "http://localhost:8086")
		selenium.start()
	}
}

scenario "Testing table lookup", {
	given "A user with no super elite permissions is logged in", {
		selenium.open("selenium_test")	
	}
	when "A search returns more that one values but ", {
	}
	then "The table should appear ...", {
		selenium.waitForPageToLoad("5000")
		header_values = ["Acct No.", "Receipt", "bla bla"]
		table_rows = [["Purple_5", "12345667", "test"], ["089967","987654","test_elite"]]
		mapped_td = [1, 3, 4]
		for(i in 0..<header_values.size()) {
			selenium.getText("//table//tr[1]/th[${mapped_td[i]}]").shouldBeEqualTo header_values[i]
		}
		for(i in 1..table_rows.size()) {
			for(j in 0..<table_rows[i-1].size()) {
				selenium.getText("//table//tr[${(i+1)}]/td[${mapped_td[j]}]").shouldBeEqualTo table_rows[i-1][j]
			}
		}
	}
}

after "stop selenium", {
	then "selenium should stop", {
		selenium.stop()
	}
}
