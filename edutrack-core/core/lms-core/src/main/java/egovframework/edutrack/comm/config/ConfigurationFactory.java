package egovframework.edutrack.comm.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.core.io.ClassPathResource;

public class ConfigurationFactory {
	
	private static ConfigurationFactory instance = null;
	private final PropertiesConfiguration config[] = new PropertiesConfiguration[2];
	
	/**
	 * 생성자
	 */
	private ConfigurationFactory() {
		try {
			config[0] = new PropertiesConfiguration("egovframework/properties/framework.properties");
			config[0].setReloadingStrategy(new FileChangedReloadingStrategy());
			
			File webContextFile = null;
			
			try {
				webContextFile = new ClassPathResource("egovframework/properties/webcontext.properties").getFile();
			} catch (FileNotFoundException ex) {
				System.out.println("webcontext.properties not found.. load webcontext-dummy.properties");
				webContextFile = new ClassPathResource("egovframework/properties/webcontext-dummy.properties").getFile();
			}
			
			config[1] = new PropertiesConfiguration(webContextFile);
			config[1].setReloadingStrategy(new FileChangedReloadingStrategy());
			
			/*
			String profile = System.getProperty("profile", "");
			String prop_framework_path = "egovframework/properties/framework.properties";
			String prop_webcontext_path = "egovframework/properties/webcontext.properties";
			
			if (!StringUtils.defaultString(profile).equals("")) {
				prop_framework_path = "egovframework/properties/framework." + profile + ".properties";
				prop_webcontext_path = "egovframework/properties/webcontext." + profile + ".properties";
			}
			
			config[0] = new PropertiesConfiguration(prop_framework_path);
			config[0].setReloadingStrategy(new FileChangedReloadingStrategy());
			
			File webContextFile = null;
			
			try {
				webContextFile = new ClassPathResource(prop_webcontext_path).getFile();
			} catch (FileNotFoundException ex) {
				System.out.println("webcontext.properties not found.. load webcontext-dummy.properties");
				webContextFile = new ClassPathResource("webcontext-dummy.properties").getFile();
			}
			
			config[1] = new PropertiesConfiguration(webContextFile);
			config[1].setReloadingStrategy(new FileChangedReloadingStrategy());
			 */
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\nSystem Error --> Properties File Not Found!");
		} catch (ConfigurationException e) {
			e.printStackTrace();
			System.out.println("\nSystem Error --> Properties File Not Found!");
		}

	}
	/**
	 * 환경설정 Factory 인스턴스를 반환한다. 
	 */
	public static ConfigurationFactory getInstance() {
		if (instance == null) {
			synchronized(ConfigurationFactory.class) {
				instance = new ConfigurationFactory(); 	
			}
		}
		return instance;
	}
	
	/**
	 * 0 : Framework 영역, 1 : WebContext 영역 관련 환경설정 인스턴스를 반환해준다.
	 * @param type
	 * @return
	 */
	public Configuration getConfiguration(int type){
		return config[type];
	}
}
