Feature: MATLAB Actions features

  # Background sets the required details of the repository like owner, name and branch, if needed.
  # It also adds the required authorization for getting the logs
  Background:
    Given Test repository owner, repo name are set for REST API URL base

  # The YML file is also passed an argument hence all the features use the same step definition files for checking job
  # status and build logs

  Scenario Outline:  Check for job status and message in logs
    When The test YML"<file>" is run
    Then Build status of "<job>" is "<status>"
    Examples:
      | file | job | status |
      | setUpMATLABTests.yml | Build without MATLAB | failure |
      | runMATLABCommandTests.yml | Build with invalid command | failure |

  Scenario Outline:  Check for job status
    When The test YML"<file>" is run
    Then Build status of "<job>" is "<status>"
    Examples:
      | file | job | status |
      | setUpMATLABTests.yml | User specified MATLAB release is picked | success |
      | runMATLABCommandTests.yml | Build with valid command | success |
