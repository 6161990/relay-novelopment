package example.api;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-01-14T15:53:03.353172+09:00[Asia/Seoul]")
@RestController
public class AnnyengApiController implements AnnyengApi {

    private final AnnyengApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public AnnyengApiController(AnnyengApiDelegate delegate) {
        this.delegate = delegate;
    }
    public ResponseEntity<String> hello() {
        return delegate.hello();
    }

}
