package com.ledlampapi.proxies;

import com.ledlampapi.entity.request.ChangeColorRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="esp32", url = "http://172.20.10.3")
public interface ESP32Server {
    @RequestMapping(method = RequestMethod.POST, value = "/changeColor")
    void setColor(String jsonColor);
}
