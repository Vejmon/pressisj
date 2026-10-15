package no.vejmon.kunstner.config;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class RelativeHrefRepresentationProcessor implements RepresentationModelProcessor<RepresentationModel<?>> {

    @Override
    public RepresentationModel<?> process(RepresentationModel<?> model) {
        Links absoluteLinks = model.getLinks();
        model.removeLinks();
        absoluteLinks.forEach(link -> {
            // Strip scheme and authority to get relative path
            if (link.getHref().startsWith("http")) {
                URI uri = URI.create(link.getHref());
                String relativePath = uri.getPath();
                if (uri.getQuery() != null) {
                    relativePath += "?" + uri.getQuery();
                }
                model.add(Link.of(relativePath, link.getRel()));
            }
        });
        return model;
    }

}
