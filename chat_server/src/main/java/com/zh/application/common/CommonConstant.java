package com.zh.application.common;

/**
 * @author
 * @date 2019-07-29
 * @description 通用常量
 */
public interface CommonConstant {
    public static final char COLON = ':';
    public static final String COLON_S = ":";
    public static final char SEMI_COLON = ';';
    public static final String SEMI_COLON_S = ";";
    public static final char BLANK_SPACE = ' ';
    public static final String BLANK_SPACE_S = " ";
    public static final char COMMA = ',';
    public static final String COMMA_S = ",";
    public static final char VERTICALBAR = '|';
    public static final char SLASH = '/';
    public static final char UNDERLINE = '_';
    public static final Long ZERO = 0l;

    public static final Byte Y = 1; // 是
    public static final Byte N = 0; // 否

    /**
     * cresteBy(管理员执行)
     */
    public static final Long CREATE_BY = 1L;

    /**
     * 登录标志
     */
    public static class Login {
        private Login() {
            throw new IllegalStateException("Login class");
        }

        public static final String LOGIN_SUCCESS_MSG = "登录成功";
        public static final String LOGIN_FAIL_MSG = "登录失败";

        public static final String LOGIN_REDIRECT_URI = "main";// 登录成功重定向url
    }

    /***
     * 通用操作结果状态
     */
    public static class ResultState {
        private ResultState() {
            throw new IllegalStateException("ResultState class");
        }

        public static final boolean SUCCESS = true; // 成功
        public static final boolean FAIL = false; // 失败
    }

    /**
     * 删除标志
     */
    public static class DelFlag {
        private DelFlag() {
            throw new IllegalStateException("DelFlag class");
        }

        public static final Byte Y = 1; // 删除
        public static final Byte N = 0; // 未删除
    }

    /**
     * 生效标志
     */
    public static class EffectiveFlag {
        private EffectiveFlag() {
            throw new IllegalStateException("EffectiveFlag class");
        }

        public static final Byte Y = 1; // 生效
        public static final Byte N = 0; // 失效
    }

    /**
     * 冻结标志
     *
     */
    public static class FreezeFlag {
        private FreezeFlag() {
            throw new IllegalStateException("FreezeFlag class");
        }

        public static final Byte Y = 1; // 冻结
        public static final Byte N = 0; // 解冻
    }



    /**
     * 加密算法
     */
    public static class SecretMethod {
        private SecretMethod() {
            throw new IllegalStateException("SecretMethod class");
        }

        public static final String PBE_MD5_DES = "PBEWITHMD5ANDDES";
        public static final String PBE_MD5_TRIPLEDES = "PBEWITHMD5ANDTRIPLEDES";
        public static final String SPBE_HA_DESEDE = "PBEWITHSHAANDDESEDE";
        public static final String PBE_SHA1_RC2_40 = "PBEWITHSHA1ANDRC2_40";
        public static final String PBKDF2_HMACSHA1 = "PBKDF2WITHHMACSHA1";
    }

    /** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;

    /** 访问权限认证未通过 510 */
    public static final Integer SC_JEECG_NO_AUTHZ = 510;

    /**
     * 权限不足
     */
    public static final Integer SC_USR_LMT = 401;

    public static final String SC_USR_LMT_MSG = "权限不足或者认证无效";

    public static final String REFRESH_APPID_FAIL_MSG = "token刷新失败,appid无效";

    public static final String REFRESH_TOKEN_FAIL_MSG = "token刷新失败,refresh_token无效";

    public static final String LOGOUT_SUCCESS = "注销成功";

    public static final String LOGOUT_FAIL = "注销失败";

    /**
     * token错误
     */
    public static final Integer TOKEN_MISTAKE = 501;

    public static final String TOKEN_MISTAKE_MSG = "认证无效";

    /**
     * token错误
     */
    public static final Integer TOKEN_MISSING = 502;

    public static final String TOKEN_MISSING_MSG = "token不能为空";

    /** 登录用户拥有角色缓存KEY前缀 */
    public static String LOGIN_USER_CACHERULES_ROLE = "loginUser_cacheRules::Roles_";
    /** 登录用户拥有权限缓存KEY前缀 */
    public static String LOGIN_USER_CACHERULES_PERMISSION = "loginUser_cacheRules::Permissions_";
    /** 登录用户令牌缓存KEY前缀 */
    public static final int TOKEN_EXPIRE_TIME = 3600; // 3600秒即是一小时

    public static final String PREFIX_USER_TOKEN = "PREFIX_USER_TOKEN_";



    /**
     *
     * @author magic
     *
     */
    public static class ControllerResult {
        private ControllerResult() {
            throw new IllegalStateException("ControllerResult class");
        }

        public static final String ADD_ERR_MSG = "添加失败,请联系管理员!";
        public static final String UPDATE_ERR_MSG = "更新失败,请联系管理员!";
        public static final String DELETE_ERROR_MSG = "删除失败,请联系管理员!";
        public static final String FIND_ERROR_MSG = "查询失败,请联系管理员!";
        public static final String OTHER_MSG = "未知错误,请联系管理员!";

        public static final String SUCESS = "操作成功!";
        public static final String FAIL = "操作失败,请联系管理员!";

    }

}
