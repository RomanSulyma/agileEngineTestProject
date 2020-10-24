package com.example.agileengine.agileengine.service;

import com.example.agileengine.agileengine.model.AuthorizeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private final RestTemplatesService restTemplatesService;

    /**
     * Authorize by key
     * @return
     * @throws Exception
     */
    public String authorize() throws Exception {

        final AuthorizeResponse authorizeModel = restTemplatesService.authCall();

        if (authorizeModel != null
                && authorizeModel.getAuth()
                && !StringUtils.isEmpty(authorizeModel.getToken())) {
            return String.format("Bearer %s", authorizeModel.getToken());
        } else {
            throw new Exception("Can't authorize");
        }
    }
}
