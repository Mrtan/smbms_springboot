package com.kuang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kuang.pojo.Bill;
import com.kuang.service.BillService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("{id}")
    public Bill getBillById(@PathVariable Integer id) {
        return billService.getBillById(id);
    }

    @PostMapping
    public Boolean addBill(@RequestBody Bill bill) {
        return billService.save(bill);
    }

    @PutMapping()
    public Boolean updateBillById(@RequestBody Bill bill) {
        return billService.updateById(bill);
    }

    @DeleteMapping("{id}")
    public Boolean deleteBill(@PathVariable Integer id) {
        return billService.removeById(id);
    }

    @GetMapping
    public IPage getBillByCondition(@RequestParam Integer currentPage, @RequestParam Integer pageSize,
                                     @RequestParam(required = false) String productName, @RequestParam(required = false) String providerId,
                                     @RequestParam(required = false) String isPayment) {
        List<Map> conditions = new ArrayList<>();
        if (Strings.isNotEmpty(productName)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("type", "like");
            map.put("field", "productName");
            map.put("value", productName);
            conditions.add(map);
        }
        if (Strings.isNotEmpty(providerId) && !"0".equals(providerId)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("type", "equal");
            map.put("field", "providerId");
            map.put("value", providerId);
            conditions.add(map);
        }
        if (Strings.isNotEmpty(isPayment) && !"0".equals(isPayment)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("type", "equal");
            map.put("field", "isPayment");
            map.put("value", isPayment);
            conditions.add(map);
        }
        return billService.getBillByCondition(currentPage, pageSize, conditions);
    }
}
