package cn.ppl.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccessFilter extends ZuulFilter {

    private Logger logger  = LoggerFactory.getLogger(AccessFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.debug("收到IP:{},seng {} request to {}",getIPAddr(request),request.getMethod(),request.getRequestURL().toString());
        String data = getData(request);

//        if (StringUtils.isBlank(data)){
//            getContextForError(context);
//            return null;
//        }
        //校验时间戳
        //校验签名
        //token
        String accessToken = request.getParameter("accessToken");
        if (StringUtils.isBlank(accessToken)){
            logger.debug("token不能为空");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            return null;
        }

        logger.debug("=================token 校验通过======================");
        return null;
    }
    private RequestContext getContextForError(RequestContext ctx) {
        RequestContext requestContext = ctx;
        requestContext.setResponseBody(null);
        requestContext.setSendZuulResponse(false);
        requestContext.getResponse().setContentType("text/html;charset=UTF-8");
        requestContext.getResponse().setContentType(String.valueOf(MediaType.APPLICATION_JSON));
        return requestContext;
    }

    /**
     * 获取请求参数
     * @param request
     * @return
     */
    private String getData(HttpServletRequest request) {
        String data = "";
        String line;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            while (null != (line = reader.readLine())){
                sb.append(line);
            }
            data = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String getIPAddr(HttpServletRequest request) {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
    }
}
