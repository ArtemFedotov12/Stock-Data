package com.start.stockdata.rest.controller.global;

import com.start.stockdata.api.StockGlobalController;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.rest.response.LongResponse;
import com.start.stockdata.service.global.GlobalService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/*@SwaggerDefinition(securityDefinition = @SecurityDefinition(
        apiKeyAuthDefinitions = {
                @ApiKeyAuthDefinition(key = "custom",
                        name = "Authorization",
                        in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER,
                        description = "Bearer Authentication")}))*/
public abstract class AbstractStockGlobalController<
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        S extends GlobalService<RQ, RS, Long>
        > implements StockGlobalController<RQ, RS> {

    protected final S service;

    public AbstractStockGlobalController(S service) {
        this.service = service;
    }

    @ApiOperation(
            value = "Add entity",
            notes = "Method allow to add entity"
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RS> save(@Valid @RequestBody final RQ requestDto) {
        return new ResponseEntity<>(service.save(requestDto), HttpStatus.OK);
    }


    @ApiOperation(
            value = "Change entity",
            notes = "Method allows only changing the entity"
    )
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RS> update(@PathVariable("id") final Long id,
                                     @Valid @RequestBody final RQ requestDto) {
        return new ResponseEntity<>(service.update(id, requestDto), HttpStatus.OK);
    }


    @ApiOperation(
            value = "Delete entity",
            notes = "Removing an entity by a given unique identifier"
    )
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RS> deleteById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }


    @ApiOperation(
            value = "Find entity by id",
            notes = "The method allows to search for an entity by a unique identifier"
    )
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RS> findById(@PathVariable("id") Long id) {
        //"src/main/java/com/start/stockdata/utils/scheme2.xml"
        try {
            System.out.println("111");
            File file = readXsdFile();
            System.out.println("222");
           /* File temp = File.createTempFile("bidorbuy", ".xml");
            FileUtils.copyURLToFile(
                    new URL("https://my.mysi.app/download/tradefeed/330ecc10b65311eaa85e31d36b34d064"),
                    temp);*/
            String content = FileUtils.readFileToString(file, "UTF-8");
            System.out.println(content);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    private File readXsdFile() throws IOException {
        File file = File.createTempFile("birorbuy", ".xsd");
        file.deleteOnExit();
        InputStream in = new FileInputStream(
                new File("src/main/resources/xsd/bidorbuy.xsd"));
        OutputStream outStream = new FileOutputStream(file);
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            return file;
    }


    @ApiOperation(
            value = "Find all entities",
            notes = "Find all entities of the specified type"
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RS>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Amount of entities",
            notes = "Count amount of entities of the specified type"
    )
    @GetMapping(value = "count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LongResponse> count(
            @RequestParam(value = "includeDeleted", defaultValue = "false") final boolean includeDeleted) {
        return new ResponseEntity<>(LongResponse.of(service.count(includeDeleted)), HttpStatus.OK);
    }


    /*    @ApiOperation(
            value = "Change entity",
            notes = "Method allow only to change entity."
    )
    @PatchMapping
    public ResponseEntity<RS> update(@PathVariable("id") final Long id,
                                     @Valid @RequestBody final RQ requestDto) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }*/

}
