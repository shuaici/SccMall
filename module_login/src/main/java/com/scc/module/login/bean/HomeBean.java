package com.scc.module.login.bean;

import java.util.List;

public class HomeBean {


    /**
     * code : 200
     * msg : 成功
     * data : {"total":0,"list":[{"targetType":2,"target":{"id":1118,"gmtCreate":1636634649000,"gmtModified":1636634649000,"userId":20462842200525,"postUserId":21335363522731,"content":"哈哈哈","amount":5,"status":50,"user":{"id":20462842200525,"userNick":"猪猪小科比002","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20211105192414852_lxfjxjefu89qnz791oxu5mgzddhfx3.png","locked":false},"receiveUser":{"id":21335363522731,"userNick":"厦门大魔王","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210925174723520_t52inlfutkkzjoitmxawhsfjrb1dgb.png","locked":false},"statistic":{"gmtCreate":1636634649000,"gmtModified":1636634649000,"targetType":2,"targetUserId":20462842200525,"targetId":1118,"commentCount":0,"shareCount":0,"likeCount":0,"playCount":0}}},{"targetType":2,"target":{"id":1116,"gmtCreate":1636616549000,"gmtModified":1636616549000,"userId":21741418196614,"postUserId":21600999468991,"content":"给我一份小龙虾秘方","amount":10,"status":50,"user":{"id":21741418196614,"userNick":"少侠请留步","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210813190132824_oefeoedia0hledw9uspvstpf6zjrb0.png","locked":false},"receiveUser":{"id":21600999468991,"userNick":"郫都吃喝玩乐","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210420141046940_kiqpgeiujxuf9g0npcaibc7x0plrvg.png","locked":false},"statistic":{"gmtCreate":1636616549000,"gmtModified":1636616549000,"targetType":2,"targetUserId":21741418196614,"targetId":1116,"commentCount":0,"shareCount":0,"likeCount":0,"playCount":0}}},{"targetType":2,"target":{"id":1115,"gmtCreate":1636615449000,"gmtModified":1636615449000,"userId":21413026435097,"postUserId":21741418196614,"content":"12131","amount":1,"status":50,"user":{"id":21413026435097,"userNick":"创作背景2","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20211111114521813_2h7wisqta1hvattf1trgcieq336llp.jpg","locked":false},"receiveUser":{"id":21741418196614,"userNick":"少侠请留步","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210813190132824_oefeoedia0hledw9uspvstpf6zjrb0.png","locked":false},"statistic":{"gmtCreate":1636615449000,"gmtModified":1636615449000,"targetType":2,"targetUserId":21413026435097,"targetId":1115,"commentCount":0,"shareCount":0,"likeCount":0,"playCount":0}}},{"targetType":2,"target":{"id":1113,"gmtCreate":1636615364000,"gmtModified":1636615364000,"userId":21413026435097,"postUserId":21741418196614,"content":"Ooooo","amount":1,"status":50,"user":{"id":21413026435097,"userNick":"创作背景2","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20211111114521813_2h7wisqta1hvattf1trgcieq336llp.jpg","locked":false},"receiveUser":{"id":21741418196614,"userNick":"少侠请留步","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210813190132824_oefeoedia0hledw9uspvstpf6zjrb0.png","locked":false},"statistic":{"gmtCreate":1636615364000,"gmtModified":1636615364000,"targetType":2,"targetUserId":21413026435097,"targetId":1113,"commentCount":0,"shareCount":0,"likeCount":0,"playCount":0}}}],"hasMore":true}
     * success : true
     */

