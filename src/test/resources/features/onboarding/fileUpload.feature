@fileUpload
Feature: File Upload during Onboarding

  #Background:
    #Given the user is on the file upload page after successful registration

  Scenario: Open file upload window
    When the user clicks the "Upload PDF" button on the file upload page
    Then the user should see the window for uploading files
    
  Scenario Outline: File upload validations
 	  When the user uploads "<fileKey>" file
  	Then the user should see the correct result for "<fileKey>"

  Examples:
    | fileKey       | 
    | invalidType   | 
    | largePDF      | 
    | validFile     | 

  Scenario: Change the uploaded file
    Given a valid PDF file has been uploaded
    When the user clicks the "Change file" button on the file upload page
    Then the "Upload PDF" button should be displayed

  Scenario: Analyze uploaded PDF file
    Given a valid PDF file has been uploaded
    When the user clicks the "Analyze Report" button on the file upload page
    Then the file should be analyzed successfully
    
    

    
