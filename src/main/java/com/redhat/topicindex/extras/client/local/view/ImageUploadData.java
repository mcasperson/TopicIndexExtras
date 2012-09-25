package com.redhat.topicindex.extras.client.local.view;

import java.util.Arrays;
import java.util.List;

import org.vectomatic.file.FileUploadExt;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;

public class ImageUploadData
{
	private static final List<String> LOCALES = Arrays.asList("ar", "ar-AE", "ar-BH", "ar-DZ", "ar-EG", "ar-IQ", "ar-JO", "ar-KW", "ar-LB", "ar-LY", "ar-MA", "ar-OM", "ar-QA", "ar-SA", "ar-SD", "ar-SY", "ar-TN", "ar-YE", "be", "be-BY", "bg", "bg-BG", "ca", "ca-ES", "cs", "cs-CZ", "da", "da-DK", "de", "de-AT", "de-CH", "de-DE", "de-LU", "el", "el-CY", "el-GR", "en", "en-AU", "en-CA", "en-GB", "en-IE", "en-IN", "en-MT", "en-NZ", "en-PH", "en-SG", "en-US", "en-ZA", "es", "es-AR", "es-BO", "es-CL", "es-CO", "es-CR", "es-DO", "es-EC", "es-ES", "es-GT", "es-HN", "es-MX", "es-NI", "es-PA", "es-PE", "es-PR", "es-PY", "es-SV", "es-US", "es-UY", "es-VE", "et", "et-EE", "fi", "fi-FI", "fr", "fr-BE", "fr-CA", "fr-CH", "fr-FR", "fr-LU", "ga", "ga-IE", "hi-IN", "hr", "hr-HR", "hu", "hu-HU", "in", "in-ID", "is", "is-IS", "it", "it-CH", "it-IT", "iw", "iw-IL", "ja", "ja-JP", "ja-JP-JP-#u-ca-japanese", "ko", "ko-KR", "lt", "lt-LT", "lv", "lv-LV", "mk", "mk-MK", "ms", "ms-MY", "mt", "mt-MT", "nl", "nl-BE", "nl-NL", "no", "no-NO", "no-NO-NY", "pl", "pl-PL", "pt", "pt-BR", "pt-PT", "ro", "ro-RO", "ru", "ru-RU", "sk", "sk-SK", "sl", "sl-SI", "sq", "sq-AL", "sr", "sr-BA", "sr-BA-#Latn", "sr-CS", "sr-ME", "sr-ME-#Latn", "sr-RS", "sr-RS-#Latn", "sr--#Latn", "sv", "sv-SE", "th", "th-TH", "th-TH-TH-#u-nu-thai", "tr", "tr-TR", "uk", "uk-UA", "vi", "vi-VN", "zh", "zh-CN", "zh-HK", "zh-SG", "zh-TW");
	private final FileUploadExt upload = new FileUploadExt();
	private final TextArea fileList = new TextArea();
	private final ListBox language = new ListBox(false);
	private final Grid grid = new Grid(3, 2);

	public FileUploadExt getUpload()
	{
		return upload;
	}

	public TextArea getFileList()
	{
		return fileList;
	}

	public ListBox getLanguage()
	{
		return language;
	}
	
	public Grid getGrid()
	{
		return grid;
	}
	
	public ImageUploadData()
	{
		fileList.setReadOnly(true);
		fileList.setWidth("500px");
		fileList.setHeight("200px");
		
		for (final String locale : LOCALES)
			language.addItem(locale);
		
		language.setSelectedIndex(LOCALES.indexOf("en-US"));
			
		getGrid().setWidget(0, 0, new Label("Select language for these images"));
		getGrid().setWidget(0, 1, language);
		
		getGrid().setWidget(1, 0, new Label("Select the images to be uploaded"));
		getGrid().setWidget(1, 1, upload);
		
		getGrid().setWidget(2, 0, new Label("This is a list of the images you have selected"));
		getGrid().setWidget(2, 1, fileList);
	}
	
	public void setEnabled(final boolean enabled)
	{
		this.getFileList().setEnabled(enabled);
		this.getLanguage().setEnabled(enabled);
		this.getUpload().setEnabled(enabled);
	}
}
