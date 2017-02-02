package com.dmmsoft.Survey.Mapper;

import com.dmmsoft.Survey.Exception.SurveyCreationException;
import com.dmmsoft.Survey.Survey;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JSONMapperTest extends TestCase {

    public JSONMapperTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(JSONMapperTest.class);
    }

    public void testGetSurveyFromJsonValidJsonWillReturnSurvey() {
        String jsonString = "{  \"id\":123,  \"questions\":[  {  \"id\":123,  \"question\":\"Jaksięnazywasz?\",  \"answers\":[  {  \"id\":1,  \"answer\":\"niewiem\",  \"isCorrect\":false  },  {  \"id\":2,  \"answer\":\"niewiem\",  \"isCorrect\":true  }  ]  }  ]  }";
        JSONMapper jsonMapper = new JSONMapper(jsonString);
        Survey survey = jsonMapper.getSurveyFromJson();
        assertTrue(survey != null);
    }

    public void testGetSurveyFromJsonInvalidJsonWillThrowException() {
        String invalidJson = "abc";
        try {
            JSONMapper jsonMapper = new JSONMapper(invalidJson);
            jsonMapper.getSurveyFromJson();
            fail();
        } catch (SurveyCreationException exception) {
            assertNotNull(exception.getMessage());
        }
    }
}
