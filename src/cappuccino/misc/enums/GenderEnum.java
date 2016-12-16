package cappuccino.misc.enums;

/**
 * Created by MainasuK on 2016-11-30.
 */
public enum GenderEnum {
    male, female;

    @Override
    public String toString() {
        switch (this) {
            case male: return "男";
            case female: return "女";
            default: return "未知";
        }
    }
}
