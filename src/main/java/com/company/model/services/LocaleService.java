package com.company.model.services;

import com.company.model.dao.DaoFactory;

import java.util.Locale;

public class LocaleService {
    public void setLocale(Locale locale) {
        DaoFactory.changeBundleLocale(locale);
    }
}
