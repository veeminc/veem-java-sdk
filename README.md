
Veem-Java-Sdk
===========

The Veem Java SDK provides an interface to make it easier to call Veem Global Payments APIs for server side Java backend service.

## Documentation 

- [Veem Global Payments APIs](https://developer.veem.com/reference)
- [Developer Dashboard](https://developer.veem.com/page/dev-dashboard-sandbox)
  

## System Requirements
1. The SDK works on JDK 8 and above.
2. A [developer]((https://developer.veem.com/page/dev-dashboard-sandbox)) account
3. An [application with a customer account]((https://developer.veem.com/page/dev-dashboard-sandbox)) and the associated client id and secret
   (Authorization flow / Client Credentials flow)

## First Use Instructions
1. Clone the GitHub repo to your computer.
2. Import it to the IDE of your choice.


## Testing the Code & Building Artifacts

To test the code locally, follow the steps below:

1. cd to the project directory
2. Client can either integrate with Authorization flow or Client Credential Flow;
3. For Authorization flow, fill in the `testConfigAuth.json` file values with client id ,client secret and redirect url (optional).
4. For Client Credentials flow, fill in the `testConfigClientCreds.json` file values with client id and client secret.
5. To exercise all Veem Global Payment APIs, fill access_token received from either step 3 or 4 to `testConfig.json`.
6. Run the command: `./gradlew build` - this will run the unit test and build the sdk jar.
