package top.catalinali.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <pre>
 * Description: wechat OAuth2.0
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/2/1
 * </pre>
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code,@RequestParam("state") String state){
        log.info("auth开始了。。。。");
        log.info("code={}",code);
        log.info("state={}",state);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx6b550b2970a8a11f&secret=1fcdf43ae679cbf7f3d3a5aa306b293a&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        log.info("result={}",result);
    }
}
