package platform.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import platform.database.repository.CodeRepository;
import platform.domain.AddCodeResponse;
import platform.database.Code;
import platform.domain.AddCodeRequest;
import platform.domain.CodeResponse;
import platform.error.NotFoundException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CodeServiceImpl implements CodeService {

    private static final Logger LOGGER = Logger.getLogger(CodeService.class.getSimpleName());
    private final CodeRepository repository;

    private static final Predicate<Code> RESTRICTIONS_VALIDATOR = c -> (c.getDate().plusSeconds(c.getTime()).isAfter(LocalDateTime.now())
            || c.getTime() == -1) && c.getViews() != 0;


    private static final Function<Code, CodeResponse> RESPONSE_FUNCTION = code -> {
        CodeResponse codeResponse = new CodeResponse();
        codeResponse.setCode(code.getContent());

        codeResponse.setTime(code.getTime() == null || code.getTime() <= 0? 0
                : code.getTime() - Duration.between(code.getDate(), LocalDateTime.now()).getSeconds());
        codeResponse.setViews(code.getViews() == null || code.getViews() <= 0 ? 0 : code.getViews());
        codeResponse.setDate(code.getDate());
        return codeResponse;
    };


    @Autowired
    public CodeServiceImpl(CodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AddCodeResponse addCode(AddCodeRequest request) {

        Code code = new Code();
        code.setId(UUID.randomUUID().toString());
        code.setContent(request.getCode());

        code.setTime(request.getTime() <= 0 ? -1 : request.getTime());
        code.setViews(request.getViews() <= 0 ? -1 : request.getViews());
        code.setDate(LocalDateTime.now());
        code = repository.save(code);

        LOGGER.log(Level.INFO,"saved: [{0}]",code);
        return new AddCodeResponse(String.valueOf(code.getId()));
    }

    @Override
    public CodeResponse getCodeById(String id,boolean forWeb) {
        return repository.findById(id)
                .map(c -> {
                            if (!RESTRICTIONS_VALIDATOR.test(c)) {
                                repository.delete(c);
                                throw new NotFoundException("Code not found/expired/max views reached.");
                            }

                            if (c.getViews() > 0) {
                                c.setViews(c.getViews() - 1);
                                c=repository.save(c);
                            }
                            if (forWeb)
                                c.setViews(c.getViews()+1);
                            return RESPONSE_FUNCTION.apply(c);
                        }
                )
                .orElseThrow(() -> new NotFoundException("Code not found/expired/max views reached."));
    }

    @Override
    public List<CodeResponse> getLatestCodes() {
        LOGGER.log(Level.INFO,"Getting latest codes.");
        return repository.getLatestCodes(PageRequest.ofSize(10))
                .stream()
                .map(RESPONSE_FUNCTION)
                .collect(Collectors.toList());
    }
}
