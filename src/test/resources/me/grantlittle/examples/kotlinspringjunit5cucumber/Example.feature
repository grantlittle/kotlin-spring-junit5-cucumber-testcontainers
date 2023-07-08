Feature: Spring Boot Application

  Scenario: Context loads correctly
    Given a spring boot application
    When the application starts
    Then the context should load successfully