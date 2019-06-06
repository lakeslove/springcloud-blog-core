package com.lakeslove.blog.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "blog-register")
public interface RegisterClient {

  @PostMapping("sendVerificationCode")
  String sendVerificationCode(@RequestParam("email") String email);

  @PostMapping("valid")
  boolean valid(@RequestParam("email") String email, @RequestParam("code") String code);

}
