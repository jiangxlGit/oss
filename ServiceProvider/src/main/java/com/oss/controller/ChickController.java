
package com.oss.controller;

import com.oss.model.Chick;
import com.oss.services.ChickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChickController {

    @Autowired
    private ChickService chickService;

    @RequestMapping("/getChicks")
    public List<Chick> getChicks() {
        List<Chick> chicks = chickService.getChicks();
        String str = null;
//        str.charAt(1);
        return chicks;
    }

    @RequestMapping("/getChick/{weight}")
    public Chick getChick(@PathVariable("weight") int weight) {
        Chick chick = chickService.getChick(weight);
        return chick;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void save(@RequestBody Chick chick) {
        chickService.save(chick);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(@RequestBody Chick chick) {
        return chickService.update(chick);
    }

    @RequestMapping(value = "/delete/{weight}")
    public void delete(@PathVariable("weight") int weight) {
        chickService.delete(weight);
    }

}
