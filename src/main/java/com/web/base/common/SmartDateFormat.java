package com.web.base.common;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yi on 2015/3/11.
 */
public class SmartDateFormat extends SimpleDateFormat {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
        return new StringBuffer(DateUtil.smartFormat(date));
    }

    @Override
    public Date parse(String text) throws ParseException {
        return DateUtil.smartFormat(text);
    }
}
