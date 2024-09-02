package com.qa.api.tests.GET;

import com.api.listeners.TestAllureListener;
import com.api.utils.FetchProperties;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.HttpHeader;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({TestAllureListener.class})

public class APIResponseHeadersTest {

    Playwright playwright;
    APIRequest request;
    APIRequestContext requestContext;

    @BeforeTest
    public void setup(){
        playwright = Playwright.create();
        request =  playwright.request();
        requestContext = request.newContext();
    }
    @AfterTest
    public void tearDown(){
        playwright.close();
    }

    @Test(priority = 2, description = "Verifying the headers of the response JSON test")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Verify the structure of the response JSON headers")
	@Story("Story Name: To validate the JSON response headers")
    public void getHeadersTest() throws IOException{
    	String baseURI= FetchProperties.fetchURI();
    	String resource = "/api/users";
    	String endpoint = baseURI.concat(resource);
    	System.out.println(endpoint);
        APIResponse apiResponse = requestContext.get(endpoint);
        int statusCode = apiResponse.status();
        System.out.println("response status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        //using map:
       Map<String, String> headersMap =  apiResponse.headers();
        headersMap.forEach((k,v) -> System.out.println(k + ":" + v));
        System.out.println("total response headers: " + headersMap.size());
        Assert.assertEquals(headersMap.get("server"), "cloudflare");
        Assert.assertEquals(headersMap.get("content-type"), "application/json; charset=utf-8");

        System.out.println("===============================");

        //using list:
       List<HttpHeader> headersList = apiResponse.headersArray();
        for(HttpHeader e : headersList){
            System.out.println(e.name + " : " + e.value);
        }
    }

}
