package com.epam.podcast.services;

import com.epam.podcast.beans.Podcast;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/podcasts")
@Api(value="podcasts", description="Podcasts services")
public class PodcastController {
    private static final String WWW_EXAMPLE_COM = "www.example.com";
    private static final String EXAMPLE_TEXT = "example text";

    @ApiOperation(value = "Add the podcast")
    @RequestMapping(value="/podcasts/{id}", method = RequestMethod.POST)
    public ResponseEntity add(@PathVariable Long id,@RequestBody Podcast input) {
        if (id == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (idIsNotInSystem()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Podcast result = new Podcast(id, "new Podcast", WWW_EXAMPLE_COM, "", EXAMPLE_TEXT);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update the podcast")
    @RequestMapping(value="/podcasts/", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Podcast input) {
        Long id = input.getId();
        if (id == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Podcast result = new Podcast(id, "Updated Podcast", WWW_EXAMPLE_COM, "", EXAMPLE_TEXT);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "View the podcast", response = Podcast.class)
    @RequestMapping(value = "/podcast/{id}", method = RequestMethod.GET)
    public Podcast get(@PathVariable Long id) {
        return new Podcast(id, "Some Podcast", WWW_EXAMPLE_COM, "", EXAMPLE_TEXT);
    }

    @ApiOperation(value = "Delete the podcast")
    @RequestMapping(value = "/podcast/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (idIsNotInSystem()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    private boolean idIsNotInSystem() {
        //Pseudo-method
        return false;
    }
}
