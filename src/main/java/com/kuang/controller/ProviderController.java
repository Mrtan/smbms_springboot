package com.kuang.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kuang.pojo.Provider;
import com.kuang.service.ProviderService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("providers")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @GetMapping("/all")
    public List<Provider> getAllProviders() {
        return providerService.list();
    }

    @GetMapping("{id}")
    public Provider getProviderById(@PathVariable Integer id) {
        return providerService.getById(id);
    }

    @PostMapping
    public Boolean addProvider(@RequestBody Provider provider) {
        return providerService.save(provider);
    }

    @PutMapping
    public Boolean updateProvider(@RequestBody Provider provider) {
        return providerService.updateById(provider);
    }

    @DeleteMapping("{id}")
    public Boolean deleteProviderById(@PathVariable Integer id) {
        return providerService.removeById(id);
    }

    @GetMapping
    public IPage getUsersByCondition(@RequestParam Integer currentPage, @RequestParam Integer pageSize,
                                     @RequestParam(required = false) String proCode, @RequestParam(required = false) String proName) {
        List<Map> conditions = new ArrayList<>();
        if (Strings.isNotEmpty(proCode)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("type", "like");
            map.put("field", "proCode");
            map.put("value", proCode);
            conditions.add(map);
        }
        if (Strings.isNotEmpty(proName)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("type", "like");
            map.put("field", "proName");
            map.put("value", proName);
            conditions.add(map);
        }
        return providerService.getProvidersByCondition(currentPage, pageSize, conditions);
    }
}
