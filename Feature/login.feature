@SmokeTest
Feature: Login test

@tag1
Scenario: Successfull Login with valid credentials
Given User is on home page
And Click on Log in link
When Enters email and password and clicks login
|email|password|
|pratiksha@test.com|pratiksha|
|rick@test.com|tester|
Then User logged in successfully