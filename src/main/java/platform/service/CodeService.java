package platform.service;

import platform.domain.AddCodeResponse;
import platform.domain.AddCodeRequest;
import platform.domain.CodeResponse;

import java.util.List;

public interface CodeService {

    CodeResponse getCodeById(String id,boolean forWeb);

    List<CodeResponse>getLatestCodes();

    AddCodeResponse addCode(AddCodeRequest request);

}
