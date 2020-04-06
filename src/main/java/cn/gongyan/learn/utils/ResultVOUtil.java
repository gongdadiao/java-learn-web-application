/***********************************************************
 * @Description : 成功或失败时的消息返回
 * @author      : 龚研
 * @date        : 2019-05-17 07:43
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.utils;


import cn.gongyan.learn.beans.vo.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Integer code, String msg, Object object) {
        return new ResultVO(code, msg, object);
    }

    public static ResultVO success(Object object) {
        return new ResultVO(0, "成功", object);
    }

    public static ResultVO success() {
        return new ResultVO(0, "成功", null);
    }


    public static ResultVO error(Integer code, String msg) {
        return new ResultVO(code, msg, null);
    }
}
