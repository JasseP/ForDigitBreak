package ru.digitbreak.userservice.web.proxy;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitbreak.library.entity.Simple;
import ru.digitbreak.library.entity.api.IGenericProxyAPI;


@RestController
@RequestMapping("/api/simples")
public class SimpleProxyResource extends AbstractProxyResource<Simple> {

    {
        log= LoggerFactory.getLogger(SimpleProxyResource.class);
        clazz = Simple.class;
    }

    public SimpleProxyResource(IGenericProxyAPI<Simple> proxyAPI) {
        super(proxyAPI);
    }
}
