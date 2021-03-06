package wcsdata.xmen.entity.ids;

import wcsdata.xmen.entity.CerebookUser;

import java.io.Serializable;

public class CerebookUserFriendsId implements Serializable {
    private CerebookUser originatedUser;
    private CerebookUser friend;

    public CerebookUserFriendsId() {}
    public CerebookUserFriendsId(CerebookUser originatedUser, CerebookUser friend) {
        this.originatedUser = originatedUser;
        this.friend = friend;
    }
}
