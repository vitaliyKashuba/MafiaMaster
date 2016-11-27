package vitaliy94.mafiamaster.entitys;

import vitaliy94.mafiamaster.R;

public enum Roles {
    MAFIA(R.string.role_mafia),
    COMISSAR(R.string.role_comissar),
    DOCTOR(R.string.role_doctor),
    MANIAC(R.string.role_maniac),
    WHORE(R.string.role_whore),
    IMMORTAL(R.string.role_immortal),
    DON(R.string.role_don),
    SHERIFF(R.string.role_sheriff),
    CHOSEN_ONE(R.string.role_chosen_one),
    CITIZEN(R.string.role_citizen);

    private int resId;

    Roles(int rid) {
        resId = rid;
    }

    /**
     * returns resource id
     * use this with getString(resId) in activity code
     *
     * @return R.string.(id)
     */
    public int getResId()
    {
        return resId;
    }
}
