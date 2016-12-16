package cappuccino.misc.enums;

/**
 * Created by MainasuK on 2016-11-30.
 */
public enum PositionEnum {
    admin, stockkeeper, teller, unknown;

    @Override
    public String toString() {
        switch (this) {
            case admin: return "系统管理员";
            case stockkeeper: return "仓库管理员";
            case teller: return "收银员";
            default: return "未知";
        }
    }
}
