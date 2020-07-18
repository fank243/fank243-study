package com.fank243.springboot.core.enums;
    
    import lombok.Getter;
    
    /**
     * 用户操作事件类型
     * 
     * @author FanWeiJie
     * @date 2019-11-05 18:02:07
     */
    @Getter
    public enum UserEventType {
    
        /** 登陆事件 **/
        LOGIN("登录"),
    
        /** 注册事件 **/
        REGISTER("注册"),
    
        FOLLOW("关注"),
    
        FOLLOW_CANCEL("取消关注"),
    
        FAVORITE("添加收藏"),
    
        FAVORITE_CANCEL("取消收藏"),
    
        LIKE("点赞"),
    
        LIKE_CANCEL("取消点赞"),
    
        COMMENT("发表评论"),
    
        COMMENT_LIKE("点赞评论"),
    
        COMMENT_LIKE_CANCEL("取消点赞评论"),
    
        USER_INFO_MODIFY("用户修改资料"),
    
        ;
    
        UserEventType(String desc) {
            this.desc = desc;
        }
    
        private String desc;
    
        public static UserEventType getEnum(String type) {
            UserEventType[] values = UserEventType.values();
            for (UserEventType eventType : values) {
                if (eventType.name().equalsIgnoreCase(type)) {
                    return eventType;
                }
            }
            return null;
        }
    }
