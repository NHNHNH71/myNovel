package novel.learn.myNovel.core.wrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;
import java.util.Map;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private static final Map<String, String> REPLACE_RULE = new HashMap<>();

    static {
        //将<>转换为&lt和&gt
        REPLACE_RULE.put("<", "&lt;");
        REPLACE_RULE.put(">", "&gt;");
    }

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            int length = values.length;
            String[] escapeValues = new String[length];
            for (int i = 0; i < length; i++) {
                escapeValues[i] = values[i];
                int index = i;
                REPLACE_RULE.forEach(
                        (k, v) -> escapeValues[index] = escapeValues[index].replaceAll(k, v));//对于每个字符，进行查找和替换
            }
            return escapeValues;//返回安全参数值
        }
        return new String[0];
    }
}
