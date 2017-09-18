package com.epam.podcast.services;

import com.epam.podcast.beans.Podcast;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/podcasts")
@Api(value="podcasts", description="Podcasts services")
public class PodcastController
{
    private static final String WWW_EXAMPLE_COM = "www.example.com";
    private static final String EXAMPLE_TEXT = "example text";

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@PathVariable Long id, @RequestBody Podcast input) {
        Podcast result = new Podcast(id, "new Podcast", WWW_EXAMPLE_COM, "", EXAMPLE_TEXT);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Podcast input) {
        Podcast result = new Podcast(1l, "new Podcast1", WWW_EXAMPLE_COM, "", EXAMPLE_TEXT);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "View the podcast", response = Podcast.class)
    @RequestMapping(method = RequestMethod.GET)
    public Podcast get(@PathVariable Long id) {
        return new Podcast(id, "new Podcast", WWW_EXAMPLE_COM, "", EXAMPLE_TEXT);
    }
}
