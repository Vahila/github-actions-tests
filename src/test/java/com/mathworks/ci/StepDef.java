package com.mathworks.ci;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDef {
    RestAPIURL restapiurl;
    ResponseUtils responseUtils;
    ActionRestAPIUtils actionRestAPIUtils = new ActionRestAPIUtils();

    // Adding the background for the tests
    @Given("Test repository owner, repo name are set for REST API URL base")
    public void setRESTAPIURLBase() {
        // Setting the details of repo name and owner
        restapiurl = new RestAPIURL();
        restapiurl.setOwner(GetTestData.getPropValues("setup.matlab.repo.owner"));
        restapiurl.setRepo(GetTestData.getPropValues("setup.matlab.repo"));

        // Rest assured util to add header
        responseUtils = new ResponseUtils();

        // Using the Util  for running the REST APIs
        actionRestAPIUtils = new ActionRestAPIUtils();
        actionRestAPIUtils.setRestAPIURL(restapiurl);
        actionRestAPIUtils.setResponseUtils(responseUtils);
    }

    // The YML file is set through this step
    @When("The test YML{string} is run")
    public void theTestYMLIsRun(String file) {
        actionRestAPIUtils.setActionWorkflowFileName(file);
    }

    // The status of the job from the YML file is verified
    @Then("Build status of {string} is {string}")
    public void VerifyBuildStatus(String job, String status) {
        actionRestAPIUtils.checkJobStatus(job, status);
    }

    // Build log of the job is checked
    @And("Build log has {string}")
    public void VerifyBuildLog(String message) {
        actionRestAPIUtils.verifyLogContains(message);
    }
}
