package example.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * A delegate to be called by the {@link AnnyengApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-01-14T15:53:03.353172+09:00[Asia/Seoul]")
public interface AnnyengApiDelegate {

    /**
     * @see AnnyengApi#hello
     */
    ResponseEntity<String> hello();

}
