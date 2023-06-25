package com.shaw.utils.text;

import com.shaw.utils.AssertUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Collection;
import java.util.regex.Pattern;

public abstract class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static final String DEFAULT_SEPARATE_MARK = ",|，|#|\\$|@|\\*";

    public static final String EMAIL_REGEX = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";

    public static final String EMOJI = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";

    private static final Pattern EMOJI_PATTERN = Pattern.compile(EMOJI);

    @Deprecated
    @SuppressWarnings("unchecked")
    protected static <T> T split(String source, String separateMark, Class<? extends Collection<String>> desClazz) {
        AssertUtils.notNull(desClazz);
        AssertUtils.hasText(source);
        String[] splits = source.split(separateMark);
        if (splits == null || splits.length == 0)
            return null;
        Collection<String> result = null;
        try {
            result = desClazz.newInstance();
            for (String element : splits) {
                result.add(element);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) result;
    }

    public static void split(String source, String separateMark, Collection<String> outputCollection) {
        AssertUtils.notEmpty(outputCollection);
        AssertUtils.hasText(source);
        String[] splits = source.split(separateMark);
        if (splits == null || splits.length == 0)
            return;
        for (String element : splits) {
            outputCollection.add(element);
        }
    }

    public static boolean equalsWithAny(String string, String... searchStrings) {
        if (string == null) {
            for (String searchString : searchStrings) {
                if (string == searchString) {
                    return true;
                }
            }
        }

        if (string != null) {
            for (String searchString : searchStrings) {
                if (string.equals(searchString)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean equalsIgnoreCaseWithAny(String string, String... searchStrings) {
        if (string == null) {
            for (String searchString : searchStrings) {
                if (string == searchString) {
                    return true;
                }
            }
        }

        if (string != null) {
            for (String searchString : searchStrings) {
                if (string.equalsIgnoreCase(searchString)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String emojiFilter(String str) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            return EMOJI_PATTERN.matcher(str).replaceAll("");
        }
        return str;
    }

    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
    }

    // 判断一个字符串是否含有中文
    public static boolean isChinese(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (isChinese(c)) {
                return true;// 有一个中文字符就返回
            }
        }
        return false;
    }

    /**
     * 调整字符串首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * @param str
     * @return
     * @throws
     * @Title: spilt
     * @Description: 将含有逗号的字符串以逗号分隔并加上单引号
     * @author: tianqinghao
     * @date: 2020年8月21日 下午5:53:10
     * @return: String
     */
    public String spilt(String str) {
        StringBuffer sb = new StringBuffer();
        String[] temp = str.split(",");
        for (int i = 0; i < temp.length; i++) {
            if (!"".equals(temp[i]) && temp[i] != null)
                sb.append("'" + temp[i] + "',");
        }
        String result = sb.toString();
        String tp = result.substring(result.length() - 1, result.length());
        if (",".equals(tp))
            return result.substring(0, result.length() - 1);
        else
            return result;
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params   参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... params) {
        if (ArrayUtils.isEmpty(params) || isEmpty(template)) {
            return template;
        }
        return StrFormatter.format(template, params);
    }
}
