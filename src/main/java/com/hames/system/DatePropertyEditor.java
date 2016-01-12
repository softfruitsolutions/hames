package com.hames.system;

import java.beans.PropertyEditorSupport;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

public class DatePropertyEditor extends PropertyEditorSupport{
	
	private DateTimeFormatter dateTimeFormatter;
	
	public DatePropertyEditor(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
				
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		 if (!StringUtils.hasText(text)) {
            setValue(null);
        } else {
            setValue(this.dateTimeFormatter.parseDateTime(text));
        }
	}

	@Override
    public String getAsText() {
        DateTime dateTime = (DateTime) getValue();
        return (dateTime != null ? dateTime.toString(this.dateTimeFormatter) : "");
    }

}
