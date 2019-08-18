package de.apkgrabber.model.APKMirror;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppExistsResponseRelease {

	@SerializedName("version")
	@Expose
	private String version;

	@SerializedName("publish_date")
	@Expose
	private String publishDate;

	@SerializedName("whats_new")
	@Expose
	private String whatsNew;

	@SerializedName("link")
	@Expose
	private String link;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getWhatsNew() {
		return whatsNew;
	}

	public void setWhatsNew(String whatsNew) {
		this.whatsNew = whatsNew;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}

