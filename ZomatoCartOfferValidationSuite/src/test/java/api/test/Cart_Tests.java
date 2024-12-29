package api.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.Routes;
import api.utility.UtilityMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Cart_Tests {

	UtilityMethods setup;

	@BeforeClass
	public void setup() {
		setup = new UtilityMethods();
		RestAssured.baseURI = Routes.Base_Url_2; 


	}
	@Test(priority=1,description="Test mock user creation")
	public void testMockerUserCreation()
	{
		Response response1=  setup.createMockUser(2, "p1");
		Response response2= setup.createMockUser(5, "p2");
		response1.then().statusCode(201);
		response2.then().statusCode(201);
	}

	@Test(description="User should get X amount of Discount")
	public void testFlatXAmountOff() {
		setup.createOffer(1, "FLATX", 10, new String[]{"p1"});
		Response response = setup.applyOffer(200, 1, 1);
		int finalCartValue = response.jsonPath().getInt("cart_value");
		assertEquals(190, finalCartValue);
	}

	@Test(description="No discount will be applied if user is ordering from other restaurants")
	public void testUserWithNoRestauId() {
		setup.createOffer(4, "FLATX", 10, new String[]{"P1"});
		Response response = setup.applyOffer(400, 5, 5);
		int finalCartValue = response.jsonPath().getInt("cart_value");
		assertEquals(400, finalCartValue);
	}

	@Test(description="User should get X% Discount")
	public void testFlatX_Percentage_Off() {
		setup.createOffer(10, "FLAT X%", 10, new String[]{"p1"});
		Response response = setup.applyOffer(2000, 1, 10);
		int finalCartValue = response.jsonPath().getInt("cart_value");
		assertEquals(1800, finalCartValue);
	}

	@Test(description="After Applying X% of discount cart value should be greater than or equal to 0")
	public void testCartValue() {
		setup.createOffer(2, "FLAT X%", 10, new String[]{"p1"});
		Response response = setup.applyOffer(0, 2, 2);
		int finalCartValue = response.jsonPath().getInt("cart_value");
		assertEquals(finalCartValue,0);
	}

	@Test(description="After Applying discount cart value should be greater than or equal to 0")
	public void testCartValue2() {
		setup.createOffer(2, "FLATX", 10, new String[]{"p1"});
		Response response = setup.applyOffer(9, 2, 2);
		int finalCartValue = response.jsonPath().getInt("cart_value");
		assertEquals(finalCartValue, 0);
	}

	@Test(description="It should raise error when cart value is less than 0")
	public void testNegativeCartValue() {
		Response response = setup.applyOffer(-20, 2, 2);
		response.then().statusCode(400);
	}
	@Test(description="if offer value is negative then cart values should not change")
	public void testNegativeOfferValue() {
		setup.createOffer(2, "FLATX", -10, new String[]{"p1"});
		Response response = setup.applyOffer(100, 2, 2);
		int finalCartValue = response.jsonPath().getInt("cart_value");
		assertEquals(finalCartValue, 100);
	}

	@Test(description="User Should get updated value of Discount")
	public void testUpdatedDiscount() {
		RestAssured.baseURI = Routes.Base_Url_2;
		setup.createOffer(2, "FLATX", 10, new String[]{"p2"});
		setup.createOffer(2, "FLATX", 30, new String[]{"p2"});
		Response response = setup.applyOffer(200, 2, 2);
		int finalCartValue = response.jsonPath().getInt("cart_value");
		assertEquals( finalCartValue,170);
	}
}
