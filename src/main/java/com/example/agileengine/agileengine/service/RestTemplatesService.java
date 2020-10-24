package com.example.agileengine.agileengine.service;

import com.example.agileengine.agileengine.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplatesService {

    @Value("${base.api}")
    private String baseAPI;

    @Value("${api.key}")
    private String apiKey;

    /**
     * Call authorization API
     * @return - Authorization response model
     * @throws Exception
     */
    AuthorizeResponse authCall() throws Exception {

        final RestTemplate restTemplate = new RestTemplate();
        final AuthorizeBody authorizeBody = new AuthorizeBody(apiKey);

        final String uri = baseAPI.concat("/auth");

        final ResponseEntity<AuthorizeResponse> authResponse = restTemplate.postForEntity(
                uri, authorizeBody, AuthorizeResponse.class);

        if (authResponse.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Service unavailable now!");
        }

        if(authResponse.hasBody()) {
            return authResponse.getBody();
        } else {
            throw new Exception("Invalid response");
        }
    }

    /**
     * Get pictures list by page
     * @param page
     * @param token
     * @return
     * @throws Exception
     */
    PhotoResponse getPicturesByPage(final Long page, final String token) throws Exception {

        final RestTemplate restTemplate = new RestTemplate();
        final String uri = baseAPI.concat("/images?page=").concat(page.toString());

        final ResponseEntity<PhotoResponse> photoResponse =
                restTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        getAuthorizationHeaderEntity(token),
                        PhotoResponse.class);

        validateResponse(photoResponse);

        return photoResponse.getBody();
    }

    /**
     * Get detailed picture by photoId
     * @param token
     * @param photoId
     * @return
     * @throws Exception
     */
    DetailedPicture getDetailedPicture(final String token, String photoId) throws Exception {

        final RestTemplate restTemplate = new RestTemplate();
        final String uri = baseAPI.concat("/images/").concat(photoId);

        final ResponseEntity<DetailedPicture> photoResponse =
                restTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        getAuthorizationHeaderEntity(token),
                        DetailedPicture.class);

        validateResponse(photoResponse);

        return photoResponse.getBody();
    }

    /**
     * Set token header to Entity
     * @param token
     * @return
     */
    private HttpEntity getAuthorizationHeaderEntity(final String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        return new HttpEntity(headers);
    }

    /**
     * Validate response of API
     * @param entity - Entity for validation
     * @param <T>
     * @throws Exception
     */
    private  <T> void validateResponse(ResponseEntity<T> entity) throws Exception {

        if (entity.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Service unavailable now!");
        }

        if(!entity.hasBody()) {
            throw new Exception("Invalid response");
        }
    }

}
