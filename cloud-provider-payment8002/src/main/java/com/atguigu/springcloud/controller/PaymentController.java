package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("插入数据的ID:\t" + payment.getId());
        log.info("插入结果:\t" +result);
        if(result>0) {
            return new CommonResult(200,"插入数据库成功,serverport"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }
    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id ){
        Payment payment=paymentService.getPaymentById(id);
        if (payment!=null){
            return new CommonResult(200,"查询成功,serverport"+serverPort,payment);
        }else{
            return new CommonResult(44,"查询失败没有对应的ID"+id,null);
        }
    }

}
