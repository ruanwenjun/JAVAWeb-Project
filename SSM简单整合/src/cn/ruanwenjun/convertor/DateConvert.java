package cn.ruanwenjun.convertor;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月9日 下午2:40:29
*/
public class DateConvert implements Converter<String, Date>{

	public Date convert(String source) {
		if(source != null) {
			Format f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				Date date = (Date) f.parseObject(source);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
