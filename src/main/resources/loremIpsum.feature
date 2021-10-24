Feature: loremIpsum
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  #noinspection NonAsciiCharacters
  Scenario Outline: Verify that the word "рыба" correctly appears in the first paragraph ("Что такое Lorem Ipsum?")
    Given User opens '<homePage>' page
    When User clicks Russian language link
    Then User checks the word '<keyword>' appears in the first paragraph
    Examples:
      | homePage            | keyword |
      | https://lipsum.com/ | рыба    |

  #noinspection SpellCheckingInspection
  Scenario Outline: Verify that default setting result in text starting with Lorem ipsum
    Given User opens '<homePage>' page
    When User clicks Generate button
    Then User checks the first paragraph starts with '<text>'
    Examples:
      | homePage            | text                                                    |
      | https://lipsum.com/ | Lorem ipsum dolor sit amet, consectetur adipiscing elit |

  Scenario Outline: Verify that Lorem Ipsum is generated with correct size
    Given User opens '<homePage>' page
    And User clicks on '<type>'
    And User inputs '<number>' into the field
    When User clicks Generate button
    Then User checks the first paragraph has correct '<size>'
    Examples:
      | homePage            | type  | number | size |
      | https://lipsum.com/ | words | 10     | 10   |
      | https://lipsum.com/ | words | -1     | 5    |
      | https://lipsum.com/ | words | 0      | 5    |
      | https://lipsum.com/ | words | 5      | 5    |
      | https://lipsum.com/ | words | 20     | 20   |
      | https://lipsum.com/ | bytes | 4      | 4    |
      | https://lipsum.com/ | bytes | 0      | 5    |
      | https://lipsum.com/ | bytes | -1     | 5    |

  Scenario Outline: Verify the checkbox
    Given User opens '<homePage>' page
    And User clicks Start checkbox
    And User checks the checkbox is not selected
    When User clicks Generate button
    Then User checks the first paragraph does not start with '<text>'
    Examples:
      | homePage            | text        |
      | https://lipsum.com/ | Lorem ipsum |