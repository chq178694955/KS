package com.king.framework.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.theme.FixedThemeResolver;

@Configuration
public class ThemeConfig {

    @Value("${ks.theme}")
    private String theme;

    @Bean
    public ResourceBundleThemeSource themeSource(){
        ResourceBundleThemeSource ResourceBundleThemeSource = new ResourceBundleThemeSource();
        ResourceBundleThemeSource.setBasenamePrefix("theme");
        ResourceBundleThemeSource.setDefaultEncoding("utf-8");
        return ResourceBundleThemeSource;
    }

    @Bean
    public FixedThemeResolver themeResolver(){
        FixedThemeResolver fixedThemeResolver = new FixedThemeResolver();
        fixedThemeResolver.setDefaultThemeName(theme);
        return fixedThemeResolver;
    }

}
