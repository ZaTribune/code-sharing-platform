package platform.controller.api;

import org.springframework.web.bind.annotation.*;
import platform.domain.AddCodeRequest;
import platform.domain.AddCodeResponse;
import platform.domain.CodeResponse;
import platform.service.CodeService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class RestCodeController {


    private final CodeService codeService;

    public RestCodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/code/latest")
    public List<CodeResponse>getLatestCodes(){

        return codeService.getLatestCodes();
    }

    @GetMapping("/code/{N}")
    public CodeResponse getCodeById(@PathVariable(name = "N") String id){

        return codeService.getCodeById(id,false);
    }

    @PostMapping("/code/new")
    public AddCodeResponse newCode(@RequestBody AddCodeRequest addCodeRequest){

       return codeService.addCode(addCodeRequest);
    }

}