    private int code;
    private String msg;
    private DataBean data;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * total : 0
         * list : [{"targetType":2,"target":{"id":1118,"gmtCreate":1636634649000,"gmtModified":1636634649000,"userId":20462842200525,"postUserId":21335363522731,"content":"哈哈哈","amount":5,"status":50,"user":{"id":20462842200525,"userNick":"猪猪小科比002","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20211105192414852_lxfjxjefu89qnz791oxu5mgzddhfx3.png","locked":false},"receiveUser":{"id":21335363522731,"userNick":"厦门大魔王","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210925174723520_t52inlfutkkzjoitmxawhsfjrb1dgb.png","locked":false},"statistic":{"gmtCreate":1636634649000,"gmtModified":1636634649000,"targetType":2,"targetUserId":20462842200525,"targetId":1118,"commentCount":0,"shareCount":0,"likeCount":0,"playCount":0}}},{"targetType":2,"target":{"id":1116,"gmtCreate":1636616549000,"gmtModified":1636616549000,"userId":21741418196614,"postUserId":21600999468991,"content":"给我一份小龙虾秘方","amount":10,"status":50,"user":{"id":21741418196614,"userNick":"少侠请留步","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210813190132824_oefeoedia0hledw9uspvstpf6zjrb0.png","locked":false},"receiveUser":{"id":21600999468991,"userNick":"郫都吃喝玩乐","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210420141046940_kiqpgeiujxuf9g0npcaibc7x0plrvg.png","locked":false},"statistic":{"gmtCreate":1636616549000,"gmtModified":1636616549000,"targetType":2,"targetUserId":21741418196614,"targetId":1116,"commentCount":0,"shareCount":0,"likeCount":0,"playCount":0}}},{"targetType":2,"target":{"id":1115,"gmtCreate":1636615449000,"gmtModified":1636615449000,"userId":21413026435097,"postUserId":21741418196614,"content":"12131","amount":1,"status":50,"user":{"id":21413026435097,"userNick":"创作背景2","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20211111114521813_2h7wisqta1hvattf1trgcieq336llp.jpg","locked":false},"receiveUser":{"id":21741418196614,"userNick":"少侠请留步","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210813190132824_oefeoedia0hledw9uspvstpf6zjrb0.png","locked":false},"statistic":{"gmtCreate":1636615449000,"gmtModified":1636615449000,"targetType":2,"targetUserId":21413026435097,"targetId":1115,"commentCount":0,"shareCount":0,"likeCount":0,"playCount":0}}},{"targetType":2,"target":{"id":1113,"gmtCreate":1636615364000,"gmtModified":1636615364000,"userId":21413026435097,"postUserId":21741418196614,"content":"Ooooo","amount":1,"status":50,"user":{"id":21413026435097,"userNick":"创作背景2","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20211111114521813_2h7wisqta1hvattf1trgcieq336llp.jpg","locked":false},"receiveUser":{"id":21741418196614,"userNick":"少侠请留步","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210813190132824_oefeoedia0hledw9uspvstpf6zjrb0.png","locked":false},"statistic":{"gmtCreate":1636615364000,"gmtModified":1636615364000,"targetType":2,"targetUserId":21413026435097,"targetId":1113,"commentCount":0,"shareCount":0,"likeCount":0,"playCount":0}}}]
         * hasMore : true
         */

        private int total;
        private boolean hasMore;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public boolean isHasMore() {
            return hasMore;
        }

