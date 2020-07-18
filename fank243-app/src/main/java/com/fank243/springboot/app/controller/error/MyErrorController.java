package com.fank243.springboot.app.controller.error;

import com.fank243.springboot.app.utils.WebUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 捕获Filter异常
 * 
 * @author FanWeiJie
 * @date 2019-10-23 16:02:37
 */
@RestController
public class MyErrorController extends BasicErrorController {

    public MyErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);

        Map<String, Object> errorAttributes = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));

        Map<String, Object> map;
        switch (status) {
            // 401
            case UNAUTHORIZED:
                map = buildResp(ResultInfo.unauthorized());
                break;
            // 403
            case FORBIDDEN:
                map = buildResp(ResultInfo.forbidden());
                break;
            // 404
            case NOT_FOUND:
                map = buildResp(ResultInfo.notFund());
                break;
            // 405
            case METHOD_NOT_ALLOWED:
                map = buildResp(ResultInfo.methodNotSupported());
                break;

            // default
            default:
                ResultInfo result;
                String message = String.valueOf(errorAttributes.get("message"));
                switch (message) {
                    case "AuthenticationException":
                        status = HttpStatus.UNAUTHORIZED;
                        result = ResultInfo.unauthorized("签名验证失败");
                        break;

                    default:
                        result = ResultInfo.exception();
                        break;
                }
                map = buildResp(result);
                break;
        }

        return new ResponseEntity<>(map, status);
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        switch (status) {
            // 401
            case UNAUTHORIZED:
                WebUtils.printJson(ResultInfo.unauthorized());
                break;
            // 403
            case FORBIDDEN:
                WebUtils.printJson(ResultInfo.forbidden());
                break;
            // 404
            case NOT_FOUND:
                WebUtils.printJson(ResultInfo.notFund());
                break;
            // 405
            case METHOD_NOT_ALLOWED:
                WebUtils.printJson(ResultInfo.methodNotSupported());
                break;

            // default
            default:
                WebUtils.printJson(ResultInfo.exception());
                break;
        }
        return null;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private Map<String, Object> buildResp(ResultInfo result) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        map.put("success", result.isSuccess());
        map.put("timestamp", System.currentTimeMillis());
        map.put("payload", result.getPayload());
        return map;
    }

}
