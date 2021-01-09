package com.ledlampapi.proxies;

import com.ledlampapi.entity.request.ChangeColorRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="esp32", url = "TODO URL du server ")
public interface ESP32Server {
    @RequestMapping(method = RequestMethod.POST, value = "/search.php?key="+pk+"&format=json")
    List<LocationIQAPIResponse> getLatLongTrash(@RequestParam("q") String address);
}
