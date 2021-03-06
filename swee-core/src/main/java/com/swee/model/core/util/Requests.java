package com.swee.model.core.util;

import javax.servlet.http.HttpServletRequest;
import com.swee.model.core.commonmodel.RequestTrack;
import lombok.experimental.UtilityClass;

/**
 * 工具类：简化通过RequestContextUtils获取HttpServletRequest进行操作的代码量
 *
 * @author Deolin
 */
@UtilityClass
public class Requests {

    private static final String REQUEST_TRACK = "com.swee.model.core.util.Requests.REQUEST_TRACK";

    public static HttpServletRequest request() {
        return RequestContextUtils.request();
    }

    public static void set(String key, Object value) {
        request().setAttribute(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) request().getAttribute(key);
    }

    public static void remove(String key) {
        request().removeAttribute(key);
    }

    /**
     * @param requestTrack 当前请求轨迹
     */
    public static void setRequestTrack(RequestTrack requestTrack) {
        request().setAttribute(REQUEST_TRACK, requestTrack);
    }

    /**
     * @return 当前请求轨迹
     */
    public static RequestTrack getRequestTrack() {
        return (RequestTrack) request().getAttribute(REQUEST_TRACK);
    }

    /**
     * @return 当前请求轨迹的标识
     */
    public static String getInsignia() {
        RequestTrack track = getRequestTrack();
        if (track == null) {
            return "";
        } else {
            return track.getInsignia();
        }
    }

}
