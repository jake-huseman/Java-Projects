package coms309;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController
{
    @RequestMapping(method = RequestMethod.GET, path = "/oops")
    public String triggerException()
    {
        throw new RuntimeException("uups an exception has occurred.");
    }
}
