package name.huhao.springbootdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
@Api(tags = "Home", description = "Demo Api")
public class HomeController {

    @GetMapping
    @ApiOperation(value = "Index")
    public String index() {
        return "Hello World";
    }
}
