package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SqlUtil {
    
      private static final Logger log = LoggerFactory.getLogger(SqlUtil.class);

    public static final String _ID = "_ID";
    
    public static String getPaginitionSQL(String sql, int start, int limit){
        String pageSql = SQLPagination.getPaginitionSQL(sql, start, limit);
        log.debug("page sql: {}" , sql);
        return pageSql;
    }


    /**
     * 匹配select和from的正则表达式
     */
    private static Pattern selectFromPattern = Pattern.compile("select|from");
    /**
     * 根据select语句返回获得总数的语句，支持带子查询的
     * select a,b,c from d where f = g order by h,i desc 返回 select count(*) from  d where f = g
     * @param selectSql select 语句
     * @return count语句
     */
    public static String getCountSql(String selectSql){
        return "select count(*) "+ getFromSql(selectSql);
    }

    /**
     * 根据select语句返回获from语句，支持带子查询的
     * select a,b,c from d where f = g order by h,i desc 返回 from  d where f = g
     * @param selectSql select 语句
     * @return from 语句
     */
    public static String getFromSql(String selectSql){
        String lowerSelectSql = selectSql.toLowerCase();
        // from的起始位置
        int fromIndex = 0;
        // 最后一个order by的起始位置
        int lastOrderIndex = -1;
        // select的个数
        int selectCount = 0;
        // 获得主语句from的位置
        Matcher m = selectFromPattern.matcher(lowerSelectSql);
        while (m.find()) {
            // 遇到select,select个数加一
            if("select".equals(m.group())){
                selectCount++;
            }
            // 遇到一个from,select个数减一
            if("from".equals(m.group())){
                selectCount--;
            }
            // 遇到主语句的from时，刚好select的个数是0
            if(selectCount == 0){
                fromIndex = m.start();
                break;
            }
        }
        // 最后一个order by 的起始位置
        lastOrderIndex = lowerSelectSql.lastIndexOf(" order ");
        // 最后一个)号的位置
        int lastRightBracketIndex = lowerSelectSql.lastIndexOf(")");
        // 如果没有order或者更order在子查询里，就取整条sql
        if(lastOrderIndex == -1 || lastOrderIndex < lastRightBracketIndex){
            lastOrderIndex = lowerSelectSql.length();
        }
        return selectSql.substring(fromIndex,lastOrderIndex);
    }


    /**
     * 获取字段信息，用于拼接SQL
     * @param as
     * @return
     */
    public static String getIdsSql(String as, String columnName) {

        List<JSONObject> jsonObjects = JSONArray.parseArray(as, JSONObject.class);
        List<String> idList = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects) {
            String id = jsonObject.get(columnName).toString();
            idList.add(id);
        }
        if(CollectionUtils.isEmpty(idList)){
            return "";
        }

        String ids = idList.stream()
                .map(id -> "'" + (id.isEmpty() ? "" : id) + "'") // 空字符串也包裹在单引号中
                .collect(Collectors.joining(","));
        return ids;
    }

    
    
}
