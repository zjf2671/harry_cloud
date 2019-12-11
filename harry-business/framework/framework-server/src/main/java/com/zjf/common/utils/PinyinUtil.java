package com.zjf.common.utils;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by
 * Bernie on 2019/5/31.
 */
@Slf4j
public class PinyinUtil {


    /**
     * 获取字符串拼音的第一个字母
     *
     * @param chinese 中文
     * @return firstChar
     */
    public static String ToFirstChar(String chinese) {
        StringBuilder pinyinStr = new StringBuilder();
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char aNewChar : newChar) {
            if (aNewChar > 128) {
                try {
                    if (PinyinHelper.toHanyuPinyinStringArray(aNewChar, defaultFormat).length > 0) {
                        pinyinStr.append(PinyinHelper.toHanyuPinyinStringArray(aNewChar, defaultFormat)[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    log.error(e.getMessage(), e);
                }
            } else {
                pinyinStr.append(aNewChar);
            }
        }
        return pinyinStr.toString();
    }


    /**
     * 汉字转为拼音
     *
     * @param chinese 中文
     * @return 中文拼音
     */
    public static String ToPinyin(String chinese) {
        StringBuilder pinyinStr = new StringBuilder();
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char aNewChar : newChar) {
            if (aNewChar > 128) {
                try {
                    if (PinyinHelper.toHanyuPinyinStringArray(aNewChar, defaultFormat).length > 0) {
                        pinyinStr.append(PinyinHelper.toHanyuPinyinStringArray(aNewChar, defaultFormat)[0]);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    log.error(e.getMessage(), e);
                }
            } else {
                pinyinStr.append(aNewChar);
            }
        }
        return pinyinStr.toString();
    }
}
