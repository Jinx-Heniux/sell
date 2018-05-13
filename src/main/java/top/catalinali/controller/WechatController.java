package top.catalinali.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.catalinali.config.ProjectUrlConfig;
import top.catalinali.enums.ResultEnum;
import top.catalinali.exception.SellException;

import java.net.URLEncoder;

/**
 * <pre>
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/2/2
 * </pre>
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

   @Autowired
    private WxMpService wxMpService;

   @Autowired
    private WxMpService wxOpenService;

   @Autowired
   private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl) {
        String url = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userinfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        return "redirect:"+redirectUrl;
    }

    @GetMapping("/userinfo")
    public String userinfo(@RequestParam("code")String code,
                          @RequestParam("state")String returnUrl){
        WxMpOAuth2AccessToken auth2AccessToken = new WxMpOAuth2AccessToken();
        try {
           auth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId = auth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid="+openId;
    }

    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl")String returnUrl) {
        String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserinfo";
        String redirectUrl = wxOpenService.oauth2buildAuthorizationUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        return "redirect:"+redirectUrl;
    }

    @GetMapping("/qrUserinfo")
    public String qrUserinfo(@RequestParam("code")String code,
                           @RequestParam("state")String returnUrl){
        WxMpOAuth2AccessToken auth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            auth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId = auth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid="+openId;
    }

}
