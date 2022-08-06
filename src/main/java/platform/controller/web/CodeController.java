package platform.controller.web;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import platform.domain.CodeResponse;
import platform.service.CodeService;

import java.io.*;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;


@Controller
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }


    @ResponseBody
    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String newCode() {
        //just another way
        return loadFileAsString(new ClassPathResource("templates/newCode.html"));
    }

    @CrossOrigin
    @GetMapping("/code/latest")
    public String getLatestCodes(Model model) {

        List<CodeResponse> codes = codeService.getLatestCodes();

        model.addAttribute("codes", codes);

        return "latestCodes";
    }

    @CrossOrigin
    @GetMapping("/code/{N}")
    public String getCodeById(@PathVariable(name = "N") String id, Model model) {

        CodeResponse code = codeService.getCodeById(id,true);

        model.addAttribute("code", code);
        return "showCode";
    }

    public static String loadFileAsString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