        public void setHasMore(boolean hasMore) {
            this.hasMore = hasMore;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * targetType : 2
             * target : {"id":1118,"gmtCreate":1636634649000,"gmtModified":1636634649000,"userId":20462842200525,"postUserId":21335363522731,"content":"哈哈哈","amount":5,"status":50,"user":{"id":20462842200525,"userNick":"猪猪小科比002","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20211105192414852_lxfjxjefu89qnz791oxu5mgzddhfx3.png","locked":false},"receiveUser":{"id":21335363522731,"userNick":"厦门大魔王","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210925174723520_t52inlfutkkzjoitmxawhsfjrb1dgb.png","locked":false},"statistic":{"gmtCreate":1636634649000,"gmtModified":1636634649000,"targetType":2,"targetUserId":20462842200525,"targetId":1118,"commentCount":0,"shareCount":0,"likeCount":0,"playCount":0}}
             */

            private int targetType;
            private TargetBean target;

            public int getTargetType() {
                return targetType;
            }

            public void setTargetType(int targetType) {
                this.targetType = targetType;
            }

            public TargetBean getTarget() {
                return target;
            }

            public void setTarget(TargetBean target) {
                this.target = target;
            }

            public static class TargetBean {
                /**
                 * id : 1118
                 * gmtCreate : 1636634649000
                 * gmtModified : 1636634649000
                 * userId : 20462842200525
                 * postUserId : 21335363522731
                 * content : 哈哈哈
                 * amount : 5
                 * status : 50
                 * user : {"id":20462842200525,"userNick":"猪猪小科比002","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20211105192414852_lxfjxjefu89qnz791oxu5mgzddhfx3.png","locked":false}
                 * receiveUser : {"id":21335363522731,"userNick":"厦门大魔王","avatar":"https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210925174723520_t52inlfutkkzjoitmxawhsfjrb1dgb.png","locked":false}
                 * statistic : {"gmtCreate":1636634649000,"gmtModified":1636634649000,"targetType":2,"targetUserId":20462842200525,"targetId":1118,"commentCount":0,"shareCount":0,"likeCount":0,"playCount":0}
                 */

                private int id;
                private long gmtCreate;
                private long gmtModified;
                private long userId;
                private long postUserId;
                private String content;
                private int amount;
                private int status;
                private UserBean user;
                private ReceiveUserBean receiveUser;
                private StatisticBean statistic;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public long getGmtCreate() {
                    return gmtCreate;
                }

                public void setGmtCreate(long gmtCreate) {
                    this.gmtCreate = gmtCreate;
                }

                public long getGmtModified() {
                    return gmtModified;
                }

                public void setGmtModified(long gmtModified) {
                    this.gmtModified = gmtModified;
                }

                public long getUserId() {
                    return userId;
                }

                public void setUserId(long userId) {
                    this.userId = userId;
                }

                public long getPostUserId() {
                    return postUserId;
                }

                public void setPostUserId(long postUserId) {
                    this.postUserId = postUserId;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public ReceiveUserBean getReceiveUser() {
                    return receiveUser;
                }

                public void setReceiveUser(ReceiveUserBean receiveUser) {
                    this.receiveUser = receiveUser;
                }

                public StatisticBean getStatistic() {
                    return statistic;
                }

                public void setStatistic(StatisticBean statistic) {
                    this.statistic = statistic;
                }

                public static class UserBean {
                    /**
                     * id : 20462842200525
                     * userNick : 猪猪小科比002
                     * avatar : https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20211105192414852_lxfjxjefu89qnz791oxu5mgzddhfx3.png
                     * locked : false
                     */

                    private long id;
                    private String userNick;
                    private String avatar;
                    private boolean locked;

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public String getUserNick() {
                        return userNick;
                    }

                    public void setUserNick(String userNick) {
                        this.userNick = userNick;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public boolean isLocked() {
                        return locked;
                    }

                    public void setLocked(boolean locked) {
                        this.locked = locked;
                    }
                }

                public static class ReceiveUserBean {
                    /**
                     * id : 21335363522731
                     * userNick : 厦门大魔王
                     * avatar : https://rich-media-resource-pub.nos-jd.163yun.com/media_pic_20210925174723520_t52inlfutkkzjoitmxawhsfjrb1dgb.png
                     * locked : false
                     */

                    private long id;
                    private String userNick;
                    private String avatar;
                    private boolean locked;

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public String getUserNick() {
                        return userNick;
                    }

                    public void setUserNick(String userNick) {
                        this.userNick = userNick;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public boolean isLocked() {
                        return locked;
                    }

                    public void setLocked(boolean locked) {
                        this.locked = locked;
                    }
                }

                public static class StatisticBean {
                    /**
                     * gmtCreate : 1636634649000
                     * gmtModified : 1636634649000
                     * targetType : 2
                     * targetUserId : 20462842200525
                     * targetId : 1118
                     * commentCount : 0
                     * shareCount : 0
                     * likeCount : 0
                     * playCount : 0
                     */

                    private long gmtCreate;
                    private long gmtModified;
                    private int targetType;
                    private long targetUserId;
                    private int targetId;
                    private int commentCount;
                    private int shareCount;
                    private int likeCount;
                    private int playCount;

                    public long getGmtCreate() {
                        return gmtCreate;
                    }

                    public void setGmtCreate(long gmtCreate) {
                        this.gmtCreate = gmtCreate;
                    }

                    public long getGmtModified() {
                        return gmtModified;
                    }

                    public void setGmtModified(long gmtModified) {
                        this.gmtModified = gmtModified;
                    }

                    public int getTargetType() {
                        return targetType;
                    }

                    public void setTargetType(int targetType) {
                        this.targetType = targetType;
                    }

                    public long getTargetUserId() {
                        return targetUserId;
                    }

                    public void setTargetUserId(long targetUserId) {
                        this.targetUserId = targetUserId;
                    }

                    public int getTargetId() {
                        return targetId;
                    }

                    public void setTargetId(int targetId) {
                        this.targetId = targetId;
                    }

                    public int getCommentCount() {
                        return commentCount;
                    }

                    public void setCommentCount(int commentCount) {
                        this.commentCount = commentCount;
                    }

                    public int getShareCount() {
                        return shareCount;
                    }

                    public void setShareCount(int shareCount) {
                        this.shareCount = shareCount;
                    }

                    public int getLikeCount() {
                        return likeCount;
                    }

                    public void setLikeCount(int likeCount) {
                        this.likeCount = likeCount;
                    }

                    public int getPlayCount() {
                        return playCount;
                    }

                    public void setPlayCount(int playCount) {
                        this.playCount = playCount;
                    }
                }
            }
        }
    }
}
