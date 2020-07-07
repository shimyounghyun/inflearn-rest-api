package restapi.demo.events;


import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

// HATEOAS 버전 변경 ResourceSupport -> RepresentationModel
public class EventResource extends RepresentationModel {

    @JsonUnwrapped
    private Event event;

    public EventResource(Event event){
        this.event = event;
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }

    public Event getEvent() {
        return event;
    }
}
