package com.tourMaster.letsTour.booking.controllerAdvices;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
public class GlobalDataBinder {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Example: trim all incoming string fields
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : text.trim());
            }
        });
    }
}
