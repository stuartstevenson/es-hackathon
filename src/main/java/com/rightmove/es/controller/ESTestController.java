package com.rightmove.es.controller;

import com.rightmove.es.dataprovider.PropertyDataProvider;
import com.rightmove.es.domain.Property;
import com.rightmove.es.repositories.PropertyRepository;
import com.rightmove.es.service.PropertyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class ESTestController {

    private Logger log = Logger.getLogger(ESTestController.class);

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyService propertyService;
	@Autowired
	private PropertyDataProvider propertyDataProvider;

    @RequestMapping("/es-test")
    public String showESTestPage(final ModelMap modelMap) {

        indexDummyData();

        modelMap.addAttribute("result",searchDummyData());

        log.debug("estest");
        return "esTest";
    }

    private String searchDummyData() {
       Page<Property> properties = propertyRepository.findAll(new PageRequest(0, 10));

       return properties.getContent().toString();
    }

    private void indexDummyData() {
		Collection<Property> properties = propertyDataProvider.listAll();
		propertyService.indexProperties(properties);
    }


}
