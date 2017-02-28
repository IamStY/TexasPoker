package com.example.stevenyang.texaspokerproject;

/**
 * Created by User on 2017/2/25.
 */

public class FinalResults {
    String resultString;
    int flag;

    public String getResultString() {
        return resultString;
    }

    public void setResultString(CardComboType cardComboType) {
        String comboString  = "";
        switch (cardComboType){
            case ROYAL_FLUSH:
                comboString = "同花順!!";
                break;
            case FOUR_OF_SAME:
                comboString = "鐵支啦!!";
                break;
            case FULL_HOUSE:
                comboString = "葫蘆!!";
                break;
            case SAME_COLOR:
                comboString = "同花!!";
                break;
            case FLUSH:
                comboString = "順子!!";
                break;
            case THREE_OF_SAME:
                comboString = "三條!";
                break;
            case TWO_PAIRS:
                comboString = "吐呸!!";
                break;
            case PAIR:
                comboString = "呸!!";
                break;
            case HIGH:
                comboString = "高牌... 靠邀";
                break;
        }
        this.resultString = comboString + " !!! 恭喜Player " + flag + " 獲得勝利!!!";
        if (flag == 0) {
            resultString  = "幹平局啦!" + cardComboType;
        }
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        if (flag == 0) {
            resultString = "幹平局啦!";
        } else {
            this.flag = flag;
        }
    }
}
