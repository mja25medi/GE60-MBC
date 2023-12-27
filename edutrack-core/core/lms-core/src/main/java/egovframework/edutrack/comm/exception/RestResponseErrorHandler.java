package egovframework.edutrack.comm.exception;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestResponseErrorHandler implements ResponseErrorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger("tc");

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode() != HttpStatus.OK) {
            StringBuffer logStr = new StringBuffer();
            logStr.append("---- REST API Response Error ---- \n");
            logStr.append("Status code : " + clientHttpResponse.getStatusCode() + "\n");
            logStr.append("Response : " + clientHttpResponse.getStatusText() + "\n");
            logStr.append(IOUtils.toString(new InputStreamReader(clientHttpResponse.getBody(), "UTF-8")));

            LOGGER.error(logStr.toString());

            if (clientHttpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                LOGGER.debug("Call returned a error 403 forbidden response ");
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
            throw new IOException();
        }
    }
}
