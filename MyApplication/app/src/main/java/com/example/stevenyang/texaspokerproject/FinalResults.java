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
        this.resultString = cardComboType + " !!! 恭喜Player " + flag + " 獲得勝利!!!";
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
