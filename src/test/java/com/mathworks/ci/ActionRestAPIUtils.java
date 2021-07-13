package com.mathworks.ci;

import io.restassured.http.Method;
import io.restassured.response.Response;
import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ActionRestAPIUtils {
    RestAPIURL restAPIURL;
    ResponseUtils responseUtils;
    String actionWorkflowFileName;

    List<Long> jobIds =  new ArrayList<Long>();
    List<String> jobNames = new ArrayList<String>();
    Long jobid;
    String jobName;

    public void setRestAPIURL(RestAPIURL restAPIURL) {
        this.restAPIURL = restAPIURL;
    }

    // Setter for YML file
    public void setActionWorkflowFileName(String actionWorkflowFileName) {
        this.actionWorkflowFileName = actionWorkflowFileName;
    }

    public void setResponseUtils(ResponseUtils responseUtils) {
        this.responseUtils = responseUtils;
    }

    // Gets the latest run id of the YML file
    int getRunId(){
        Response response;

        String URL = restAPIURL.common_url() + "actions/workflows/" + actionWorkflowFileName + "/runs";
        response = responseUtils.getResponse(Method.GET, URL);

        return response.jsonPath().get("workflow_runs[0].id");
    }

    // Gets the Job Ids and Job names for the latest run
    void getJobsData(){
        int runId = getRunId();
        String url = restAPIURL.common_url() + "actions/runs/" + Long.toString(runId) + "/jobs";
        Response response = responseUtils.httpRequest.request(Method.GET, url);
        Assert.assertEquals(response.getStatusCode(), 200);
         jobIds = response.jsonPath().get("jobs.id");
         jobNames = response.jsonPath().get("jobs.name");
    }

    // Gets the Job Id from Job name
    Long getJobid(String jobName){
        if(jobIds.isEmpty()) {
            getJobsData();
        }
        this.jobName = jobName;
        getJobsData();
        int i = jobNames.indexOf(jobName);
        jobid = jobIds.get(i);
        return jobid;
    }

    // Verifies the job status based on the job id
    public void checkJobStatus(String jobName, String status){
        jobid = getJobid(jobName);
        String url = restAPIURL.common_url() + "actions/jobs/" + Long.toString(jobid);
        Response response = responseUtils.httpRequest.request(Method.GET, url);
        String job_status = response.jsonPath().get("conclusion");
        assertEquals(job_status, status);
    }

    // Checks the build log for error message
    public void verifyLogContains(String message){
        if(jobIds.isEmpty()) {
            getJobsData();
        }
        String url = restAPIURL.common_url() + "actions/jobs/" + Long.toString(jobid) + "/logs";
            Response response = responseUtils.httpRequest.request(Method.GET, url);
            String log = response.getBody().asString();
            assertTrue(log.contains(message));
    }

}

